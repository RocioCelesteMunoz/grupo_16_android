package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.services.EmailService;
import com.example.app.utils.SessionManager;

import java.text.DecimalFormat;


public class TwoFactorActivity extends AppCompatActivity implements SensorEventListener {

    private long lastUpdate = 0;
    private float lastX, lastY, lastZ;
    private static final int UMBRAL_SHAKE = 200;

    Button buttonAuth;
    Button buttonReenviar;
    EditText txtAuth;

    String randomCode = "";

    String email;

    EmailService _mailService = new EmailService();
    SessionManager session;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twofactor);

        buttonAuth = findViewById(R.id.buttonAuth);
        buttonReenviar = findViewById(R.id.buttonReenviar);
        txtAuth = findViewById(R.id.txtAuth);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        session = new SessionManager(getApplicationContext());
        String tokenTes = session.getStringData("TOKEN");
        Intent i = getIntent();

        /** Parémetros que recibo de la activity login/register **/
        email = i.getStringExtra("email");
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

    //uso sensor acelerometro para reenvio de mail
    @Override
    public void onSensorChanged(SensorEvent event) {
        String txt = "";


        synchronized (this) {

            switch (event.sensor.getType()) {
                case Sensor.TYPE_ACCELEROMETER:

                    float x = event.values[0];
                    float y = event.values[1];
                    float z = event.values[2];

                    long currentTime = System.currentTimeMillis();
                    if ((currentTime - lastUpdate) > 200) {
                        long diffTime = (currentTime - lastUpdate);
                        lastUpdate = currentTime;
                        float speed = Math.abs(x + y + z - lastX - lastY - lastZ) / diffTime * 10000;
                        if (speed > UMBRAL_SHAKE) {
                            randomCode = _mailService.sendEmail(email, TwoFactorActivity.this);
                            Toast.makeText(TwoFactorActivity.this, "Se ha enviado un nuevo token", Toast.LENGTH_SHORT).show();
                        }
                        lastX = x;
                        lastY = y;
                        lastZ = z;
                    }
                    break;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onRestart() {
        startSensors();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startSensors();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopSensors();
    }

    protected void startSensors() {
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void stopSensors() {
        mSensorManager.unregisterListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
    }

}
