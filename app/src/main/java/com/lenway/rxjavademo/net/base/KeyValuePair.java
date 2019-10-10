package com.lenway.rxjavademo.net.base;

/**
 * Key-value
 */
public class KeyValuePair
{
    private String mKey;
    private String mValue;

    public KeyValuePair(String key, String value)
    {
        mKey = key;
        mValue = value;
    }

    public String getName()
    {
        return mKey;
    }

    public String getValue()
    {
        return mValue;
    }
}
