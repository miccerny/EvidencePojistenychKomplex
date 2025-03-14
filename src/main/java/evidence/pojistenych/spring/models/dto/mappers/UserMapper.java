package evidence.pojistenych.spring.models.dto.mappers;


import evidence.pojistenych.spring.data.entities.UserEntity;
import evidence.pojistenych.spring.models.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserDTO source);

    UserDTO toDTO(UserEntity source);
}
