package com.example.app.models;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("env")
    private String env;

    @SerializedName("token")
    private String token;

    @SerializedName("token_refresh")
    private String token_refresh;

    public RegisterResponse(boolean success, String env, String token, String token_refresh) {
        this.success = success;
        this.env = env;
        this.token = token;
        this.token_refresh = token_refresh;
    }

    public boolean isErr() {
        return success;
    }

    public String getMsg() {
        return env;
    }

    public String getToken() {
        return token;
    }

    public String getTokenRefresh() {
        return token_refresh;
    }

}
