package sys.passos.exception;

public class SongNotFoundException extends RuntimeException{

    public SongNotFoundException(String mensagem) {
        super(mensagem);
    }
}