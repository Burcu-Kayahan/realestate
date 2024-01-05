package Prettier_Homes.converter;

import Prettier_Homes.data.entity.RoleEntity;
import Prettier_Homes.data.entity.UserEntity;
import Prettier_Homes.dto.RoleDto;
import Prettier_Homes.dto.UserDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper extends HibernateAwareMapper {




    @InheritConfiguration
    void toEntityForUpdate(RoleDto model, @MappingTarget RoleEntity entity);


    RoleDto toDto(RoleEntity entity);

    List<RoleDto> toDto(List<RoleEntity> entities);


    RoleEntity toEntity(RoleDto dto);

    List<RoleEntity> toEntity(List<RoleDto> dto);

}
