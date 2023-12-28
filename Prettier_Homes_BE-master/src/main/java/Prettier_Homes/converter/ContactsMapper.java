package Prettier_Homes.converter;

import Prettier_Homes.data.entity.ContactsEntity;
import Prettier_Homes.dto.ContactsDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactsMapper extends HibernateAwareMapper {


    // NOT: entity de creationDate degiskeni varsa ilk method a belirtildigi gibi yazilacak.
    // creationDate yoksa silmeli => =>  @Mapping(target = "creationDate", ignore = true)

    // asagidaki diger methodlarin sadece Clas isimleri degistirelecek. WordsDto => UserDto olacak.

     @Mapping(target = "createAt", ignore = true)
     @InheritConfiguration
     void toEntityForUpdate(ContactsDto dto, @MappingTarget ContactsEntity entity);


     ContactsDto toDto(ContactsEntity entity);

        List<ContactsDto> toDto(List<ContactsEntity> entities);

       ContactsEntity toEntity(ContactsDto dto);

      List<ContactsEntity> toEntity(List<ContactsDto> models);

}
