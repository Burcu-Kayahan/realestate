package Prettier_Homes.data.repository;


import Prettier_Homes.data.entity.CategoryPropertiesValuesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryPropertiesValueRepository extends JpaRepository <CategoryPropertiesValuesEntity, Long>{


    List<CategoryPropertiesValuesEntity> findByAdvertId(Long id);

    void deleteByAdvertId(Long id);
}
