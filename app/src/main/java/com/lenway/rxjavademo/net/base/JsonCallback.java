package com.lenway.rxjavademo.net.base;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;

public abstract class JsonCallback<T> extends AbsCallback<T>
{
    @Override
    public T convertResponse(okhttp3.Response response)
    {
        ResponseBody body = response.body();
        if (body == null)
            return null;

        T data;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());

        // 解析出当前类的父类上的泛型的真实泛型
        Type genType = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) genType).getActualTypeArguments()[0];

        data = gson.fromJson(jsonReader, type);

        return data;
    }
}
