package Prettier_Homes.converter;

import Prettier_Homes.data.entity.ImagesEntity;
import Prettier_Homes.data.entity.WordsEntity;
import Prettier_Homes.dto.ImagesDto;
import Prettier_Homes.dto.WordsDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImagesMapper extends HibernateAwareMapper {


    // NOT: entity de creationDate degiskeni varsa ilk method a belirtildigi gibi yazilacak.
    // creationDate yoksa silmeli => =>  @Mapping(target = "creationDate", ignore = true)

    // asagidaki diger methodlarin sadece Clas isimleri degistirelecek. WordsDto => UserDto olacak.


    @InheritConfiguration
    void toEntityForUpdate(ImagesDto model, @MappingTarget ImagesEntity user);


    ImagesDto toDto(ImagesEntity entity);

    List<ImagesDto> toDto(List<ImagesEntity> entities);

    ImagesEntity toEntity(ImagesDto dto);

    List<ImagesEntity> toEntity(List<ImagesDto> dtos);

}
