package Prettier_Homes.data.repository;

import Prettier_Homes.data.entity.AdvertsEntity;
import Prettier_Homes.data.entity.FavoritesEntity;
import Prettier_Homes.dto.AdvertsDto;
import Prettier_Homes.dto.response.AdvertCartRespons;
import Prettier_Homes.dto.response.FavoritesAddRemoveResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritesRepository extends JpaRepository <FavoritesEntity,Long >{


    //List<FavoritesEntity> findByUserId(Long id);

    @Query("select a from AdvertsEntity a " +
            "join FavoritesEntity f on f.advert.id=a.id " +
            "where f.user.id =:id")
    List<AdvertsEntity> getFavoritesByUserId( @Param("id") Long id);

    Optional<FavoritesEntity> findByUserIdAndAdvertId(Long id, Long advertId);

    void deleteByAdvertId(Long id);
    @Query("select new Prettier_Homes.dto.response.AdvertCartRespons( a , MAX(i.url)) from AdvertsEntity a " +
            " join FavoritesEntity f on f.advert.id=a.id and  f.user.id =:userId " +
            " join ImagesEntity i ON a.id=i.advert.id " +
            "GROUP BY a.id" )
    Page<AdvertCartRespons> getFavotitesAdvertByUser(@Param("userId")Long userId, Pageable pageable);


    @Query("select new Prettier_Homes.dto.response.FavoritesAddRemoveResponse(f.id, f.user.id, f.advert.id ) from  FavoritesEntity f " +
            " where f.user.id =:id" )
    List<FavoritesAddRemoveResponse> getListForUserForCart(Long id);
}
