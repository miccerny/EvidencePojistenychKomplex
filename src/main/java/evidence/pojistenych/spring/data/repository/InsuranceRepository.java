package evidence.pojistenych.spring.data.repository;

import evidence.pojistenych.spring.data.entities.InsuranceEntity;
import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repozitář pro práci s pojištěními v databázi.
 * Umožňuje základní CRUD operace pro entitu InsuranceEntity.
 */
@Repository
public interface InsuranceRepository extends CrudRepository<InsuranceEntity, Long> {
    /**
     * Najde stránky pojištění podle konkrétního pojištěnce.
     *
     * @param insuredPerson - pojištěnec, podle kterého hledáme pojištění
     * @param pageable - informace o stránkování (stránka, velikost, třídění)
     * @return - stránka pojištění daného pojištěnce
     */
    Page<InsuranceEntity> findByInsuredPerson(InsuredPersonEntity insuredPerson, Pageable pageable);
}
