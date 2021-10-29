package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.api.RetrofitClient;
import com.example.app.models.LoginResponse;
import com.example.app.services.EmailService;
import com.example.app.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnToRegister;
    EditText txtUser;
    EditText txtPasswordLogin;
    EditText textError;

    String randomCode = "";
    EmailService _mailService = new EmailService();
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new SessionManager(getApplicationContext());
        txtUser = findViewById(R.id.txtUser);
        txtPasswordLogin = findViewById(R.id.txtPasswordLogin);
        textError = findViewById(R.id.textError);

        btnToRegister = findViewById(R.id.button_registrar);
        btnLogin = findViewById(R.id.button_login);
        btnToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,RegistrarActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usuario = txtUser.getText().toString();
                final String password = txtPasswordLogin.getText().toString();

                if(usuario.isEmpty()){
                    txtUser.setError("El Email es requerido");
                    txtUser.requestFocus();
                    return;
                }

                if(password.isEmpty()){
                    txtUser.setError("La contraseña es requerida");
                    txtUser.requestFocus();
                    return;
                }

                textError.setText("");

                Call<LoginResponse> call = RetrofitClient.getInstance(getApplicationContext()).getApi().userLogin(usuario,password);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        LoginResponse loginResponse = response.body();
                        if (response.isSuccessful()) {
                            LoginResponse resp = response.body();
                            randomCode = _mailService.sendEmail(txtUser.getText().toString(), LoginActivity.this);
                            redirectToTwoFactorAuth(resp);
                            Toast.makeText(LoginActivity.this, loginResponse.getMsg(), Toast.LENGTH_LONG).show();
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                String message = jsonObject.getString("msg");
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
//                              Por error de credenciales pasa por acá
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });


            }
        });
    }

    public void redirectToTwoFactorAuth(LoginResponse response) {
        String token = response.getToken();
        String tokenRefresh = response.getToken_refresh();
        session.saveStringData("TOKEN", token);
        session.saveStringData("TOKEN_REFRESH", tokenRefresh);
        Intent intent = new Intent(LoginActivity.this, TwoFactorActivity.class);
        intent.putExtra("email",txtUser.getText().toString());
        intent.putExtra("token",token);
        intent.putExtra("tokenRefresh",tokenRefresh);
        intent.putExtra("tokenRefresh",randomCode);
        startActivity(intent);
        finish();
    }
}
