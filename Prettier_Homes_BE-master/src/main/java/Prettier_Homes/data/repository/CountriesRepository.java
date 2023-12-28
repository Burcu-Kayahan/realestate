package Prettier_Homes.data.repository;

import Prettier_Homes.data.entity.CountriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountriesRepository extends JpaRepository<CountriesEntity, Long> {


}
