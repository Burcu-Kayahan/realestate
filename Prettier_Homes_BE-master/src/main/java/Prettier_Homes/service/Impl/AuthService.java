package Prettier_Homes.service.Impl;


import Prettier_Homes.converter.UserMapper;
import Prettier_Homes.data.entity.RefreshToken;
import Prettier_Homes.data.entity.RoleEntity;
import Prettier_Homes.data.entity.UserEntity;
import Prettier_Homes.data.repository.RoleRepository;
import Prettier_Homes.data.repository.UserRepository;
import Prettier_Homes.dto.requests.LoginRequest;
import Prettier_Homes.dto.requests.RefreshRequest;
import Prettier_Homes.dto.requests.RegisterRequest;
import Prettier_Homes.dto.response.AuthResponse;
import Prettier_Homes.security.JwtTokenProvider;
import Prettier_Homes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {

    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    UserMapper userMapper;
    public AuthResponse login(LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        UserEntity user = userService.getOneUserByEmail(loginRequest.getEmail());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken("Bearer " + jwtToken);
        authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
        authResponse.setUserId(user.getId());
        authResponse.setUser(userMapper.toDto(user));
        authResponse.setIsAuthenticated(true);
        authResponse.setMessage("Login success");
        return authResponse;
    }

    public ResponseEntity<AuthResponse> register(RegisterRequest registerRequest) {
        AuthResponse authResponse = new AuthResponse();
        if(userService.getOneUserByEmail(registerRequest.getEmail()) != null) {
            authResponse.setMessage("Email already in use.");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setCreateAt(LocalDateTime.now());
        user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setPhone(registerRequest.getPhone());
        user.setBuiltIn(false);

        RoleEntity role = roleRepository.findByName("CUSTOMER");
        user.setRoles(role);
        Long confirmToken = generateConfirmToken();
        user.setActiveCode(""+confirmToken);
        user.setActive(false);
        UserEntity user1=     userRepository.save(user);



        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);

        authResponse.setMessage("User successfully registered.");
        authResponse.setToken("Bearer " + jwtToken);
        authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
        authResponse.setUserId(user.getId());
        authResponse.setUser(userMapper.toDto(user1));

        emailService.sendEmailRegister(user.getEmail(),confirmToken , user.getFirstName());
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }//todo kisi register olduktan sonra email gonderilecek ve register onaylanmasi istenecek.

    public ResponseEntity<AuthResponse> refresh(RefreshRequest refreshRequest) {
        AuthResponse response = new AuthResponse();
        RefreshToken token = refreshTokenService.getByUser(refreshRequest.getUserId());
        if(token.getToken().equals(refreshRequest.getRefreshToken()) &&
                !refreshTokenService.isRefreshExpired(token)) {

            UserEntity user = token.getUser();
            String jwtToken = jwtTokenProvider.generateJwtTokenByUserId(user.getId());
            response.setMessage("token successfully refreshed.");
            response.setToken("Bearer " + jwtToken);
            response.setUserId(user.getId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("refresh token is not valid.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

    }


   public Long generateConfirmToken(){
       Random random = new Random();
       Long x = Math.abs(random.nextLong() % 1_000_000);
       Long y = Math.abs(random.nextLong() %  100_000_000);
       String s = x+""+y;
       return Long.parseLong((s));
   }


    public void forgotPasswortReq(String email) throws MessagingException {
        System.out.println(email+"sdddf");
        Optional<UserEntity> user = Optional.ofNullable(userRepository.findByEmail(email));
        if(user.isPresent()){
            System.out.println(email+"service");
            emailService.forgotPassword(user.get(), email);
        }

    }


    public ResponseEntity<AuthResponse> registerConfirm(Long code) {
      Optional<UserEntity> user = userRepository.findByActiveCode(""+code);
      AuthResponse response = new AuthResponse();
      if(user.isEmpty()){
          response.setMessage("user not found");
          return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
      }
      user.get().setActive(true);
      user.get().setActiveCode(null);
      user.get().setUpdateAt(LocalDateTime.now());
      userRepository.save(user.get());
      response.setMessage("Merhaba "+user.get().getFirstName()+" "+user.get().getLastName()+". Basariyla kaydoldunuz. Yeniden giris yapabilirsiniz.");
  return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
