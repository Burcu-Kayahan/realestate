package Prettier_Homes.data.repository;

import Prettier_Homes.data.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

	RefreshToken findByUserId(Long userId);
	
}
