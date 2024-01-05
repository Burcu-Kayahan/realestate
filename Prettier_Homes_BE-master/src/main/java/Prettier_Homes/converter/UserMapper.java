package Prettier_Homes.converter;

import Prettier_Homes.data.entity.UserEntity;
import Prettier_Homes.dto.UserDto;
import Prettier_Homes.dto.requests.UserUpateForAdminManager;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends HibernateAwareMapper {



    @Mapping(target = "createAt", ignore = true)
    @InheritConfiguration
    void toEntityForUpdate(UserDto model, @MappingTarget UserEntity user);

    @Mapping(target = "createAt", ignore = true)
    @InheritConfiguration
    void toEntityForUpdateForAdminAndManager(UserUpateForAdminManager model, @MappingTarget UserEntity entity);

    @Mapping(target = "role", source = "roles.name")
    UserDto toDto(UserEntity entity);

    List<UserDto> toDto(List<UserEntity> entity);


    UserEntity toEntity(UserDto model);

    List<UserEntity> toEntity(List<UserDto> models);
    @Mapping(target = "role", source = "roles.name")
    UserUpateForAdminManager toDtoForAdmin(UserEntity entity);

    List<UserUpateForAdminManager> toDtoForAdmin(List<UserEntity> entities);

    UserEntity toEntityForAdminManager(UserUpateForAdminManager model);

    List<UserEntity> toEntityForAdminManager(List<UserUpateForAdminManager> models);


}
