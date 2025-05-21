package evidence.pojistenych.spring.models.exceptions;

public class InsuranceNotFoundException extends RuntimeException{

    public InsuranceNotFoundException(Long id){
        super("Pojištění s ID " + id + " nenalezeno");
    }
}
