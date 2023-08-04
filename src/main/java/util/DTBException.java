package util;

public class DTBException extends  Exception{
    public DTBException(String msg){
        super(msg);

    }
    public DTBException(String msg,Throwable e){
        super(msg,e);

    }
    public DTBException(Throwable e){
        super(e);
    }
}
