package evidence.pojistenych.spring.data.repository;

import evidence.pojistenych.spring.data.entities.InsuranceEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InsuranceRepository extends CrudRepository<InsuranceEntity, Long> {


    List<InsuranceEntity> findByInsuredPerson(InsuranceEntity insuranceEntity);
}
