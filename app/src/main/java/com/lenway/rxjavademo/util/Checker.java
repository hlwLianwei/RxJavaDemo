package com.lenway.rxjavademo.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import org.json.JSONArray;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 各种检查工具
 */
public class Checker
{
    public static <E> boolean isEmpty(Collection<E> collection)
    {
        if (collection == null || collection.size() == 0)
        {
            return true;
        }

        return false;
    }

    public static <E> boolean isNotEmpty(Collection<E> collection)
    {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(String str)
    {
        if (str == null || str.length() == 0)
        {
            return true;
        }

        return false;
    }

    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    public static boolean isAllEmpty(String... strings)
    {
        for (String string : strings)
        {
            if (Checker.isNotEmpty(string))
            {
                return false;
            }
        }

        return true;
    }

    public static boolean isAllNotEmpty(String... strings)
    {
        for (String string : strings)
        {
            if (Checker.isEmpty(string))
            {
                return false;
            }
        }

        return true;
    }

    public static <E> boolean isEmpty(E[] array)
    {
        return (array == null || array.length == 0);
    }

    public static <E> boolean isNotEmpty(E[] array)
    {
        return !isEmpty(array);
    }

    public static <K, V> boolean isEmpty(Map<K, V> sourceMap)
    {
        return (sourceMap == null || sourceMap.size() == 0);
    }

    public static <K, V> boolean isNotEmpty(Map<K, V> sourceMap)
    {
        return !isEmpty(sourceMap);
    }

    public static boolean isEmpty(JSONArray jsonArray)
    {
        return jsonArray == null || jsonArray.length() == 0;
    }

    public static boolean isNotEmpty(JSONArray jsonArray)
    {
        return !isEmpty(jsonArray);
    }

    /**
     * 检查指定包名的app是否已经安装
     *
     * @param manager
     * @param packageName
     * @return
     */
    public static boolean isInstallApp(PackageManager manager, String packageName)
    {
        try
        {
            List<PackageInfo> pkgList = manager.getInstalledPackages(0);

            for (int i = 0; i < pkgList.size(); i++)
            {
                PackageInfo pInfo = pkgList.get(i);

                if (pInfo.packageName.equalsIgnoreCase(packageName))
                    return true;
            }

        }
        catch (Exception ex)
        {
            return false;
        }

        return false;
    }

    public static boolean checkEmail(String email)
    {
        if (email == null)
        {
            return false;
        }
        return Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$").matcher(email).matches();
    }

    public static boolean checkNum(String num)
    {
        if (num == null)
        {
            return false;
        }
        return Pattern.compile("^[0-9]\\d*$").matcher(num).matches();
    }

    public static boolean checkPhone(String phone)
    {
        if (phone == null)
        {
            return false;
        }
        return Pattern.compile("^1[3456789]\\d{9}$").matcher(phone).matches();
    }

    public static boolean checkPwd(String pwd)
    {
        if (pwd == null)
        {
            return false;
        }
        return Pattern.compile("^[A-Za-z0-9]+$").matcher(pwd).matches();
    }

    public static boolean checkURL(String url)
    {
        if (url == null)
        {
            return false;
        }
        return Pattern.compile("^(http://|https://)?((?:[A-Za-z0-9]+-[A-Za-z0-9]+|[A-Za-z0-9]+)\\.)+([A-Za-z]+)[/\\?\\:]?.*$").matcher(url).matches();
    }
}
