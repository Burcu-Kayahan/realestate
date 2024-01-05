package Prettier_Homes.service.Impl;

import Prettier_Homes.converter.RoleMapper;
import Prettier_Homes.converter.UserMapper;
import Prettier_Homes.data.entity.RoleEntity;
import Prettier_Homes.data.entity.UserEntity;
import Prettier_Homes.data.repository.RoleRepository;
import Prettier_Homes.data.repository.UserRepository;
import Prettier_Homes.dto.*;
import Prettier_Homes.dto.requests.ResetPasswordRequest;
import Prettier_Homes.dto.requests.UserUpateForAdminManager;
import Prettier_Homes.dto.response.DeleteRespons;
import Prettier_Homes.dto.response.UserAllinfoResponseForAdmin;
import Prettier_Homes.security.JwtUserDetails;
import Prettier_Homes.service.AdvertsService;
import Prettier_Homes.service.LogsService;
import Prettier_Homes.service.TourRequestService;
import Prettier_Homes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    UserMapper mapper;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AdvertsService advertsService;

    @Autowired
    LogsService logsService;

    @Autowired
    TourRequestService tourRequestService;
//    @Autowired
//    FavoriyRepository favoriyRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleMapper roleMapper;
    @Override
    public ResponseEntity<UserDto> getByAuth(Long id) {
        UserEntity user = repository.findById(id).get();
        return new ResponseEntity<>(mapper.toDto(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> update(Long id, UserDto dto, JwtUserDetails jwtUserDetails) {
        Optional<UserEntity> entity= repository.findById(id);

        if(entity.isEmpty() || entity.get().getBuiltIn()){
            throw new RuntimeException("Data could not be found or User is Built in. Can not update: " );
        }
//        if (entity.get().getRoles().contains())

        mapper.toEntityForUpdate(dto, entity.get());
        entity.get().setId(id);
        entity.get().setUpdateAt(LocalDateTime.now());
       UserEntity resultEntity=  repository.save(entity.get());
       UserDto result = mapper.toDto(resultEntity);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    public UserEntity getOneUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public ResponseEntity<String> updatePassword(Long id, ResetPasswordRequest request) {
        System.out.println(request.getPassword());
        System.out.println(request.getNewPassword());

        Optional<UserEntity> entity= repository.findById(id);
        System.out.println("==========");

        System.out.println(encoder.matches(request.getPassword(),entity.get().getPasswordHash()));

        System.out.println(entity.get().getPasswordHash()); //db de kayitli encode edilmis password

        System.out.println(encoder.encode(request.getPassword())); // passwordun yeniden encode edilmis hali

        if(entity.isEmpty() || entity.get().getBuiltIn()){

            throw new RuntimeException("Data could not be found or User is Built in. Can not update: " );
        } else if (!encoder.matches(request.getPassword(), entity.get().getPasswordHash())) {
            throw new RuntimeException("Current password is incorrect: " );
        }
        entity.get().setUpdateAt(LocalDateTime.now());
        entity.get().setPasswordHash(encoder.encode(request.getNewPassword()));
        entity.get().setResetPasswordCode(null);
        repository.save(entity.get());
        return new ResponseEntity<>("Password update successful", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DeleteRespons> delete(Long id) {
        Optional<UserEntity> entity= repository.findById(id);
        if(entity.isEmpty() || entity.get().getBuiltIn()){
            throw new RuntimeException("Data could not be found or User is Built in. Can not update: " );
        }
        // Advert kayitli ise silinme basatrisiz denecek ve Ait olan advertlere ait kisa bilgi gonderilecek.
        //Tour isteklerinde bir kaydi varsa silinemeyecek
        // favoriler kontrol edilecek ve silinecek
        // kisininin log kayitlari silinecek.
        // en son ksiiyi silecegiz
//        entity.get().setRoles(null);
//        repository.save(entity.get());
        repository.deleteById(id);
        DeleteRespons deleteRespons = new DeleteRespons();
        deleteRespons.setMessage("Delete basarili");
        deleteRespons.setDelete(true);
        return new ResponseEntity<>(deleteRespons, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<UserUpateForAdminManager>> getUsersByFilter(int page, int size, String sort, Sort.Direction direction, String search) {

        Pageable pageable = PageRequest.of(page,size, Sort.by(direction,sort));

        Page<UserEntity> entities = repository.findByFirstNameContainingOrLastNameContainingOrEmailContainingOrPhoneContaining(search, search, search,search, pageable);
        Page<UserUpateForAdminManager> resultList = entities.map(entity -> mapper.toDtoForAdmin(entity));

//        int count = 0;
//        for (UserUpateForAdminManager x : resultList.getContent()) {
//            // Mevcut rolleri temizle
//            x.getRoles().clear();
//
//            Set<RoleEntity> roles = entities.getContent().get(count).getRoles();
//            Set<RoleDto> roleDtos = new HashSet<>();
//
//            for (RoleEntity r : roles) {
//                roleDtos.add(convertRoleToDto(r));
//            }
//
//            x.setRoles(roleDtos);
//
//            count++;
//        }

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    public RoleDto convertRoleToDto(RoleEntity entity){
        return roleMapper.toDto(entity);
    }

    @Override
    public ResponseEntity<UserAllinfoResponseForAdmin> getUserAllInfos(Long userId) {
        UserAllinfoResponseForAdmin result = new UserAllinfoResponseForAdmin();

        Optional<UserEntity> userEntity= repository.findById(userId);

        if(userEntity.isEmpty() || userEntity.get().getBuiltIn()){
            result.setMessage("User id Not Found id : "+ userId);
           return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        UserDto user = mapper.toDto(userEntity.get());

        List<AdvertsDto> adverts = advertsService.getAdvertsByUserId(userId).getBody();

        List<LogsDto> logs = logsService.getLogsByUserId(userId).getBody();

        List<FavoritesDto> favorites = new ArrayList<>();
        //favoriler Zisan hanim gonderdikten sonra yazilacak
        List<TourRequestDto> tourRequest=tourRequestService.getAllByUserId(userId).getBody();

        result = new UserAllinfoResponseForAdmin(user,adverts,tourRequest,favorites,logs,"success");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {
        String encodedCode=encoder.encode(request.getCode());
//        Optional<UserEntity> entity = repository.findByResetPasswordCode(encodedCode);
        Optional<UserEntity> entity = Optional.ofNullable(repository.findByEmail(request.getEmail()));
        if(entity.isPresent()){
            if (encoder.matches(request.getCode(),entity.get().getResetPasswordCode())){
                entity.get().setPasswordHash(encoder.encode(request.getPassword()));
                entity.get().setResetPasswordCode(null);
                repository.save(entity.get());
            }else {
                throw new RuntimeException("Reset Code is not valid");
            }
        }else{
            throw new RuntimeException("Reset Code is not valid");
        }
    }

    @Override
    public ResponseEntity<UserUpateForAdminManager> updateUserByAdminAndManager(Long userId, UserUpateForAdminManager userDto, JwtUserDetails jwtUserDetails) {
        Optional<UserEntity> entity= repository.findById(userId);
        if(entity.isEmpty() || entity.get().getBuiltIn() ){
            throw new RuntimeException("Data could not be found or User is Built in. Can not update: " );
        }
        if(!isAdmin(jwtUserDetails)){
            if(isAdminOrManager(entity.get())){
                throw  new RuntimeException("unauthorized user");

            }
        }
// burada hersey uygunsa method calismaya devam edicek
        userDto.setId(userId);
        mapper.toEntityForUpdateForAdminAndManager(userDto, entity.get());
        RoleEntity role = roleRepository.findByName((userDto.getRole()== null ) ? "CUSTOMER":userDto.getRole());
        if(role.getId()<0 || role.getId()==null){
            role= roleRepository.findByName("CUSTOMER");
        }
        entity.get().setRoles(role);
        entity.get().setUpdateAt(LocalDateTime.now());
        entity.get().setBuiltIn(false);
        UserUpateForAdminManager result =mapper.toDtoForAdmin(repository.save(entity.get()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DeleteRespons> deleteUserByAdmin(Long userId,  JwtUserDetails jwtUserDetails) {
        Optional<UserEntity> user = repository.findById(userId);
        if(user.isEmpty()) throw new RuntimeException("User not fount. id : "+userId);
        if (user.get().getBuiltIn())throw new RuntimeException("User is Built_in = true. Can Not delete. id : "+userId + " Email : "+ user.get().getEmail());

        if(!isAdmin(jwtUserDetails)){ // Admin degil sadece Manager ise. silecegi kisinin sadece Custemer olup olmadigina bakiyoruz
            if (!isAdminOrManager(user.get())){ // ne admin nede manager. Manager bu kisiyi silebilri
             return   delete(userId);
            }else {
                throw new RuntimeException("Manager can not delete this user. ");
            }
        }

        return delete(userId);
    }

    @Override
    public ResponseEntity<Page<UserEntity>> getAll(int page, int size, String sort, Sort.Direction direction, String search) {

        Pageable pageable = PageRequest.of(page,size, Sort.by(direction,sort));

        Page<UserEntity> entities = repository.findByFirstNameContainingOrLastNameContainingOrEmailContainingOrPhoneContaining(search, search, search,search, pageable);

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }


    public boolean isAdmin( JwtUserDetails jwtUserDetails){
        boolean isAdmin = jwtUserDetails.getAuthorities().stream()
                .anyMatch(authority -> "ADMIN".equals(authority.getAuthority()));
        return isAdmin; //// burada degisiklik yapmak isteyen ksiinin rolu admin olup olmadigi kontrol ediliyor
        // Admin kullanicisi herkesi update edebilir.
        // eger degisiklik yapmaya calisan bir Manager role ise sadece Customer role sahip kisileri update edebilir.
    }

    public boolean isAdminOrManager(UserEntity entity){
        if (entity.getRoles().equals("ADMIN") ||entity.getRoles().equals("MANAGER") ){
            return  true;
        }
        return  false;}
    // burada eger degisiklik yapmak steyen kisinin rolu Admin degilse, degistirmek istedigi kisinin rolune bakiyoruz. degistirmek istedigi kisi
    // Admin veya manager rolune sahip ise update yapmasina izin vermiyoruz. ex.. firlatiyoryuz
}
