package Prettier_Homes.data.entity;

import Prettier_Homes.data.enums.EnmTourReqStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tour_request")
public class TourRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private UserEntity guestUser;
    
    @Enumerated(EnumType.ORDINAL)
    private EnmTourReqStatus status;

    private LocalDateTime createAt;

    @ManyToOne
    private AdvertsEntity advert;

    private LocalDateTime tourTime;
    private LocalDateTime updateAt;

    @OneToOne
    private UserEntity ownerUser;

    //yazilacak fieldlar var!(advert,tourTime,updateAt,ownerUser,guestUser)



}
