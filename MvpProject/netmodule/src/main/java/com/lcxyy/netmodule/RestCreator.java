package com.lcxyy.netmodule;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestCreator {


    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    public static WeakHashMap<String, Object> getParams() {
        return paramsHolder.PARAMS;
    }
    private static final class paramsHolder{
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = "https://www.baidu.com/";
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

    }

    private static final class OkhttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OHHTTP_CLIENT = new OkHttpClient.Builder().connectTimeout(TIME_OUT, TimeUnit.SECONDS).build();

    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
