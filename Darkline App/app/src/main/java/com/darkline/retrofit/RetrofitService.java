package com.darkline.retrofit;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static final String BASE_URL = "http://192.168.95.153:5000/";
    private static  final String BASE_LOGIN_URL = "http://192.168.95.153:5000/";

    private static Retrofit retrofitWithToken;
    private static Retrofit retrofitWithoutToken;

//    public RetrofitService(String token){
//        initializeRetrofit(token);
//    }

    public static Retrofit getRetrofitClientWithToken(String token){
        if (retrofitWithToken == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new AuthInterceptor(token));

            retrofitWithToken = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .client(httpClient.build())
                    .build();
        }
        return retrofitWithToken;
    }

    public static Retrofit getRetrofitClientWithoutToken(){
        if (retrofitWithoutToken == null){
            retrofitWithoutToken = new Retrofit.Builder()
                    .baseUrl(BASE_LOGIN_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitWithoutToken;
    }

//    public Retrofit getRetrofit() {
//        return retrofit;
//    }
}
