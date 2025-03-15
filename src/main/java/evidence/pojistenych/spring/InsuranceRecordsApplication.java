package evidence.pojistenych.spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 */
@SpringBootApplication
@EnableJpaRepositories
public class  InsuranceRecordsApplication {
    public static void main(String[] args) {
        SpringApplication.run(InsuranceRecordsApplication.class, args);
    }
}
