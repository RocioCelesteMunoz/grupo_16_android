package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.api.RetrofitClient;
import com.example.app.models.EventRegisterResponse;
import com.example.app.models.LoginResponse;
import com.example.app.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {

    ImageView btnExit;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        session = new SessionManager(getApplicationContext());
        String usuario = session.getStringData("USUARIO");
        Call<EventRegisterResponse> call = RetrofitClient.getInstance(getApplicationContext()).getApi().registerEvent("PROD", "USUARIO-EN-MENU", usuario);
        call.enqueue(new Callback<EventRegisterResponse>() {
            @Override
            public void onResponse(Call<EventRegisterResponse> call, Response<EventRegisterResponse> response) {
                EventRegisterResponse eventResponse = response.body();
                if (response.isSuccessful()) {
                } else {
                }
            }

            @Override
            public void onFailure(Call<EventRegisterResponse> call, Throwable t) {
            }
        });
        btnExit = findViewById(R.id.btnExit);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }
}
