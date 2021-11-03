package com.example.app.api;
import android.content.Context;

import com.example.app.interfaces.Api;
import com.example.app.models.RefreshTokenResponse;
import com.example.app.utils.SessionManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://so-unlam.net.ar/api/api/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;
    SessionManager session;
    Context context;
    private RetrofitClient(Context context) {
        this.context = context;
        session = new SessionManager(context);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();

                                Request.Builder requestBuilder = original.newBuilder()
                                        .addHeader("Content-Type", "application/json");

                                if (!session.getStringData("TOKEN").isEmpty() & urlNeedsToken(original.url().toString())) {
                                    requestBuilder.addHeader("Authorization", "Bearer " + session.getStringData("TOKEN"));
                                }

                                if (original.url().toString().endsWith("refresh")) {
                                    requestBuilder.addHeader("Authorization", "Bearer " + session.getStringData("TOKEN_REFRESH"));
                                }

                                requestBuilder.method(original.method(), original.body());

                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        }
                )
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static synchronized RetrofitClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RetrofitClient(context);
        }
        return mInstance;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }

    private boolean urlNeedsToken(String url) {
        if (!url.endsWith("login") & !url.endsWith("refresh") & !url.endsWith("register")) {
            return true;
        }
        return false;
    }
}
