package Prettier_Homes.dto;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class ContactsDto {


    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String message;

    private LocalDateTime createAt;

}
