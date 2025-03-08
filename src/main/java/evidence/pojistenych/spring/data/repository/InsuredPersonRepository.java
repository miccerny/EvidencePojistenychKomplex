package evidence.pojistenych.spring.data.repository;

import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InsuredPersonRepository extends JpaRepository<InsuredPersonEntity, Long> {

    Optional<InsuredPersonEntity> findByUserEntity_UserId(long userId);

}
