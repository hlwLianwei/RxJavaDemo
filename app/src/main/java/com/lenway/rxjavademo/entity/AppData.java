package com.lenway.rxjavademo.entity;

import com.lenway.rxjavademo.util.PreferUtil;

public class AppData
{
    public static void clear()
    {
        PreferUtil.clearAll();
    }
    public static String getAuthorization()
    {
        return PreferUtil.getStringPreference(AUTHORIZATION);
    }

    public static void setAuthorization(String orderSeq)
    {
        PreferUtil.setStringPreference(AUTHORIZATION, orderSeq);
    }

    public final static String AUTHORIZATION = "Authorization";
    public final static String PLATFORM = "Platform";
}
