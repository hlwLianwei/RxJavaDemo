package com.lenway.rxjavademo.net.base;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.DeleteRequest;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.PutRequest;

import org.json.JSONObject;

import java.io.File;
import java.util.Iterator;

import com.lenway.rxjavademo.entity.AppData;
import com.lenway.rxjavademo.util.Checker;
import com.lenway.rxjavademo.util.LogUtil;

public abstract class BaseRequest
{
    public static final String GET = "get";
    public static final String POST = "post";
    public static final String PUT = "put";
    public static final String DELETE = "delete";

    public abstract String url();

    public String method()
    {
        return GET;
    }

    public JSONObject postBody()
    {
        return null;
    }

    public File postFile()
    {
        return null;
    }

    public <T> void send(AbsCallback<T> callback)
    {
        LogUtil.w(toLogInfo(null, 2, getClass().getSimpleName() + "." + method()));

        String authorization = AppData.getAuthorization();
        if (method().equalsIgnoreCase(GET))
        {
            String strUrl = url();
            LogUtil.d(strUrl);
            GetRequest<T> request = OkGo.get(strUrl);
            if (Checker.isNotEmpty(authorization))
            {
                request.headers(AppData.AUTHORIZATION, "Bearer " + authorization);
            }
            else
            {
                request.headers(AppData.AUTHORIZATION, "Bearer ");
            }

            request.headers(AppData.PLATFORM, platform());
            request.tag(getCallerName());
            request.execute(callback);
        }
        else if (method().equalsIgnoreCase(POST))
        {
            PostRequest<T> request = OkGo.post(url());
            if (Checker.isNotEmpty(authorization))
            {
                request.headers(AppData.AUTHORIZATION, "Bearer " + authorization);
            }
            else
            {
                request.headers(AppData.AUTHORIZATION, "Bearer ");
            }

            request.headers(AppData.PLATFORM, platform());
            request.tag(getCallerName());

            JSONObject jsonObject = postBody();
            File file = postFile();
            if (file != null)
            {
                // 其他参数
                if (jsonObject != null)
                {
                    HttpParams params = new HttpParams();
                    try
                    {
                        for (Iterator iter = jsonObject.keys(); iter.hasNext();)
                        {
                            String key = (String) iter.next();
                            params.put(key, jsonObject.getString(key));
                        }
                    }
                    catch (Exception e)
                    {
                        LogUtil.e(e.toString());
                    }
                }

                request.isMultipart(true);
                request.params("file", file);
                //request.params(params);
                //request.upFile(file);
            }
            else if (jsonObject != null)
            {
                request.upJson(jsonObject);
            }

            request.execute(callback);
        }
        else if (method().equalsIgnoreCase(PUT))
        {
            PutRequest<T> request = OkGo.put(url());
            if (Checker.isNotEmpty(authorization))
            {
                request.headers(AppData.AUTHORIZATION, "Bearer " + authorization);
            }
            else
            {
                request.headers(AppData.AUTHORIZATION, "Bearer ");
            }

            request.headers(AppData.PLATFORM, platform());
            request.tag(getCallerName());
            request
                .upJson(postBody())
                .execute(callback);
        }
        else if (method().equalsIgnoreCase(DELETE))
        {
            DeleteRequest<T> request = OkGo.delete(url());
            if (Checker.isNotEmpty(authorization))
            {
                request.headers(AppData.AUTHORIZATION, "Bearer " + authorization);
            }
            else
            {
                request.headers(AppData.AUTHORIZATION, "Bearer ");
            }

            request.headers(AppData.PLATFORM, platform());
            request.tag(getCallerName());
            request.execute(callback);
        }
    }

    private String platform()
    {
        JSONObject  jsonObject = new JSONObject();
        try
        {
            jsonObject.put("system", "android");
            // 手机厂商
            jsonObject.put("brand", android.os.Build.BRAND);
            jsonObject.put("manufacturer", android.os.Build.MANUFACTURER);
            // 手机型号
            jsonObject.put("model", android.os.Build.MODEL);
            // android系统版本
            jsonObject.put("version", android.os.Build.VERSION.RELEASE);
        }
        catch (Exception e)
        {
            LogUtil.e(e.toString());
        }

        return jsonObject.toString();
    }

    /**
     * 获取调用者的名称(带包名的全称)
     * @return
     */
    private static String getCallerName()
    {
        try
        {

            StackTraceElement[] traceElements = (new Exception()).getStackTrace();
            StackTraceElement traceElement = traceElements[2];

            return traceElement.getClassName();
        }
        catch (Exception e)
        {
            LogUtil.e(e.toString());
        }

        return "";
    }

    /**
     * 打印日志时获取当前的程序类名.函数名 -->
     *
     * @return
     */
    public static String toLogInfo(Object object, int index, String log)
    {
        try
        {
            StackTraceElement[] traceElements = (new Exception()).getStackTrace();
            StackTraceElement traceElement = traceElements[index];

            StringBuffer sb = new StringBuffer()
                .append(object != null ?
                    object.getClass().getSimpleName() :
                    traceElement.getClassName())
                .append(".")
                .append(traceElement.getMethodName())
                .append(" --> ")
                .append(log);
            return sb.toString();
        }
        catch (Exception e)
        {
            LogUtil.e(e.toString());
        }

        return "";
    }
}
