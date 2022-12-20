package CoveringDesignProblem.Exceptions;

public class ElementAlreadyInBlockException extends RuntimeException{
    public ElementAlreadyInBlockException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public ElementAlreadyInBlockException(String errorMessage) {
        super(errorMessage);
    }
}