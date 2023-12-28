package Prettier_Homes.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "countries")
public class CountriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "country")
    private List<CitiesEntity> cities;


}
