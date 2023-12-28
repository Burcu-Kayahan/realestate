package Prettier_Homes.data.repository;

import Prettier_Homes.data.entity.AdvertsTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertTypeRepository extends JpaRepository<AdvertsTypeEntity, Long> {


}
