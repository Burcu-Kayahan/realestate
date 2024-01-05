package Prettier_Homes.data.repository;

import Prettier_Homes.data.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {


    RoleEntity findByName(String user);
}
