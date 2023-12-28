package Prettier_Homes.converter;

import Prettier_Homes.data.entity.FavoritesEntity;
import Prettier_Homes.dto.FavoritesDto;
import Prettier_Homes.dto.WordsDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface favoritesMapper extends HibernateAwareMapper {


    // NOT: entity de creationDate degiskeni varsa ilk method a belirtildigi gibi yazilacak.
    // creationDate yoksa silmeli => =>  @Mapping(target = "creationDate", ignore = true)

    // asagidaki diger methodlarin sadece Clas isimleri degistirelecek. WordsDto => UserDto olacak.

  @Mapping(target = "createAt", ignore = true)
    @InheritConfiguration
    void toEntityForUpdate(FavoritesDto model, @MappingTarget FavoritesEntity entity);


    FavoritesDto toDto(FavoritesEntity entity);

    List<FavoritesDto> toDto(List<FavoritesEntity> entities);


    FavoritesEntity toEntity(FavoritesDto model);

    List<FavoritesEntity> toEntity(List<FavoritesDto> models);

}
