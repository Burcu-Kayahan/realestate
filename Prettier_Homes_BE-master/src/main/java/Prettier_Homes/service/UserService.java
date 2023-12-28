package Prettier_Homes.service;

import Prettier_Homes.data.entity.UserEntity;
import Prettier_Homes.dto.UserDto;
import Prettier_Homes.dto.requests.ResetPasswordRequest;
import Prettier_Homes.dto.requests.UserUpateForAdminManager;
import Prettier_Homes.dto.response.DeleteRespons;
import Prettier_Homes.dto.response.UserAllinfoResponseForAdmin;
import Prettier_Homes.security.JwtUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserDto> getByAuth(Long id);

    ResponseEntity<UserDto> update(Long id, UserDto dto, JwtUserDetails jwtUserDetails);

    UserEntity getOneUserByEmail(String email);

    ResponseEntity<String> updatePassword(Long id, ResetPasswordRequest password);

    ResponseEntity<DeleteRespons> delete(Long id);

    ResponseEntity<Page<UserUpateForAdminManager>> getUsersByFilter(int page, int size, String sort, Sort.Direction direction, String search);

    ResponseEntity<UserAllinfoResponseForAdmin> getUserAllInfos(Long userId);

    void resetPassword(ResetPasswordRequest request);

    ResponseEntity<UserUpateForAdminManager> updateUserByAdminAndManager(Long userId, UserUpateForAdminManager user, JwtUserDetails jwtUserDetails);

    ResponseEntity<DeleteRespons> deleteUserByAdmin(Long userId,  JwtUserDetails jwtUserDetails);

    ResponseEntity<Page<UserEntity>> getAll(int page, int size, String sort, Sort.Direction direction, String search);
}
