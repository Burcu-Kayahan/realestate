package Prettier_Homes.data.repository;

import Prettier_Homes.data.entity.WordsEntity;
import Prettier_Homes.dto.WordsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WordsRepository extends JpaRepository<WordsEntity, Long> {

    @Query("SELECT new Prettier_Homes.dto.WordsDto( w ) from WordsEntity w")
    Page<WordsDto> getAll(Pageable pageable);


    // burada entity olarak gelen sonuclari jpql sorgusu ile dto ya cevirdik
    // entity den dto ya aktarmak icin wordsdto clasinda bir constructur yazdik
}
