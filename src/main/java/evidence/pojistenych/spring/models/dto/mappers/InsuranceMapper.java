package evidence.pojistenych.spring.models.dto.mappers;


import evidence.pojistenych.spring.data.entities.InsuranceEntity;
import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

/**
 * Mapper pro převod mezi entitou pojištění a DTO objekty.
 * Používá se pro snadnější práci s daty v aplikační vrstvě.
 */
@Mapper(componentModel = "spring")
public interface InsuranceMapper {

        /**
         * Převede objekt InsuranceRecordDTO na entitu InsuranceEntity.
         *
         * @param source DTO objekt s daty o pojištění
         * @return entita InsuranceEntity s namapovanými hodnotami
         */
        @Mapping(source = "insuredPersonId", target = "insuredPerson", qualifiedByName = "mapInsuredPerson")
        InsuranceEntity toEntity(InsuranceRecordDTO source);

        /**
         * Převede entitu InsuranceEntity na DTO objekt InsuranceRecordDTO.
         *
         * @param source entita pojištění
         * @return DTO objekt s daty pojištění
         */
        @Mapping(source = "insuredPerson.id", target = "insuredPersonId")
        InsuranceRecordDTO toDTO(InsuranceEntity source);

        /**
         * Pomocná metoda pro mapování ID pojištěnce na entitu InsuredPersonEntity.
         * Nastavuje pouze ID, bez načítání celé entity z databáze.
         *
         * @param insuredPersonId ID pojištěnce, které se má namapovat
         * @return entita InsuredPersonEntity s nastaveným ID nebo null, pokud je ID null
         */
        @Named("mapInsuredPerson")
        default InsuredPersonEntity mapInsuredPerson(Long insuredPersonId) {
                if (insuredPersonId == null) {
                        return null;
                }
                InsuredPersonEntity insuredPerson = new InsuredPersonEntity();
                insuredPerson.setId(insuredPersonId);
                return insuredPerson;
        }

        /**
         * Aktualizuje hodnoty cílového DTO objektu na základě dat ze zdrojového DTO.
         *
         * @param source zdrojový DTO objekt s novými daty
         * @param target cílový DTO objekt, který se má aktualizovat
         */
        void updateInsuranceRecordDTO(InsuranceRecordDTO source, @MappingTarget InsuranceRecordDTO target);

        /**
         * Aktualizuje hodnoty cílové entity na základě dat ze zdrojového DTO.
         *
         * @param source zdrojový DTO objekt s novými daty
         * @param target cílová entita, která se má aktualizovat
         */
        void updateInsuranceEntity(InsuranceRecordDTO source, @MappingTarget InsuranceEntity target);


}
