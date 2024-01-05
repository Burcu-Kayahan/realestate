package Prettier_Homes.data.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "cities")
public class CitiesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;


   @NotNull
   @ManyToOne
   private CountriesEntity country;


    @OneToMany(mappedBy = "cities")
    private List<DistrictsEntity> district;

}
