package evidence.pojistenych.spring.data.repository;

import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repozitář pro práci s pojištěnci v databázi.
 * Podporuje základní CRUD operace díky rozhraní JpaRepository.
 */
@Repository
public interface InsuredPersonRepository extends JpaRepository<InsuredPersonEntity, Long> {
    /**
     * Vrátí pojištěnce podle jeho ID.
     *
     * @param id ID pojištěnce
     * @return pojištěnec s daným ID
     */
    InsuredPersonEntity getById (Long id);

    /**
     * Zkontroluje, jestli už existuje pojištěnec s daným emailem.
     *
     * @param email email pojištěnce
     * @return true, pokud pojištěnec s emailem existuje, jinak false
     */
    boolean existsByEmail(String email);
}
