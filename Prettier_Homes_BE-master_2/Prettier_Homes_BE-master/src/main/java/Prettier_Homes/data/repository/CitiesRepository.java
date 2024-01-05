package Prettier_Homes.data.repository;

import Prettier_Homes.data.entity.CitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitiesRepository extends JpaRepository<CitiesEntity,Long> {
    void deleteByCountryId(Long id);

    List<CitiesEntity> findByCountryId(Long id);
}
