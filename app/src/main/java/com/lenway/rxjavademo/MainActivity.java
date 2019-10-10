package com.lenway.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lenway.rxjavademo.entity.Banner;
import com.lenway.rxjavademo.entity.ChargeStation;
import com.lenway.rxjavademo.entity.News;
import com.lenway.rxjavademo.entity.resp.RespResult;
import com.lenway.rxjavademo.net.FetchBannerList;
import com.lenway.rxjavademo.net.FetchNewsList;
import com.lenway.rxjavademo.net.FetchStationList;
import com.lenway.rxjavademo.net.base.JsonCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_test).setOnClickListener(v -> test());
    }

    private void test()
    {
        new FetchBannerList(0).send(new JsonCallback<RespResult<List<Banner>>>()
        {
            @Override
            public void onSuccess(Response<RespResult<List<Banner>>> response)
            {
                RespResult<List<Banner>> resp = response.body();
                List<Banner> result = resp.result;

            }
        });

        new FetchNewsList(0).send(new JsonCallback<RespResult<List<News>>>()
        {
            @Override
            public void onSuccess(Response<RespResult<List<News>>> response)
            {
                RespResult<List<News>> resp = response.body();
                List<News> result = resp.result;
            }
        });

        new FetchStationList(0, 100).send(new JsonCallback<RespResult<List<ChargeStation>>>()
        {
            @Override
            public void onSuccess(Response<RespResult<List<ChargeStation>>> response)
            {
                RespResult<List<ChargeStation>> resp = response.body();
                List<ChargeStation> result = resp.result;
            }

            @Override
            public void onFinish()
            {
                super.onFinish();
            }
        });
    }
}
