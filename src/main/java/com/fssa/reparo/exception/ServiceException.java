package com.fssa.reparo.exception;

public class ServiceException extends  Exception{
    public ServiceException (String msg){
        super(msg);

    }
    public ServiceException(Throwable e){
        super(e);
    }
    public ServiceException(String m , Throwable e){
        super(m,e);
    }
}
