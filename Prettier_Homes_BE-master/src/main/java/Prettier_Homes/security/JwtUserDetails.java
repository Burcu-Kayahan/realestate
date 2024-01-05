package Prettier_Homes.security;

import Prettier_Homes.data.entity.RoleEntity;
import Prettier_Homes.data.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
public class JwtUserDetails implements UserDetails {

	public Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	private RoleEntity userRole;
	private Collection<? extends GrantedAuthority> authorities;
	
    private JwtUserDetails(RoleEntity roles, Long id, String firstName, String lastName, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
		this.lastName=lastName;
		this.email =email;
        this.authorities = authorities;
		this.password=password;
		this.userRole=roles;
    }

    public static JwtUserDetails create(UserEntity user) {
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
		if(user.getRoles()!=null){authoritiesList.add(new SimpleGrantedAuthority(user.getRoles().getName()));}
        return new JwtUserDetails(user.getRoles(),user.getId(), user.getFirstName(), user.getLastName(),user.getEmail(), user.getPasswordHash(),authoritiesList);
    }

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getEmail(){
		return email;
	}

	public Long getId(){
		return id;
	}

}
