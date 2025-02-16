package evidence.pojistenych.spring.models.dto.mappers;


import evidence.pojistenych.spring.data.entities.InsuranceEntity;
import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import org.mapstruct.Mapper;

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
}
