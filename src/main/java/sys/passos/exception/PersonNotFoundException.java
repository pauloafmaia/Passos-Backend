package sys.passos.exception;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String mensagem) {
        super(mensagem);
    }
}
