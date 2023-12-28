package Prettier_Homes.controller;


import Prettier_Homes.dto.requests.AdvertCreateRequest;
import Prettier_Homes.dto.response.*;
import Prettier_Homes.security.JwtUserDetails;
import Prettier_Homes.service.AdvertsService;
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
@RequestMapping("/adverts")
@Slf4j
public class AdvertsController {
    @Autowired
    AdvertsService service;


//todo asagidaki methodda response clasi degiseke


    @GetMapping//A01 sorgu yazildi denenmedi.
    public ResponseEntity<Page<AdvertCartRespons>> getListFilter(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                    @RequestParam(value = "size", defaultValue = "10", required = false) int size,
                                                    @RequestParam(value = "sort", defaultValue = "title", required = false) String sort,
                                                    @RequestParam(value = "type", defaultValue = "DESC",required = false) Sort.Direction direction ,
                                                    @RequestParam(name ="q", required = false) String search,
                                                    @RequestParam(name ="categoryid", defaultValue = "", required = false) Long category,
                                                    @RequestParam(name ="country", defaultValue = "", required = false) Long country,
                                                    @RequestParam(name ="city", defaultValue = "", required = false) Long city,
                                                    @RequestParam(name ="district", defaultValue = "", required = false) Long district,
                                                    @RequestParam(name ="advert_type_id", defaultValue = "", required = false) Long advert_type,
                                                    @RequestParam(name ="price_start", required = false) Double price_start,
                                                    @RequestParam(name ="price_end", required = false) Double price_end,
                                                    @RequestParam(name ="status", required = false ,defaultValue = "0") int status)
    {
        return service.getListFilter(page,size,sort,direction,search,category,advert_type,price_start,price_end,status,country,city, district);
    }

      @GetMapping("/cities")//A02 ilk 10 city geliyor, ordered
    public ResponseEntity< List<AdvertsCitiesResponse>> getGroupAdvertCity(){

        return service.getGroupAdvertCity();
    }

    @GetMapping("/countries")//A03  ilk 10 city geliyor, ordered
    public ResponseEntity <List<AdvertsCountriesResponse>> getGroupAdvertCountry() {
        return service.getGroupAdvertCountry();
    }



    @GetMapping("/popular/{amount}")//A04
    public ResponseEntity<List<AdvertCartRespons>> getList(@PathVariable Long amount){
        return service.getPopulerList(amount);
    }


    @GetMapping("/auth")//A05
    public ResponseEntity<Page<AdvertCartRespons>> getFilterByAuth(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "title") String sort,
            @RequestParam(name = "q", required = false) String search){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();

        return service.getFilterByAuth(page,size,sort,search,jwtUserDetails);
    }
    @GetMapping("/userAdverts/{id}")//A05
    public ResponseEntity<Page<AdvertCartRespons>> getAdvertsByUser(
            @PathVariable Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();

        return service.getAdvertsByUser(id,page,size,"title",jwtUserDetails);
    }

    @PostMapping //A10 bu method degistirilecek.... en azindan request degistirilecek
    public ResponseEntity<AdvertCreateRequest> create(@RequestBody AdvertCreateRequest dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        System.out.println("controller");
        return service.create(dto, jwtUserDetails);
    }

    @GetMapping("/admin")//A06
    public ResponseEntity<Page<AdvertCartRespons>> getFilterByAdmin(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "title") String sort,
            @RequestParam(value = "direction", defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(name = "q", required = false) String search,
            @RequestParam(name = "category_id", required = false) Long category,
            @RequestParam(name = "advert_type_id", required = false) Long advertType,
            @RequestParam(name = "price_start", required = false) Double priceStart,
            @RequestParam(name = "price_end", required = false) Double priceEnd,
            @RequestParam(name ="country", defaultValue = "", required = false) Long country,
            @RequestParam(name ="city", defaultValue = "", required = false) Long city,
            @RequestParam(name ="district", defaultValue = "", required = false) Long district,
            @RequestParam(name = "status", required = false) Integer status) {

        return service.getFilterByAdmin(page, size, sort, direction, search, category, advertType, priceStart, priceEnd, status,country,city, district);
    }

    @GetMapping("/{slug}")//A07 bitti, kontrol edilecek
    public ResponseEntity<AdvertDetailBySlugResponse> getAdvertDetailBySlug(@PathVariable String slug){
        return service.getAdvertDetailBySlug(slug);
    }

    @GetMapping("/{slug}/auth") //A08 bitti, yukaridaki method dan ne farki var anlayamadim.
    public ResponseEntity<AdvertDetailBySlugResponse> getUsersAdvertBySlug(@PathVariable String slug){
        return service.getAdvertDetailBySlug(slug);
    }

    @GetMapping("/{slug}/admin") //A09 bitti, yukaridaki method dan ne farki var anlayamadim.
    public ResponseEntity<AdvertDetailBySlugResponse> getAdvertBySlugForAdmin(@PathVariable String slug){
        return service.getAdvertDetailBySlug(slug);
    }


@PutMapping("/auth/{slug}") //A11 bitti, bu advert kendisinin mi kontrolu yapilacak
    public ResponseEntity<AdvertCreateRequest> update(@RequestBody AdvertCreateRequest dto){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        return service.update(dto, jwtUserDetails.getId());
}
@PutMapping("/admin/{slug}") //A12 bitti
    public ResponseEntity<AdvertCreateRequest> updateForAdmin_Mangr(@RequestBody AdvertCreateRequest dto){
        return service.updateForAdmin(dto);
}

@DeleteMapping("/admin/{id}")
    public ResponseEntity<DeleteRespons> delete(@PathVariable Long id){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        return service.delete(id, jwtUserDetails);

}


}
