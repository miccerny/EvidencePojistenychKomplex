package evidence.pojistenych.spring.models.dto.mappers;


import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = InsuranceMapper.class)
public interface InsuredPersonMapper {

    @Mapping(source = "insuranceEntities", target = "insuranceEntities")
    InsuredPersonDTO toDTO(InsuredPersonEntity source);

    @Mapping(target = "insuranceEntities", source = "insuranceEntities")
    InsuredPersonEntity toEntity(InsuredPersonDTO source);

    void updateInsuredPersonDTO(InsuredPersonDTO source, @MappingTarget InsuredPersonDTO target);
    void updateInsuredPersonEntity(InsuredPersonDTO source, @MappingTarget InsuredPersonEntity target);
}
