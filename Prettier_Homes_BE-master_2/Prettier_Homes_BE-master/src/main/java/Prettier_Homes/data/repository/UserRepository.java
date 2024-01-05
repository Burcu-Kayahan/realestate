package Prettier_Homes.data.repository;

import Prettier_Homes.data.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    UserEntity findByEmail(String email);

    Page<UserEntity> findByFirstNameContainingOrLastNameContainingOrEmailContainingOrPhoneContaining(String search, String search1, String search2, String search3, Pageable pageable);

    Optional<UserEntity> findByActiveCode(String encode);

    Optional<UserEntity> findByResetPasswordCode(String encode);
}
