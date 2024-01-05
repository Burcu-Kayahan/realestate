package Prettier_Homes.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "words")
public class WordsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String translate;

    private Long language;

    @Column( nullable = false)
    private LocalDateTime creationDate;


    public WordsEntity setCreationdate(){
        this.creationDate=LocalDateTime.now();
        return this;
    }
}
