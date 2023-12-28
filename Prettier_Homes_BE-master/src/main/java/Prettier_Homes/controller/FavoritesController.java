package Prettier_Homes.controller;


import Prettier_Homes.dto.AdvertsDto;
import Prettier_Homes.dto.FavoritesDto;
import Prettier_Homes.dto.response.AdvertCartRespons;
import Prettier_Homes.dto.response.FavoritesAddRemoveResponse;
import Prettier_Homes.security.JwtUserDetails;
import Prettier_Homes.service.FavoritesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@Slf4j
public class FavoritesController {

    @Autowired
    FavoritesService service;

    @GetMapping("/auth") //bitti K01
    public ResponseEntity<List<AdvertsDto>> getListForUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();

        Long id = jwtUserDetails.getId();
        return service.getListForUser(id);
    }
      @GetMapping("/forCart/auth") //bitti K01
    public ResponseEntity<List<FavoritesAddRemoveResponse>> getListForUserForCart(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();

        Long id = jwtUserDetails.getId();
        return service.getListForUserForCart(id);
    }


   // @PreAuthorize("h")
    @GetMapping("/admin/{userId}") //bitti K02
    public ResponseEntity<Page<AdvertCartRespons>> getListAdmin(@PathVariable Long userId,
                                                                @RequestParam(value = "page", defaultValue = "0",required = false) int page,
                                                                @RequestParam(value = "size", defaultValue = "10",required = false) int size,
                                                                @RequestParam(value = "sort", defaultValue = "title",required = false) String sort,
                                                                @RequestParam(value = "direction", defaultValue = "ASC",required = false) Sort.Direction direction){
        System.out.println(userId);
        return service.getListAdmin(userId, page, size, sort, direction);
    }

    @PostMapping("")
    public ResponseEntity<FavoritesAddRemoveResponse> addOrRemoveFavorite( @RequestBody FavoritesAddRemoveResponse id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();

        return service.addOrRemoveFavorite(id.getAdvertId(),jwtUserDetails.getId());
    }
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/admin/{id}")
    public ResponseEntity<FavoritesAddRemoveResponse> addOrRemoveFavorite( @PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();

        return service.addOrRemoveFavorite(id,jwtUserDetails.getId());
    }
    @DeleteMapping("/auth")
    public ResponseEntity<FavoritesDto> deleteAuth() {
        return service.deleteAuth();
    }

    @DeleteMapping("/admin")
    public ResponseEntity<FavoritesDto> deleteAdmin() {
        return service.deleteAdmin();
    }

    @DeleteMapping("/{id}/admin")
    public ResponseEntity<FavoritesDto> deleteIdAdmin() {
        return service.deleteIdAdmin();
    }
//    @PreAuthorize("hasAnyAuthority('CUSTOMER','ADMIN')")
    @GetMapping("/isFavory/{id}")
    public Boolean isFavory(@PathVariable Long id){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
    return service.isFavory(id, jwtUserDetails.getId());
}
//todo sevice katinda kisi bilgilerini cekecegiz.
    // todo METHODLARA ROLE SEVIYESI ATAYALIM.
}

