package Prettier_Homes.converter;

import Prettier_Homes.data.entity.TourRequestEntity;
import Prettier_Homes.data.enums.EnmTourReqStatus;
import Prettier_Homes.dto.TourRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TourRequestMapper extends HibernateAwareMapper {

    @Named("convertStatus")
    static String convertStatus(EnmTourReqStatus status) {
        return status.name();
    }

    @Named("convertStatus")
    static EnmTourReqStatus convertStatus(String status) {
        return EnmTourReqStatus.valueOf(status);
    }
    @Mapping(target = "status", qualifiedByName = "convertStatus")
   @Mapping(target = "createAt", ignore = true)
    void toEntityForUpdate(TourRequestDto model, @MappingTarget TourRequestEntity user);

    @Mapping(target = "status", qualifiedByName = "convertStatus")
    TourRequestDto toDto(TourRequestEntity brand);

    List<TourRequestDto> toDto(List<TourRequestEntity> brand);

    @Mapping(target = "status", qualifiedByName = "convertStatus")
    TourRequestEntity toEntity(TourRequestDto model);

    List<TourRequestEntity> toEntity(List<TourRequestDto> models);

}
