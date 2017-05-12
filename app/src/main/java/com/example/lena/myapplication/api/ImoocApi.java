package com.example.lena.myapplication.api;

import com.example.lena.myapplication.api.bean.WelfarePhotoList;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 创建者 LeeBoo
 * 创建时间 2017/5/11
 */

public interface ImoocApi {


    @FormUrlEncoded
    @POST("/api3/courselistinfo")
    Observable<WelfarePhotoList> getVideoByTitle(@Field("timestamp") String timestamp,
                                                 @Field("uid") String uid,
                                                 @Field("secrect") String secrect,
                                                 @Field("token") String token,
                                                 @Field("page") String page,
                                                 @Field("cat_type") String cat_type,
                                                 @Field("sort_type") String sort_type,
                                                 @Field("uuid") String uuid,
                                                 @Field("course_type") String course_type);

}
