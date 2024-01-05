package Prettier_Homes.dto.requests;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserUpateForAdminManager {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Boolean builtIn;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;


    private String role;
}
