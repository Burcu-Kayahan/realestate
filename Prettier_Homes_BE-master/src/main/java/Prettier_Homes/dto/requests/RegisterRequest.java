package Prettier_Homes.dto.requests;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RegisterRequest {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email
    private String email;
    @NotNull
    private String phone;
    @NotNull
    @Size(min = 8)
    private String password;

}
