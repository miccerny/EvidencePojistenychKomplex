package evidence.pojistenych.spring.models.dto.mappers;


import evidence.pojistenych.spring.data.entities.InsuranceEntity;
import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

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
        InsuranceEntity toEntity(InsuranceRecordDTO source);

        InsuranceRecordDTO toDTO(InsuranceEntity source);

        InsuredPersonEntity toEntity(InsuredPersonDTO source);

        InsuredPersonDTO toDTO(InsuredPersonEntity source);

        void updateInsuranceRecordDTO(InsuranceRecordDTO source, @MappingTarget InsuranceRecordDTO target);
        void updateInsuranceEntity(InsuranceRecordDTO source, @MappingTarget InsuranceEntity target);

        void updateInsuredPersonDTO(InsuredPersonDTO source, @MappingTarget InsuredPersonDTO target);
        void updateInsuredPersonEntity(InsuredPersonDTO source, @MappingTarget InsuredPersonEntity target);
}
