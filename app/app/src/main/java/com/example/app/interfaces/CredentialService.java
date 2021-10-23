package com.example.app.interfaces;

import com.example.app.models.Credential;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CredentialService {

    @FormUrlEncoded
    @GET("login")
    Call<Credential> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

}
