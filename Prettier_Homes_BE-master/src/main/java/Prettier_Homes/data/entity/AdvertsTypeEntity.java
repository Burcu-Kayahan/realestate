package Prettier_Homes.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Data
@Table(name = "advert_types")
public class AdvertsTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Size(max=30, message = "Your title should be maximum 30 chars")
    private String title;


    public AdvertsTypeEntity(Long id){
        this.id=id;
    }




}
