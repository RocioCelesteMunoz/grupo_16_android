package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import com.example.app.models.Credential;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnToRegister;
    EditText txtUser;
    EditText txtPasswordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUser = findViewById(R.id.txtUser);
        txtPasswordLogin = findViewById(R.id.txtPasswordLogin);

        btnToRegister = findViewById(R.id.button_registrar);
        btnLogin = findViewById(R.id.button_login);

        btnToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,RegistrarActivity.class);
                startActivity(i);
            }
        });
    }

}
