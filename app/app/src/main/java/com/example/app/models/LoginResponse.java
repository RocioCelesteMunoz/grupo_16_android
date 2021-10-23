package com.example.app.models;

import com.example.app.models.Credential;

public class LoginResponse {

    private Boolean success;
    private String msg;
    private Credential credential;

    public LoginResponse(Boolean success, String msg, Credential credential) {
        this.success = success;
        this.msg = msg;
        this.credential = credential;
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

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }
}
