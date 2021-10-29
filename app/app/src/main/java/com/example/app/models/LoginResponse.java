package com.example.app.models;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("success")
    private Boolean success;
    @SerializedName("msg")
    private String msg;
    //private Credential credential;
    @SerializedName("token")
    private String token;
    @SerializedName("token_refresh")
    private String token_refresh;

    public LoginResponse(Boolean success, String msg,String token, String token_refresh) {
        this.success = success;
        this.token = token;
        this.token_refresh = token_refresh;
        this.msg = msg;
       // this.credential = credential;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken(){return token;}

    public void setToken(String token){this.token = token;}

    public String getToken_refresh(){return token_refresh;}

    public void setToken_refresh(String token_refresh){this.token_refresh = token_refresh;}

   /* public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }*/
}
