package Prettier_Homes.data.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "images")
public class ImagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    private Byte[] imgData;

    @NotNull
    private String name;

    @Column(nullable = false)
    private String type;

    @NotNull
    private Boolean featured;

    @ManyToOne
    private AdvertsEntity advert;

    private String url;


}
