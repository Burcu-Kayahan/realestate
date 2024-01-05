package Prettier_Homes.dto;


import lombok.Data;



@Data
public class CategoryPropertiesKeyDto {


    private Long id;

    private String name ;

    private Boolean builtIn;

    private CategoriesDto category;


}
