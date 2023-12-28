package Prettier_Homes.dto;


import Prettier_Homes.data.entity.WordsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
 public class WordsDto {

    private Long id;

    private String translate;

    private Long language;

    private LocalDateTime creationDate;

    private String deneme;


    public WordsDto(WordsEntity entity){
        this.id=entity.getId();
        this.creationDate=entity.getCreationDate();
        this.language= entity.getLanguage();
        this.translate=entity.getTranslate();
    }

}
