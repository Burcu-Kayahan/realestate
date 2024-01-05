package Prettier_Homes.dto.requests;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResetPasswordRequest {

    private String email;
    private String code;
    private String password;
    private String newPassword;
}
