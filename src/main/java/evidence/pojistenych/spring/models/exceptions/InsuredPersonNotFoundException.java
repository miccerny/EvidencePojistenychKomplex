package evidence.pojistenych.spring.models.exceptions;

public class InsuredPersonNotFoundException extends RuntimeException {

    public InsuredPersonNotFoundException(Long id) {
      super("Pojištěnec s ID " + id + " nenalezen");
    }
}
