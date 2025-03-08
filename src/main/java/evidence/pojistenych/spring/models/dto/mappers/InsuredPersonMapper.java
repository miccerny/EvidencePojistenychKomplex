package evidence.pojistenych.spring.models.dto.mappers;


import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InsuredPersonMapper {

    @Mapping(source = "userEntity.email", target = "email")  // Tady se používá cesta k 'email' v 'userEntity'
    InsuredPersonDTO toDTO(InsuredPersonEntity source);

    // Mapování mezi InsuredPersonDTO a InsuredPersonEntity
    @Mapping(source = "email", target = "userEntity.email")  // Mapování emailu z DTO do entity
    InsuredPersonEntity toEntity(InsuredPersonDTO source);

    void updateInsuredPersonDTO(InsuredPersonDTO source, @MappingTarget InsuredPersonDTO target);
    void updateInsuredPersonEntity(InsuredPersonDTO source, @MappingTarget InsuredPersonEntity target);
}
