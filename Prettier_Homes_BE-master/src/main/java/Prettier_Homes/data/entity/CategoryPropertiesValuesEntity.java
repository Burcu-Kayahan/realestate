package Prettier_Homes.data.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "category_properties_values")
public class CategoryPropertiesValuesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Size(max=100, message = "max= 100 karakter olmalidir")
    private String value;

    @ManyToOne
    private AdvertsEntity advert;

    @ManyToOne
    private CategoryPropertiesKeyEntity categoryPropertiesKey;


}
