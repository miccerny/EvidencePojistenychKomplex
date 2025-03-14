package evidence.pojistenych.spring.models.dto.mappers;


import evidence.pojistenych.spring.data.entities.InsuranceEntity;
import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.data.entities.UserEntity;
import evidence.pojistenych.spring.data.repository.InsuredPersonRepository;
import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import evidence.pojistenych.spring.models.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

/**
 *
 */
@Mapper(componentModel = "spring")
public interface InsuranceMapper {


        /**
         *
         * @param source
         * @return
         */
        @Mapping(source = "insuredPersonId", target = "insuredPerson", qualifiedByName = "mapInsuredPerson")
        InsuranceEntity toEntity(InsuranceRecordDTO source);

        /**
         *
         * @param source
         * @return
         */
        @Mapping(source = "insuredPerson.id", target = "insuredPersonId")
        InsuranceRecordDTO toDTO(InsuranceEntity source);

        /**
         *
         * @param insuredPersonId
         * @return
         */
        @Named("mapInsuredPerson")
        default InsuredPersonEntity mapInsuredPerson(Long insuredPersonId) {
                if (insuredPersonId == null) {
                        return null;
                }
                InsuredPersonEntity insuredPerson = new InsuredPersonEntity();
                insuredPerson.setId(insuredPersonId); // Nastavení pouze ID, bez načítání z DB
                return insuredPerson;
        }

        /**
         *
         * @param source
         * @param target
         */
        void updateInsuranceRecordDTO(InsuranceRecordDTO source, @MappingTarget InsuranceRecordDTO target);
        void updateInsuranceEntity(InsuranceRecordDTO source, @MappingTarget InsuranceEntity target);


}
