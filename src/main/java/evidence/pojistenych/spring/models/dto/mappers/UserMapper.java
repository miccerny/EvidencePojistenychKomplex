package evidence.pojistenych.spring.models.dto.mappers;


import evidence.pojistenych.spring.data.entities.UserEntity;
import evidence.pojistenych.spring.models.dto.UserDTO;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitou uživatele a jeho DTO objekty.
 * Usnadňuje práci s uživatelskými daty v aplikaci.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Převede UserDTO na entitu UserEntity.
     *
     * @param source DTO uživatele, který se převádí
     * @return entita uživatele
     */
    UserEntity toEntity(UserDTO source);

    /**
     * Převede UserEntity na DTO UserDTO.
     *
     * @param source entita uživatele, která se převádí
     * @return DTO uživatele
     */
    UserDTO toDTO(UserEntity source);
}
