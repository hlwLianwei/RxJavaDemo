package com.lenway.rxjavademo.net;

import com.lenway.rxjavademo.net.base.BaseRequest;

public class FetchBannerList extends BaseRequest
{
    /**
     * 从0开始
     */
    public int reqPageNo;

    /**
     *
     * @param pageNo 从0开始
     */
    public FetchBannerList(int pageNo)
    {
        reqPageNo = pageNo;
    }

    @Override
    public String url()
    {
        return Urls.v2.FETCH_BANNER_LIST + "?pageNo=" + reqPageNo;
//        return Urls.GET_NEWS_LIST + "?pagenum=" + reqPage;
    }
}
