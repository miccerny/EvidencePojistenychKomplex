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


        // Mapování mezi InsuredPersonEntity a InsuredPersonDTO
        @Mapping(source = "userEntity.email", target = "email")  // Tady se používá cesta k 'email' v 'userEntity'
        InsuredPersonDTO toDTO(InsuredPersonEntity source);

        // Mapování mezi InsuredPersonDTO a InsuredPersonEntity
        @Mapping(source = "email", target = "userEntity.email")  // Mapování emailu z DTO do entity
        InsuredPersonEntity toEntity(InsuredPersonDTO source);

        /**
         * Mapování mezi UserDTO a UserEntity
         * @param source
         * @return
         */
        // Mapování emailu z UserDTO do UserEntity
        UserEntity toEntity(UserDTO source);
        // Mapování emailu z UserDTO do UserEntity
        UserDTO toDTO(UserEntity source);

        void updateInsuranceRecordDTO(InsuranceRecordDTO source, @MappingTarget InsuranceRecordDTO target);
        void updateInsuranceEntity(InsuranceRecordDTO source, @MappingTarget InsuranceEntity target);

        void updateInsuredPersonDTO(InsuredPersonDTO source, @MappingTarget InsuredPersonDTO target);
        void updateInsuredPersonEntity(InsuredPersonDTO source, @MappingTarget InsuredPersonEntity target);
}
