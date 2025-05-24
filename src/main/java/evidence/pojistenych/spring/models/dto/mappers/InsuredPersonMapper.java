package evidence.pojistenych.spring.models.dto.mappers;


import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper pro převod mezi entitou pojištěnce a jeho DTO objekty.
 * Používá se pro snadnější práci s daty pojištěnce v aplikaci.
 */
@Mapper(componentModel = "spring", uses = InsuranceMapper.class)
public interface InsuredPersonMapper {

    /**
     * Převede entitu InsuredPersonEntity na DTO objekt InsuredPersonDTO.
     *
     * @param source entita pojištěnce
     * @return DTO objekt pojištěnce s mapovanými pojistkami
     */
    @Mapping(source = "insuranceEntities", target = "insuranceEntities")
    InsuredPersonDTO toDTO(InsuredPersonEntity source);

    /**
     * Převede DTO objekt InsuredPersonDTO na entitu InsuredPersonEntity.
     *
     * @param source DTO objekt pojištěnce
     * @return entita pojištěnce s mapovanými pojistkami
     */
    @Mapping(target = "insuranceEntities", source = "insuranceEntities")
    InsuredPersonEntity toEntity(InsuredPersonDTO source);

    /**
     * Aktualizuje cílový InsuredPersonDTO objekt daty ze zdrojového DTO.
     *
     * @param source zdrojový DTO objekt s novými hodnotami
     * @param target cílový DTO objekt, který se má aktualizovat
     */
    void updateInsuredPersonDTO(InsuredPersonDTO source, @MappingTarget InsuredPersonDTO target);

    /**
     * Aktualizuje cílovou InsuredPersonEntity entitu daty ze zdrojového DTO.
     *
     * @param source zdrojový DTO objekt s novými hodnotami
     * @param target cílová entita, která se má aktualizovat
     */
    void updateInsuredPersonEntity(InsuredPersonDTO source, @MappingTarget InsuredPersonEntity target);
}
