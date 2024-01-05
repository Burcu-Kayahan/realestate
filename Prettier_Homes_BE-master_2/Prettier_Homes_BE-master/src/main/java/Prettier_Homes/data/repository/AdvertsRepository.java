package Prettier_Homes.data.repository;

import Prettier_Homes.data.entity.AdvertsEntity;
import Prettier_Homes.dto.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertsRepository extends JpaRepository<AdvertsEntity, Long> {


    List<AdvertsEntity> findByUserId(Long userId);


//    @Query("select new Prettier_Homes.dto.response.AdvertCartRespons( a, MAX(i.url)  ) " +
//            " from AdvertsEntity a " +
//            " left join ImagesEntity i ON a.id = i.advert.id " + //images table da null olanlarida cekebilmek icin left join kullandik
//            " where a.title like %:search% or a.description like %:search% " +
//            " and (COALESCE(:category, CAST(0 AS long)) = 0 OR a.category.id = :category) " +
//            " and (COALESCE(:advertType, CAST(0 AS long)) = 0 OR a.advertType.id = :advertType) " +
//            " and (COALESCE(:priceStart,CAST(0 AS double)) = 0 OR a.price >= :priceStart) " +
//            " and (COALESCE(:priceEnd,CAST(0 AS double)) = 0 OR a.price <= :priceEnd) " +
//            " GROUP BY a.id ")
//    Page<AdvertCartRespons> getFilterByAdmin(@Param("search")String search, @Param("category") Long category,
//                                             @Param("advertType")Long advertType, @Param("priceStart")Double priceStart,
//                                             @Param("priceEnd") Double priceEnd, Pageable pageable);

    @Query("SELECT new Prettier_Homes.dto.response.AdvertCartRespons(a, MAX(i.url)) " +
            "FROM AdvertsEntity a " +
            "left JOIN ImagesEntity i ON a.id = i.advert.id " +
            "WHERE (a.title LIKE %:search% OR a.description LIKE %:search%) " +
            "and (:category IS NULL or a.category.id = :category )" +
            "and (:advertType is null or a.advertType.id = :advertType) " +
            "and (:priceStart is null or a.price >= :priceStart) " +
            "and (:priceEnd is null or a.price <= :priceEnd) " +
            "and (:country is null or a.country.id = :country) " +
            "and (:city is null or a.city.id = :city) " +
            "and (:district is null or a.district.id = :district) " +
            "GROUP BY a.id")
    Page<AdvertCartRespons> getFilterByAdmin(@Param("search") String search,
            @Param("category") Long category,
            @Param("advertType") Long advertType,
            @Param("priceStart") Double priceStart,
            @Param("priceEnd") Double priceEnd,
                                             @Param("country")Long country,
                                             @Param("city")Long city,
                                             @Param("district")Long district,
            Pageable pageable
    );


    @Query("select new Prettier_Homes.dto.response.AdvertsCitiesResponse(c.id,c.name,count(a.id) as amount)from CitiesEntity c " +
            "  join AdvertsEntity a ON c.id=a.city.id " +
            " GROUP BY c.name " +
            " order by amount desc")
    List<AdvertsCitiesResponse> getGroupAdvertCity(Pageable pageable);

    @Query("select new Prettier_Homes.dto.response.AdvertsCountriesResponse(c.id,c.name,count(a.id) as amount)from CountriesEntity c " +
            "  join AdvertsEntity a ON c.id=a.country.id " +
            " GROUP BY c.name " +
            "order by amount desc")
    List<AdvertsCountriesResponse> getGroupAdvertCountry(Pageable pageable);


    @Query("select new Prettier_Homes.dto.response.AdvertCartRespons( a , MAX(i.url)) from AdvertsEntity a " +
            "join ImagesEntity i ON a.id=i.advert.id " +
            "where (:search is null or a.title like %:search%) " +
            "and a.user.id = :id " +
            "GROUP BY a.id")
    Page<AdvertCartRespons> getFilterByAuth(String search, Pageable pageable, Long id);


    @Query("select new Prettier_Homes.dto.response.AdvertDetailBySlugResponse( a ,MAX(i.url) as url)from AdvertsEntity a " +
            "  join ImagesEntity i ON a.id=i.advert.id " +
            " where a.slug=:slug " +
            " GROUP BY a.id , i.url")
    AdvertDetailBySlugResponse getAdvertDetailBySlug(String slug);

//    @Query("select new Prettier_Homes.dto.response.AdvertCartRespons( a ,MAX(i.imgData) as img )from AdvertsEntity a " +
//            "  join ImagesEntity i ON a.id=i.advert.id " +
//            " where (a.title like %:search% or a.description like %:search% ) " +
//            " and (COALESCE(:category, 0) = 0 OR (a.category.id = :category)) " +
//            " and (COALESCE(:advertType, 0) = 0 OR a.advertType.id = :advertType) " +
//            " and (COALESCE(:priceStart,0) = 0 OR a.price >= :priceStart) " +
//            " and (COALESCE(:priceEnd,0) = 0 OR a.price <= :priceEnd) " +
//            " and (COALESCE(:status,0) = 0 OR a.status = :status) " +
//            " GROUP BY a.id ")
//    Page<AdvertCartRespons> getCartListFilter(String search, int category, int advertType, int priceStart, int priceEnd, int status, Pageable pageable);


    Optional<AdvertsEntity> findBySlug(String slug);


//    @Query("SELECT NEW Prettier_Homes.dto.response.AdvertCartRespons(a, MAX(i.url), a.viewCount + COALESCE(tr.visitcount, 0) AS popularity) " +
//            "FROM AdvertsEntity a " +
//            "JOIN ImagesEntity i ON a.id = i.advert.id " +
//            "LEFT JOIN (SELECT tr.advert.id, COUNT(tr) AS visitcount FROM TourRequestEntity tr GROUP BY tr.advert.id) tr ON a.id = tr.advert.id " +
//            "GROUP BY a.id, i.url, a.viewCount, tr.visitcount " +
//            "ORDER BY popularity DESC")
//    List<AdvertCartRespons> getPopulerList(Pageable pageable);
/*
    SELECT NEW Prettier_Homes.dto.response.AdvertCartRespons(
    a,
    MAX(i.url),
    a.viewCount + COALESCE(tr.visitcount, 0)
)
    FROM AdvertsEntity a
    JOIN ImagesEntity i ON a.id = i.advert.id
    LEFT JOIN (
            SELECT tr.advert.id, COUNT(tr) AS visitcount
    FROM TourRequestEntity tr
    GROUP BY tr.advert.id
) tr ON a.id = tr.advert.id
    GROUP BY a.id, i.url, a.viewCount, tr.visitcount
    ORDER BY a.viewCount + COALESCE(tr.visitcount, 0) DESC

*/
//    @Query("SELECT NEW Prettier_Homes.dto.response.AdvertCartRespons(a, MAX(i.url), a.viewCount + COALESCE(tr.visit count, 0) AS popularity) " +
//            "FROM AdvertsEntity a " +
//            "JOIN ImagesEntity i ON a.id = i.advert.id " +
//            "LEFT JOIN (SELECT tr.advert.id, COUNT(tr) AS visit count FROM TourRequestEntity tr GROUP BY tr.advert.id) tr ON a.id = tr.advert.id " +
//            "GROUP BY a.id, i.url, a.viewCount, tr.visit count " +
//            "ORDER BY popularity DESC")
//    List<AdvertCartRespons> getPopulerList(Pageable pageable);


    @Query("SELECT NEW Prettier_Homes.dto.response.AdvertCartRespons(a, MAX(i.url), (a.viewCount + count(tr.advert.id)) as popularites  )" +
            " FROM AdvertsEntity a " +
            " JOIN ImagesEntity i ON a.id = i.advert.id " +
            " left JOIN TourRequestEntity tr ON a.id = tr.advert.id " +
            " GROUP BY a.id, i.url " +
            " order by popularites DESC" )
    List<AdvertCartRespons> getPopulerList(Pageable pageable);

}












