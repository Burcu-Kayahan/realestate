package Prettier_Homes.data.repository;

import Prettier_Homes.data.entity.CategoriesEntity;
import Prettier_Homes.dto.CategoriesDto;
import Prettier_Homes.dto.WordsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<CategoriesEntity,Long> {
    Page<CategoriesEntity> findByTitle(String search, Pageable pageable);
//    @Query("SELECT new Prettier_Homes.dto.CategoriesDto( w ) from CategoriesEntity w")
//    Page<CategoriesDto> getAll(Pageable pageable);

}