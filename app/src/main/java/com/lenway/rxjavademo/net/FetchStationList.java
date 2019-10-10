package com.lenway.rxjavademo.net;

import com.lenway.rxjavademo.net.base.BaseRequest;

public class FetchStationList extends BaseRequest
{
    /**
     * 从0开始
     */
    public int reqPageNo;

    /**
     * 每页数量
     */
    public int reqPageSize;

    /**
     *
     * @param pageNo 从0开始
     * @param pageSize 每页数量
     */
    public FetchStationList(int pageNo, int pageSize)
    {
        reqPageNo = pageNo;
        reqPageSize = pageSize;
    }

    @Override
    public String url()
    {
        return Urls.v2.FETCH_STATION_LIST + "?pageNo=" + reqPageNo + "&pageSize=" + reqPageSize;
//        return Urls.GET_NEWS_LIST + "?pagenum=" + reqPage;
    }
}
