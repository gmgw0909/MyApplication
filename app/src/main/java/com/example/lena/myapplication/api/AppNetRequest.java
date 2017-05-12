package com.example.lena.myapplication.api;

import com.example.lena.myapplication.base.BaseRequest;
import com.example.lena.myapplication.utils.Constants;

import retrofit2.Retrofit;

/**
 * 创建者：  LeeBoo
 * 创建时间：2016/11/2 10:13
 * 类描述：  App的所有api创建
 */
public class AppNetRequest extends BaseRequest {

    private static IWelfareApi iWelfareApi;

    public static IWelfareApi getWelfareApi() {
        if (iWelfareApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(getClient())
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            iWelfareApi = retrofit.create(IWelfareApi.class);
        }
        return iWelfareApi;
    }

    private static ImoocApi imoocApi;

    public static ImoocApi getImoocApi() {
        if (imoocApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(getClient())
                    .baseUrl(Constants.IMOOC_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            imoocApi = retrofit.create(ImoocApi.class);
        }
        return imoocApi;
    }
}
