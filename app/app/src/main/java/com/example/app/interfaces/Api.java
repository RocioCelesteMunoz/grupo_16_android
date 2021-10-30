package com.example.app.interfaces;

import com.example.app.models.EventRegisterResponse;
import com.example.app.models.LoginResponse;
import com.example.app.models.RefreshTokenResponse;
import com.example.app.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;

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

    @PUT("refresh")
    Call<RefreshTokenResponse> refreshToken();

    @FormUrlEncoded
    @POST("event")
    Call<EventRegisterResponse> registerEvent(
            @Field("env") String env,
            @Field("type_events") String type_events,
            @Field("description") String description
    );
}
