package exception;


public class InvalidEntryException extends Exception {
    public InvalidEntryException(String msg){
        super(msg);

    }
    public InvalidEntryException(Throwable e){
        super(e);
    }
    public InvalidEntryException(Throwable e , String msg){
        super(msg ,e);
    }

}
