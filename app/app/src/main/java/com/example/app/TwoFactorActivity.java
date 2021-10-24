package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.interfaces.Asyncronable;
import com.example.app.models.RegisterResponse;
import com.example.app.models.User;
import com.example.app.services.EmailService;

import org.json.JSONObject;

import static com.example.app.utils.Configuration.generateRandomCode;

public class TwoFactorActivity extends AppCompatActivity {

    Button buttonAuth;
    Button buttonReenviar;
    EditText txtAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twofactor);

        buttonAuth = findViewById(R.id.buttonAuth);
        buttonReenviar = findViewById(R.id.buttonReenviar);
        txtAuth = findViewById(R.id.txtAuth);


        Intent i = getIntent();

        // Parémetros que recibo de la activity login/register //
        String email = i.getStringExtra("email");
        String token = i.getStringExtra("token");
        String tokenRefresh = i.getStringExtra("tokenRefresh");

        String randomCode = sendEmail(email,token);

    }

    public String sendEmail(String email, String token){

        Intent sendEmail = new Intent(Intent.ACTION_SEND);

        String randomCode = generateRandomCode();

        //EmailService emailService = new EmailService( this, email, "Token Autenticacion", token);

        return randomCode;
    }
}
