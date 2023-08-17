package exception;

public class EndOfDeckException extends RuntimeException {

    public EndOfDeckException(){
        super("ERROR: Drawing from an empty deck");
    }
}
