package com.lenway.rxjavademo.entity.resp;

import com.lenway.rxjavademo.entity.GsonObj;

public class RespResult<T> extends GsonObj<RespResult>
{
    public int status;
    public String message;
    public T result;
}
