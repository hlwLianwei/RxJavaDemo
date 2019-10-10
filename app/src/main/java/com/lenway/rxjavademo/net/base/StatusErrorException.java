package com.lenway.rxjavademo.net.base;

public class StatusErrorException extends Throwable
{
    public Object data;

    public StatusErrorException(Object data)
    {
        this.data = data;
    }
}
