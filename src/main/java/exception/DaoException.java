package exception;

public class DaoException extends Exception{
    public DaoException(String msg){
        super(msg);
    }
    public DaoException(Throwable e){
        super(e);

    }
    public DaoException(Throwable e , String msg){
        super(msg,e);
    }
}
