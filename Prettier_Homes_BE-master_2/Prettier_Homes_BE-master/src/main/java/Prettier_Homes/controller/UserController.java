package Prettier_Homes.controller;


import Prettier_Homes.data.entity.UserEntity;
import Prettier_Homes.dto.UserDto;
import Prettier_Homes.dto.requests.ResetPasswordRequest;
import Prettier_Homes.dto.requests.UserUpateForAdminManager;
import Prettier_Homes.dto.response.DeleteRespons;
import Prettier_Homes.dto.response.UserAllinfoResponseForAdmin;
import Prettier_Homes.security.JwtUserDetails;
import Prettier_Homes.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

     @Autowired
     UserService service;

    //    @PostMapping
    //    public ResponseEntity<WordsDto> create(@RequestBody WordsDto dto) {
    //        return service.create(dto);
    //    }
    //
    //
    //    @GetMapping("/admin")
    //    public ResponseEntity<Page<WordsDto>> getList(@RequestParam(value = "page", defaultValue = "0") int page,
    //                                                  @RequestParam(value = "size", defaultValue = "10") int size,
    //                                                  @RequestParam(value = "sort", defaultValue = "name") String sort,
    //                                                  @RequestParam(value = "type", defaultValue = "desc") Sort.Direction direction ,
    //                                                  @RequestParam(name ="q", required = false) String search) {
    //        return service.getList(page,size,sort,direction,search);
    //    }
    //
    //

//         @PostMapping("/reset-password") //F04 bitti
//         public void resetPassword(@RequestBody ResetPasswordRequest request){
//        service.resetPassword(request);
//    }
    //yukaridaki method authController a tasindi. token olmadan erisilebilmeli

        @GetMapping("/auth") //F05 bitti
        public ResponseEntity<UserDto> getByAuth() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
            return service.getByAuth(jwtUserDetails.getId());
        }

    //
        @PutMapping("/auth")  //F06 bitti
        public ResponseEntity<UserDto> update(@RequestBody UserDto dto) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();

            return service.update(jwtUserDetails.getId(), dto, jwtUserDetails);
        }



        @PatchMapping("/auth")  //F07 bitti
       public ResponseEntity<String> updatePassword(@RequestBody ResetPasswordRequest request){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
          return   service.updatePassword(jwtUserDetails.getId(), request);
        }





        @DeleteMapping("/auth") //F08 bitmedi. kisinin diger tablolarda halen kayitli bilgileri varsa ya onlarida silecegiz,
        // yada baska ozellestirme yapacaz. user pasif yapmak gibi.     Favory ve advert servicelerinin bitmesi bekleniyor
        public ResponseEntity<DeleteRespons> delete() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
            return service.delete(jwtUserDetails.getId());
        }


        @GetMapping("/admin") //F09 biiti
        public ResponseEntity<Page<UserUpateForAdminManager>> getUsersByFilter(@RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "size", defaultValue = "10") int size,
                                                      @RequestParam(value = "sort", defaultValue = "email",required = false) String sort,
                                                      @RequestParam(value = "type", defaultValue = "desc",required = false) Sort.Direction direction ,
                                                      @RequestParam(name ="q", required = false) String search){
            return service.getUsersByFilter(page,size,sort,direction,search);
        }

        @GetMapping("/{userId}/admin") //F10 todo bu end poin tamamen degistirilecek, clas icerisndeki tum objeler ayri ayri cekilecek
        public ResponseEntity<UserAllinfoResponseForAdmin> getUserInfos(@PathVariable Long userId){
            return service.getUserAllInfos(userId);
        }

        @PutMapping("/{userId}/admin") //F11 bitti, hasAnyAuth..... yazilacak
        public ResponseEntity<UserUpateForAdminManager> updateUser(@PathVariable Long userId, @RequestBody UserUpateForAdminManager user){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
             return service.updateUserByAdminAndManager(userId, user, jwtUserDetails);
        }

//Admin Manager
        @DeleteMapping("/{userId}/admin") //F12 bitti, sadece F08 deki service methodunun tamamlanmasi gerekiyor. F08 e bagli
        public ResponseEntity<DeleteRespons> deleteUserByAdmin(@PathVariable Long userId){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        return service.deleteUserByAdmin(userId, jwtUserDetails);
        }

        @GetMapping("/all") // deneme amacli yazildi silinecek
        public ResponseEntity<Page<UserEntity>> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "10") int size,
                                                   @RequestParam(value = "sort", defaultValue = "email") String sort,
                                                   @RequestParam(value = "type", defaultValue = "desc") Sort.Direction direction ,
                                                   @RequestParam(name ="q", required = false) String search){
            return service.getAll(page,size,sort,direction,search);
        }


}
