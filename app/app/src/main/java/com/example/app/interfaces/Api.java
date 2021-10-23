package com.example.app.interfaces;

import com.example.app.models.LoginResponse;
import com.example.app.models.RegisterResponse;
import com.example.app.models.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> singUp(
            @Field("env") String env,
            @Field("name") String name,
            @Field("lastname") String lastname,
            @Field("dni") int dni,
            @Field("email") String email,
            @Field("password") String password,
            @Field("commission") int comission,
            @Field("group") int group
    );

}
