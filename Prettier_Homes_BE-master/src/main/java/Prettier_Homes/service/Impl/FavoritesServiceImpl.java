package Prettier_Homes.service.Impl;


import Prettier_Homes.converter.AdvertMapper;
import Prettier_Homes.converter.favoritesMapper;
import Prettier_Homes.data.entity.AdvertsEntity;
import Prettier_Homes.data.entity.FavoritesEntity;
import Prettier_Homes.data.entity.UserEntity;
import Prettier_Homes.data.repository.AdvertsRepository;
import Prettier_Homes.data.repository.FavoritesRepository;
import Prettier_Homes.dto.AdvertsDto;
import Prettier_Homes.dto.FavoritesDto;
import Prettier_Homes.dto.response.AdvertCartRespons;
import Prettier_Homes.dto.response.FavoritesAddRemoveResponse;
import Prettier_Homes.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FavoritesServiceImpl implements FavoritesService {

    @Autowired
    FavoritesRepository repository;

    @Autowired
    favoritesMapper mapper;

    @Autowired
    AdvertsRepository advertsRepository;

    @Autowired
    AdvertMapper advertMapper;


    @Override
    public ResponseEntity<Page<AdvertCartRespons>> getListAdmin(Long userId, int page, int size, String sort, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(direction,sort));
        Page<AdvertCartRespons> resultList= repository.getFavotitesAdvertByUser(userId, pageable);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FavoritesDto> deleteAuth() {
        return null;
    }

    @Override
    public ResponseEntity<FavoritesDto> deleteAdmin() {
        return null;
    }

    @Override
    public ResponseEntity<FavoritesDto> deleteIdAdmin() {
        return null;
    }

    @Override
    public ResponseEntity<List<AdvertsDto>> getListForUser(Long id) {
        List<AdvertsEntity> entities = repository.getFavoritesByUserId(id);
        List<AdvertsDto> resultList = advertMapper.toDto(entities);

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FavoritesAddRemoveResponse> addOrRemoveFavorite(Long advertId, Long id) {
            FavoritesAddRemoveResponse result = new FavoritesAddRemoveResponse();
        Optional <FavoritesEntity> favorites = repository.findByUserIdAndAdvertId(id,advertId);
        if (favorites.isPresent()){
            repository.deleteById(favorites.get().getId());
            result.setId(favorites.get().getId());
            result.setRemove(true);
            result.setAdd(false);
            result.setAdvertId(favorites.get().getAdvert().getId());

            return  new ResponseEntity<>(result,HttpStatus.OK);

        }else {
            FavoritesEntity entity = new FavoritesEntity();
            AdvertsEntity advert = new AdvertsEntity();
            advert.setId(advertId);
            entity.setAdvert(advert);
            UserEntity user = new UserEntity();
            user.setId(id);
            entity.setUser(user);
            entity.setCreateAt(LocalDateTime.now());
            repository.save(entity);

            result.setAdd(true);
            result.setRemove(false);
            result.setAdvertId(advertId);

            return  new ResponseEntity<>(result,HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<List<FavoritesAddRemoveResponse>> getListForUserForCart(Long id) {
        List<FavoritesAddRemoveResponse> resultList = repository.getListForUserForCart(id);
        return  new ResponseEntity<>(resultList,HttpStatus.OK);
    }

    @Override
    public Boolean isFavory(Long advertId, Long id) {
        Optional <FavoritesEntity> favorites = repository.findByUserIdAndAdvertId(id,advertId);
        if ((favorites.isPresent())){
            return true;
        }else {
            return false;
        }

    }
}
