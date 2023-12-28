package Prettier_Homes.service;

import Prettier_Homes.dto.AdvertsDto;
import Prettier_Homes.dto.requests.AdvertCreateRequest;
import Prettier_Homes.dto.response.*;
import Prettier_Homes.security.JwtUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdvertsService  {


    ResponseEntity<Page<AdvertsDto>> getList(int page, int size, String sort, Sort.Direction direction, String search);

    ResponseEntity<List<AdvertsDto>> getList();

    ResponseEntity<Page<AdvertCartRespons>> getListFilter(int page, int size, String sort, Sort.Direction direction, String search, Long category, Long advert_type, Double price_start, Double price_end, int status, Long country,Long city,Long district);

    ResponseEntity<AdvertCreateRequest> create(AdvertCreateRequest dto, JwtUserDetails jwtUserDetails);

    ResponseEntity<List<AdvertsDto>> getAdvertsByUserId(Long userId);

    ResponseEntity<Page<AdvertCartRespons>> getFilterByAdmin(int page, int size, String sort, Sort.Direction direction, String search, Long category, Long advert_type, Double price_start, Double price_end, Integer status, Long country,Long city,Long district);

    ResponseEntity<List<AdvertsCitiesResponse>>getGroupAdvertCity();

    ResponseEntity<List<AdvertsCountriesResponse>> getGroupAdvertCountry();

    ResponseEntity<Page<AdvertCartRespons>> getFilterByAuth(int page, int size, String sort, String search, JwtUserDetails jwtUserDetails);




    ResponseEntity<AdvertDetailBySlugResponse> getAdvertDetailBySlug(String slug);

    ResponseEntity<AdvertCreateRequest> update(AdvertCreateRequest dto, Long id);

    ResponseEntity<AdvertCreateRequest> updateForAdmin(AdvertCreateRequest dto);

    ResponseEntity<DeleteRespons> delete(Long id, JwtUserDetails jwtUserDetails);

    ResponseEntity<List<AdvertCartRespons>> getPopulerList(Long amount);


    ResponseEntity<Page<AdvertCartRespons>> getAdvertsByUser(Long id,int page, int size, String title, JwtUserDetails jwtUserDetails);
}
