package com.lenway.rxjavademo;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.lenway.rxjavademo.util.LogUtil;
import com.lenway.rxjavademo.util.PreferUtil;
import com.lenway.rxjavademo.widget.WeakHandler;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class theApp extends Application
{
    public static Context sContext = null;
    public static theApp sInstance = null;

    private static Handler sHandler = new WeakHandler((msg) -> {
    });

    @Override
    public void onCreate()
    {
        super.onCreate();

        sContext = this;
        sInstance = this;
        init(this);
    }

    private void init(Context context)
    {
        LogUtil.init(context);
        PreferUtil.init(context);
        initOKGo();
    }

    private void initOKGo()
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("uuu_uuu");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);

        // 由于某些品牌的电桩开电需要90秒以上
        // 所以简单的将所有网络请求的超时时间设置为120秒
        int TIME_OUT = 120 * 1000;
        //全局的读取超时时间
        builder.readTimeout(TIME_OUT, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(TIME_OUT, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS);

        OkGo.getInstance().init(this)
            .setOkHttpClient(builder.build())
            .setRetryCount(0);
    }

    public static String string(int resId)
    {
        return sInstance.getString(resId);
    }

    public static int color(int resId)
    {
        return sContext.getResources().getColor(resId);
    }

    public static String colorString(int resId)
    {
        return "#" + Integer.toHexString(sContext.getResources().getColor(resId));
    }

    public static void showToast(String strToast)
    {
        sHandler.post(() -> toast(strToast));
    }

    public static void showToast(int resId)
    {
        sHandler.post(() -> toast(string(resId)));
    }

    private static void toast(String str)
    {
        Toast.makeText(sInstance, str, Toast.LENGTH_SHORT).show();
    }
}
