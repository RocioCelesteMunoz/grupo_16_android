package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.app.api.RetrofitClient;
import com.example.app.models.RegisterResponse;
import com.example.app.models.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrarActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText name;
    EditText lastName;
    EditText dni;
    Button registrar;
    User user;

    FirebaseAuth firebaseAuth;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        firebaseAuth = firebaseAuth.getInstance();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastName);
        dni = findViewById(R.id.dni);
        registrar = findViewById(R.id.button_registrar);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mail = email.getText().toString();
                final String pass = password.getText().toString();
                final String nom = name.getText().toString();
                final String lastn = lastName.getText().toString();
                final int id = Integer.parseInt(dni.getText().toString());

                if(mail.isEmpty()){
                    email.setError("Email es requerido");
                    email.requestFocus();
                    return;
                }

                if(pass.isEmpty()){
                    password.setError("Contraseña es requerido");
                    password.requestFocus();
                    return;
                }

                if(nom.isEmpty()){
                    name.setError("Nombre es requerido");
                    name.requestFocus();
                    return;
                }

                if(lastn.isEmpty()){
                    lastName.setError("Apellido es requerido");
                    lastName.requestFocus();
                    return;
                }

                if(dni.getText().toString().isEmpty() ){
                    dni.setError("DNI es requerido");
                    dni.requestFocus();
                    return;
                }

                user = new User(nom,lastn,id,mail,pass);

                Call<RegisterResponse> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .singUp("TEST",nom,lastn,id,mail,pass,3900,16);

                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                        if(response.code() == 200){

                            RegisterResponse rr = response.body();

                            final String email = "mailsoagrupo16@gmail.com";
                            final String password = "prueba123";

                            String messageToSend = "Hola, el código de autenticación es: ";

                            Properties props = new Properties();

                            props.put("mail.smtp.auth", "true");
                            props.put("mail.smtp.starttls.enable", "true");
                            props.put("mail.smtp.host", "smtp.gmail.com");
                            props.put("mail.smtp.port", "587");

                            /*Session session = Session.getInstance(props,new javax.mail.Authenticator(){
                                @Override
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return super.getPasswordAuthentication(email, password);
                                }
                            });*/

                            sendIntent(rr);
                            Toast.makeText(RegistrarActivity.this, rr.getMsg(), Toast.LENGTH_LONG).show();

                        } else if ( response.code() == 400 ) {
                            Toast.makeText(RegistrarActivity.this, "Error al crear el usuario", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Toast.makeText(RegistrarActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }


    public void sendIntent(RegisterResponse response) {

        String msg = getString(R.string.credentialsError);
        boolean success;
        String token = "";
        String tokenRefresh = "";

        if(response == null) {
            Toast.makeText(RegistrarActivity.this, "Error al intentar registrar al usuario", Toast.LENGTH_LONG).show();
            return;
        }

        success = response.isErr();
        token = response.getToken();
        tokenRefresh = response.getTokenRefresh();

        if(success){
            Intent intent = new Intent(RegistrarActivity.this, TwoFactorActivity.class);
            intent.putExtra("email",user.getEmail());
            intent.putExtra("token",token);
            intent.putExtra("tokenRefresh",tokenRefresh);
            startActivity(intent);
            finish();
        }
    }


}