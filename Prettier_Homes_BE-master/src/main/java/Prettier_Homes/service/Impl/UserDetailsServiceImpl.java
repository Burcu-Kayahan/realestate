package Prettier_Homes.service.Impl;

import Prettier_Homes.data.entity.UserEntity;
import Prettier_Homes.data.repository.UserRepository;
import Prettier_Homes.security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


	@Autowired
	private UserRepository userRepository;
	
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByEmail(email);
		return JwtUserDetails.create(user);
	}
	
	public UserDetails loadUserById(Long id) {
		UserEntity user = userRepository.findById(id).get();
		return JwtUserDetails.create(user); 
	}



}
