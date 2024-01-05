package Prettier_Homes.data.repository;

import Prettier_Homes.data.entity.ImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<ImagesEntity, Long> {
    List<ImagesEntity> findByAdvertId(Long advert);

    void deleteByAdvertId(Long id);
}
