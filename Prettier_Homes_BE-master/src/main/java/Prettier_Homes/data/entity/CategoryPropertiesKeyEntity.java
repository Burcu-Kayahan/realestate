package Prettier_Homes.data.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "category_properties_keys")
public class CategoryPropertiesKeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Size(min = 2, max = 80, message = "min=2 max=80 karakter olmalidir")
    private String name ;

    @Column(name = "built_in",  updatable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean builtIn;

    @ManyToOne()
    private CategoriesEntity category;



}
