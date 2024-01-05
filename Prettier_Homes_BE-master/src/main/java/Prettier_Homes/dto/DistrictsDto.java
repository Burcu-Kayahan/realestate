package Prettier_Homes.dto;

import lombok.Data;

import javax.persistence.*;


@Data
public class DistrictsDto {

    private Long id;

    private String name;

    private CitiesDto cities;

}
