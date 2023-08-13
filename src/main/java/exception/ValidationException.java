package exception;

public class ValidationException extends Exception{

    public ValidationException(Exception e){
        super(e);
    }

    public ValidationException(String e){
        super(e);
    }
}
