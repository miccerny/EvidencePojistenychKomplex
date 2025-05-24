package evidence.pojistenych.spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Hlavní třída Spring Boot aplikace pro správu pojistných záznamů.
 *
 * @SpringBootApplication – označuje hlavní konfigurační třídu a aktivuje automatickou konfiguraci Spring Boot.
 * @EnableJpaRepositories – povoluje podporu JPA repozitářů pro práci s databází.
 */
@SpringBootApplication
@EnableJpaRepositories
public class  InsuranceRecordsApplication {

    /**
     * Spouštěcí metoda aplikace.
     *
     * @param args argumenty příkazové řádky
     */
    public static void main(String[] args) {
        SpringApplication.run(InsuranceRecordsApplication.class, args);
    }
}
