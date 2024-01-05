package Prettier_Homes.service;

import Prettier_Homes.dto.AdvertsDto;
import Prettier_Homes.dto.FavoritesDto;
import Prettier_Homes.dto.response.AdvertCartRespons;
import Prettier_Homes.dto.response.FavoritesAddRemoveResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FavoritesService {




    ResponseEntity<Page<AdvertCartRespons>> getListAdmin(Long userId, int page, int size, String sort, Sort.Direction direction);


    ResponseEntity<FavoritesDto> deleteAuth();

    ResponseEntity<FavoritesDto> deleteAdmin();


    void deleteIdAdmin(Long id);

    ResponseEntity<List<AdvertsDto>> getListForUser(Long id);

    ResponseEntity<FavoritesAddRemoveResponse> addOrRemoveFavorite(Long advertId, Long id);


    ResponseEntity<List<FavoritesAddRemoveResponse>> getListForUserForCart(Long id);

    Boolean isFavory(Long advertId, Long id);
}
