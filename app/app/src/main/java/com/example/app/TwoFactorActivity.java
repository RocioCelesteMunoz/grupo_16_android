package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.api.RetrofitClient;
import com.example.app.services.EmailService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TwoFactorActivity extends AppCompatActivity {

    Button buttonAuth;
    Button buttonReenviar;
    EditText txtAuth;

    String randomCode = "";

    EmailService _mailService = new EmailService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twofactor);

        buttonAuth = findViewById(R.id.buttonAuth);
        buttonReenviar = findViewById(R.id.buttonReenviar);
        txtAuth = findViewById(R.id.txtAuth);


        Intent i = getIntent();

        /** Parémetros que recibo de la activity login/register **/
        String email = i.getStringExtra("email");
        String token = i.getStringExtra("token");
        String tokenRefresh = i.getStringExtra("tokenRefresh");
        randomCode = i.getStringExtra("tokenAccess");


        buttonReenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                randomCode = _mailService.sendEmail(email, TwoFactorActivity.this);

                Toast.makeText(TwoFactorActivity.this, "Se ha enviado un nuevo token", Toast.LENGTH_SHORT).show();

                }
            });

        buttonAuth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String tokenAuth = txtAuth.getText().toString();

                if(tokenAuth.equals(randomCode)) {
                    Intent intent = new Intent(TwoFactorActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(TwoFactorActivity.this, "Código de autenticación erroneo", Toast.LENGTH_SHORT).show();
                    txtAuth.setText("");
                }

            }
        });


    }

}
