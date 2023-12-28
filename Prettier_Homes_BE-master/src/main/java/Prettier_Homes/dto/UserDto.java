package Prettier_Homes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;


@Data
@NoArgsConstructor
public class UserDto {


    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Boolean builtIn;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private String role;


//todo roller dto classlarinda string seklinde gelecek ve her bir user in tek bir rol u olacak.
}
