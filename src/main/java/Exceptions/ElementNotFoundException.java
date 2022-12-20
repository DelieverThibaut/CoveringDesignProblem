package CoveringDesignProblem.Exceptions;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public ElementNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
