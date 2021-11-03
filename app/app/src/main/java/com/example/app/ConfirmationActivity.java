package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class ConfirmationActivity extends AppCompatActivity {

    EditText lblMonto;
    EditText lblCode;

    Button salir;
    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        btnVolver = findViewById(R.id.btnVolver);

        lblMonto = findViewById(R.id.lblMonto);
        lblCode = findViewById(R.id.lblCode);

        Intent i = getIntent();
        String total = i.getStringExtra("monto");
        String randomCode = i.getStringExtra("randomCode");

        lblMonto.setText(total);
        lblCode.setText(randomCode);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ConfirmationActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();

            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfirmationActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

    }

}
