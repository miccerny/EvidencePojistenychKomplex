package evidence.pojistenych.spring.data.repository;

import evidence.pojistenych.spring.data.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repozitář pro práci s uživateli v databázi.
 * Poskytuje základní CRUD operace díky rozhraní CrudRepository.
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    /**
     * Najde uživatele podle jeho emailu.
     *
     * @param email  - email uživatele, kterého hledáme
     * @return -  Optional, který může obsahovat uživatele s daným emailem nebo být prázdný, pokud uživatel neexistuje
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * Zjistí, zda uživatel s daným emailem již existuje v databázi.
     *
     * @param email emailová adresa, kterou chceme ověřit
     * @return true pokud existuje, jinak false
     */
    boolean existsByEmail(String email);

}
