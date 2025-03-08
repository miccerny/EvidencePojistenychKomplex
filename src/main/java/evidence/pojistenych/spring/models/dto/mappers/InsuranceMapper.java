package evidence.pojistenych.spring.models.dto.mappers;


import evidence.pojistenych.spring.data.entities.InsuranceEntity;
import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.data.entities.UserEntity;
import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import evidence.pojistenych.spring.models.dto.UserDTO;
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

        void updateInsuranceRecordDTO(InsuranceRecordDTO source, @MappingTarget InsuranceRecordDTO target);
        void updateInsuranceEntity(InsuranceRecordDTO source, @MappingTarget InsuranceEntity target);


}
