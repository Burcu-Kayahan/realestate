package Prettier_Homes.data.repository;

import Prettier_Homes.data.entity.DistrictsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictsRepository extends JpaRepository<DistrictsEntity,Long> {

    List<DistrictsEntity> findByCitiesId(Long id);
}
