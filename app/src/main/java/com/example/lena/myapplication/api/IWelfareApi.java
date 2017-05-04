package com.example.lena.myapplication.api;

import com.example.lena.myapplication.api.bean.WelfarePhotoList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface IWelfareApi {

    /**
     * 获取图片
     * eg: http://gank.io/api/data/福利/10/1
     *
     * @param page 页码
     * @return
     */
    @GET("/api/data/{title}/10/{page}")
    Observable<WelfarePhotoList> getPhotoByTitle(@Path("title") String title, @Path("page") int page);

}
