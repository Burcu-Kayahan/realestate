package Prettier_Homes.dto.response;

import Prettier_Homes.dto.UserDto;
import lombok.Data;

@Data
public class AuthResponse {

	private String message;
	private Long userId;
	private String token;
	private String refreshToken;
	private Boolean isAuthenticated;
	private UserDto user;
}
