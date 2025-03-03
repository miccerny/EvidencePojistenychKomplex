package evidence.pojistenych.spring.data.repository;

import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;

import java.util.Optional;

public interface InsuredPersonRepository {

    Optional<InsuredPersonEntity> findByUserAccount(InsuredPersonEntity insuredPersonEntity);

}
