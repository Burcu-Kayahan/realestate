package Prettier_Homes.data.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "favorites")
public class FavoritesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private AdvertsEntity advert;

    @NotNull
    private LocalDateTime createAt;




}
