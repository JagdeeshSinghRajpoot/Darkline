package com.darkline.apis;

import com.darkline.model.LoginKey;
import com.darkline.model.Posts;
import com.darkline.model.User;
import com.darkline.model.UserLogin;
import com.google.gson.annotations.JsonAdapter;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface UserApi {

    @Headers( "Content-Type: application/json" )
    @POST("/api/create-user")
    Call<User> createUser(@Body  User user);

    @POST("/api/v1/auth/login")
    Call<LoginKey> userLogin(@Body UserLogin userLogin);

    @GET("/darkline/posts/")
    Call<List<Posts>> getAllPosts();

    @GET
    Call<ResponseBody> getImage(@Url String url);

}
