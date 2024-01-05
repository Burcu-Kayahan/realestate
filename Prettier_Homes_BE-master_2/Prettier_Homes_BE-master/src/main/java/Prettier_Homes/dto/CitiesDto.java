package Prettier_Homes.dto;

import lombok.Data;

import javax.persistence.*;


@Data
public class CitiesDto {


    private Long id;

    private String name;

    private CountriesDto country;




}
