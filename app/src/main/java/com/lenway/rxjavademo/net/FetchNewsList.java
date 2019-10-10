package com.lenway.rxjavademo.net;

import com.lenway.rxjavademo.net.base.BaseRequest;

public class FetchNewsList extends BaseRequest
{
    /**
     * 从0开始
     */
    public int reqPageNo;

    /**
     *
     * @param pageNo 从0开始
     */
    public FetchNewsList(int pageNo)
    {
        reqPageNo = pageNo;
    }

    @Override
    public String url()
    {
        return Urls.v2.FETCH_NEWS_LIST + "?pageNo=" + reqPageNo;
//        return Urls.GET_NEWS_LIST + "?pagenum=" + reqPage;
    }
}
