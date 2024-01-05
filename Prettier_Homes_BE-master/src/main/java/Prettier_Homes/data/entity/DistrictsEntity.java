package Prettier_Homes.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "districts")
public class DistrictsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;


    @ManyToOne
    private CitiesEntity cities;


}
