package Prettier_Homes.converter;

import Prettier_Homes.data.entity.LogsEntity;

import org.mapstruct.*;

import Prettier_Homes.data.enums.EnmLog;
import Prettier_Homes.dto.LogsDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LogsMapper extends HibernateAwareMapper {
   


  



    @Named("convertStatus")
    static String convertStatus(EnmLog status) {
        return status.getDescription();
    }

    @Named("convertStatus")
    static EnmLog convertStatus(String status) {
        return EnmLog.valueOf(status);
    }

   @Mapping(target = "createAt", ignore = true)
    @InheritConfiguration
    void toEntityForUpdate(LogsDto model, @MappingTarget LogsEntity user);

    @Mapping(target = "log",qualifiedByName = "convertStatus")
    LogsDto toDto(LogsEntity brand);

    List<LogsDto> toDto(List<LogsEntity> brand);

    @Mapping(target = "log",qualifiedByName = "convertStatus")
    LogsEntity toEntity(LogsDto model);

    List<LogsEntity> toEntity(List<LogsDto> models);


}
