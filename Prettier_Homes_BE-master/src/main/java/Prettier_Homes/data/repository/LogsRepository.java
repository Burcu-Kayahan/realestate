package Prettier_Homes.data.repository;


import Prettier_Homes.data.entity.LogsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;





import java.util.List;

@Repository
public interface LogsRepository extends JpaRepository<LogsEntity, Long> {
    List<LogsEntity> findByUserId(Long userId);

    void deleteByAdvertId(Long id);
}
