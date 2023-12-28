package Prettier_Homes.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
}
