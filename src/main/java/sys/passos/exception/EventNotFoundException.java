package sys.passos.exception;

public class EventNotFoundException extends RuntimeException{

    public EventNotFoundException(String mensagem) {
        super(mensagem);
    }
}