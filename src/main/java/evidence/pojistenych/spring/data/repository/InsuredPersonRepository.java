package evidence.pojistenych.spring.data.repository;

import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuredPersonRepository extends JpaRepository<InsuredPersonEntity, Long> {

    InsuredPersonEntity getById (Long id);
}
