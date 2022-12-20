package CoveringDesignProblem.Exceptions;

public class InvalidConfigurationException extends RuntimeException{
    public InvalidConfigurationException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public InvalidConfigurationException(String errorMessage) {
        super(errorMessage);
    }
}
