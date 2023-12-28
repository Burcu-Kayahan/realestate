package Prettier_Homes.data.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "\"users\"")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;
    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;
    @NotNull
    @Size(min = 10, max = 80)
    @Column(unique = true)
    @Email
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private String passwordHash; // hashed pasword
    private String resetPasswordCode; // hashed nullable
    @Column(name = "built_in",  updatable = false,  columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean builtIn;
    @NotNull
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Boolean active;
    private String activeCode;

    //UserEntity

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private RoleEntity roles;


    public UserEntity setId(Long id){
        this.id=id;
        return this;
    }


}
