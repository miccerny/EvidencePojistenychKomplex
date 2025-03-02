package evidence.pojistenych.spring.data.repository;

import evidence.pojistenych.spring.data.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
