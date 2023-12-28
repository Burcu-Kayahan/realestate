package Prettier_Homes.data.repository;

import Prettier_Homes.data.entity.CategoryPropertiesKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryPropertiesKeyRepository extends JpaRepository <CategoryPropertiesKeyEntity, Long>{


    List<CategoryPropertiesKeyEntity> findByCategoryId(Long id);
}
