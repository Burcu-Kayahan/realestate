package Prettier_Homes.data.entity;

import Prettier_Homes.data.enums.EnmLog;
import lombok.Data;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "logs")
public class LogsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @NotNull
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;



    @Enumerated(EnumType.ORDINAL)
    private EnmLog log;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private AdvertsEntity advert;







}
