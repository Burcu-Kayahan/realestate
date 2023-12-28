package Prettier_Homes.data.entity;

import Prettier_Homes.data.enums.EnmAdvertStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "advert")
public class AdvertsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column( nullable = false)
    @Size(min = 5, max = 150, message = "Length must be between 5 and 150")
    private String title;
    @Size( max = 1500, message = "must be less than 300")
    private String description;
    @Size(min = 5, max = 200, message = "Length must be between 5 and 200")
    @Column( nullable = false)
    private String slug;
    @Column( nullable = false)
    private Double price;
    @Enumerated(EnumType.ORDINAL)
    private EnmAdvertStatus status;

    @Column(name = "built_in", nullable = false, updatable = false, insertable = false, columnDefinition = "boolean default true")
    private Boolean builtIn;

    @Column( nullable = false,columnDefinition = "boolean default true")
    private Boolean isActive;
   //false:Not published true:published(for users)

    @Column(name = "view_count", nullable = false,columnDefinition = "integer default 0")
    private Integer viewCount;
    //the visit count of detail page of an advert

    private String location;
    //google embed code

    @ManyToOne
    @JoinColumn(name = "advert_types_id",  nullable = false)
    private AdvertsTypeEntity advertType;



    @ManyToOne
    @JoinColumn(name = "countries_id",  nullable = false)
    private CountriesEntity country;


    @ManyToOne
    @JoinColumn(name = "cities_id", nullable = false)
    private CitiesEntity city;
    @ManyToOne
    @JoinColumn(name = "districts_id",  nullable = false)
    private DistrictsEntity district;

    @ManyToOne
    @JoinColumn(name = "user_id",  nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoriesEntity category;

    @Column(name = "create_at", nullable = false)//ex:1990-10-25T10:35:25Z
    private LocalDateTime createAt;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;


    @OneToMany(mappedBy = "advert")
    private List<ImagesEntity> images;

    @OneToMany(mappedBy = "advert")
    private List<FavoritesEntity> favory;

    @OneToMany(mappedBy = "advert")
    private List<LogsEntity> logs;


    @OneToMany(mappedBy = "advert")
    private List<TourRequestEntity> tourReq;

    @OneToMany(mappedBy = "advert")
    private List<CategoryPropertiesValuesEntity> values;
    public AdvertsEntity setCreateAt (){
        this.createAt=LocalDateTime.now();

    return this;}

    public AdvertsEntity(Long id){
        this.id=id;
    }

}
