package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.app.api.RetrofitClient;
import com.example.app.interfaces.Asyncronable;
import com.example.app.interfaces.Inputable;
import com.example.app.models.RegisterResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrarActivity extends AppCompatActivity implements Asyncronable<JSONObject>, Inputable {

    EditText email;
    EditText password;
    EditText name;
    EditText lastName;
    EditText dni;
    Button registrar;

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

                Call<RegisterResponse> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .singUp("TEST",nom,lastn,id,mail,pass,3900,16);

                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                        if(response.code() == 200){
                            RegisterResponse rr = response.body();
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


    @Override
    public void showProgress(String msg) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void afterRequest(JSONObject response) {

    }

    @Override
    public void addInputChangedListeners() {

    }
}