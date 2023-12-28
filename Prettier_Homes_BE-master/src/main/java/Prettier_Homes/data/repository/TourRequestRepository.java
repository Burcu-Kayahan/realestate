package Prettier_Homes.data.repository;

import Prettier_Homes.data.entity.TourRequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TourRequestRepository extends JpaRepository<TourRequestEntity, Long> {



    void deleteByAdvertId(Long id);

    Page<TourRequestEntity> findByAdvertTitleOrGuestUserEmailOrOwnerUserEmail(String search, String search1, String search2, Pageable pageable);




   // TourRequestEntity findByGuestUser_Id(Long userId);
}
