package com.lenway.rxjavademo.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferUtil
{
    private static final String ms_perferenceName = "Preference";
    private static SharedPreferences ms_preferences    = null;

    public static synchronized void init(final Context context)
    {
        if (ms_preferences != null)
        {
            return;
        }

        ms_preferences = context.getSharedPreferences(ms_perferenceName, Context.MODE_PRIVATE);
    }

    /**
     * 清除所有数据
     */
    public static void clearAll()
    {
        ms_preferences.edit().clear().commit();
    }

    /**
     * 移除项
     * @param name 键值
     */
    public static void remove(final String name)
    {
        ms_preferences.edit().remove(name).commit();
    }

    /**
     * 返回 bool 值
     * @param key 键值
     * @param defValue 默认值
     */
    public static boolean getBooleanPreference(final String key, final boolean defValue)
    {
        return ms_preferences.getBoolean(key, defValue);
    }

    /**
     * 设置 bool 值
     * @param key 键值
     * @param value 值
     *
     */
    public static boolean setBooleanPreference(final String key, final boolean value)
    {
        return ms_preferences.edit().putBoolean(key, value).commit();
    }

    /**
     * 返回字符串
     * @param key 键值
     */
    public static String getStringPreference(final String key)
    {
        return ms_preferences.getString(key, "");
    }


    /**
     * 设置字符串
     * @param key 键值
     * @param value 值
     */
    public static boolean setStringPreference(final String key, final String value)
    {
        return ms_preferences.edit().putString(key, value).commit();
    }


    /**
     * 返回整数类型
     * @param key 键值
     * @param defValue 默认值
     */
    public static int getIntPreference(final String key, final int defValue)
    {
        return ms_preferences.getInt(key, defValue);
    }

    /**
     * 设置整数类型
     * @param key 键值
     * @param value 值
     */
    public static void setIntPreference(final String key, final int value)
    {
        ms_preferences.edit().putInt(key, value).commit();
    }

    /**
     * 返回长整形
     * @param key 键值
     * @param defValue 默认值
     */
    public static long getLongPreference(final String key, final long defValue)
    {
        return ms_preferences.getLong(key, defValue);
    }

    /**
     * 设置长整形
     * @param key 键值
     * @param value 值
     */
    public static void setLongPreference(final String key, final long value)
    {
        ms_preferences.edit().putLong(key, value).commit();
    }
}
