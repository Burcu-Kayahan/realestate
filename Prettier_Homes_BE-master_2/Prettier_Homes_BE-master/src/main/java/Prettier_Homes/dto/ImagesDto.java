package Prettier_Homes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
public class ImagesDto {


    private Long id;

    private Byte[] imgData;

    private String name;

    private  String type;

    private Boolean featured;

    private AdvertsDto advert;

    private String url;


}
