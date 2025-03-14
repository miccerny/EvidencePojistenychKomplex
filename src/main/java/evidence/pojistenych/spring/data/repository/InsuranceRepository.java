package evidence.pojistenych.spring.data.repository;

import evidence.pojistenych.spring.data.entities.InsuranceEntity;
import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceRepository extends CrudRepository<InsuranceEntity, Long> {


    List<InsuranceEntity> findByInsuredPerson(InsuredPersonEntity insuredPerson);
}
