package evidence.pojistenych.spring.data.repository;

import evidence.pojistenych.spring.data.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    /**
     *
     * @param email
     * @return
     */
    Optional<UserEntity> findByEmail(String email);

}
