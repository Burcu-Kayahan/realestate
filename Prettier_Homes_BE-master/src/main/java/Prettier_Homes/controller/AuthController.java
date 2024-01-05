package Prettier_Homes.controller;


import Prettier_Homes.dto.requests.LoginRequest;
import Prettier_Homes.dto.requests.RefreshRequest;
import Prettier_Homes.dto.requests.RegisterRequest;
import Prettier_Homes.dto.requests.ResetPasswordRequest;
import Prettier_Homes.dto.response.AuthResponse;
import Prettier_Homes.service.Impl.AuthService;
import Prettier_Homes.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController

@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    AuthService service;

    @Autowired
    UserService userService;



    @PostMapping("/login") //  F01 bitti
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        return service.login(loginRequest);
    }

    @PostMapping("/register") // F02 bitti
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return service.register(registerRequest);
    }

    @PostMapping("/refresh") // bu gorevde yok. auth respons guncellenecek. refresh token gonderilmeyecek
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
        return service.refresh(refreshRequest);}

    @PostMapping("/forgot-password") //
    public void fotgotPasswordReq(@RequestBody String email) throws MessagingException, UnsupportedEncodingException {
        String decodedEmail = URLDecoder.decode(email, "UTF-8");
        System.out.println(decodedEmail);
        if (decodedEmail.endsWith("=")) {
            decodedEmail = decodedEmail.substring(0, decodedEmail.length() - 1);
        }
        service.forgotPasswortReq(decodedEmail);
    }

    @PostMapping("/reset-password") //F04 bitti
    public void resetPassword(@RequestBody ResetPasswordRequest request){
        userService.resetPassword(request);
    }

    @PostMapping("/registerconfirm/{code}")
    public ResponseEntity<AuthResponse> registerConfirm(@PathVariable Long code){
        return service.registerConfirm(code);
    }

}
