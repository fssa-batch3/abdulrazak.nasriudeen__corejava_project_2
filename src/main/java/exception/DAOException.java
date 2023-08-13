package exception;

public class DAOException extends Exception{
    public DAOException(String msg){
        super(msg);
    }
    public DAOException(Throwable e){
        super(e);

    }
    public DAOException(Throwable e , String msg){
        super(msg,e);
    }
}
