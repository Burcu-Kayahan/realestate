package Prettier_Homes.data.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "contacts")
public class ContactsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

   @NotNull
   @Column(name= "first_name")
    private String firstName;

    @NotNull
    @Column(name="last_name")
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String message;
     @NotNull
     @Column(name ="create_at")
    private LocalDateTime createAt;




}
