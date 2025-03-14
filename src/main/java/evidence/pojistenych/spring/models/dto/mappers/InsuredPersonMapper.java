package evidence.pojistenych.spring.models.dto.mappers;


import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 *
 */
@Mapper(componentModel = "spring", uses = InsuranceMapper.class)
public interface InsuredPersonMapper {

    /**
     *
     * @param source
     * @return
     */
    @Mapping(source = "insuranceEntities", target = "insuranceEntities")
    InsuredPersonDTO toDTO(InsuredPersonEntity source);

    /**
     *
     * @param source
     * @return
     */
    @Mapping(target = "insuranceEntities", source = "insuranceEntities")
    InsuredPersonEntity toEntity(InsuredPersonDTO source);

    /**
     *
     * @param source
     * @param target
     */
    void updateInsuredPersonDTO(InsuredPersonDTO source, @MappingTarget InsuredPersonDTO target);
    void updateInsuredPersonEntity(InsuredPersonDTO source, @MappingTarget InsuredPersonEntity target);
}
