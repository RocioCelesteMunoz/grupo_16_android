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

import com.example.app.models.Menu;
import com.example.app.services.EmailService;
import com.example.app.utils.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.app.ReservationActivity.COMBO_CINCO;
import static com.example.app.ReservationActivity.COMBO_CUATRO;
import static com.example.app.ReservationActivity.COMBO_DOS;
import static com.example.app.ReservationActivity.COMBO_SEIS;
import static com.example.app.ReservationActivity.COMBO_SIETE;
import static com.example.app.ReservationActivity.COMBO_TRES;
import static com.example.app.ReservationActivity.COMBO_UNO;



public class TwoFactorActivity extends AppCompatActivity implements SensorEventListener {

    private long lastUpdate = 0;
    private float lastX, lastY, lastZ;
    private static final int UMBRAL_SHAKE = 200;

    public static HashMap<Integer,Menu> menus = new HashMap<Integer,Menu>();

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
                    inicializarMenus();
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(TwoFactorActivity.this, "Código de autenticación erroneo", Toast.LENGTH_SHORT).show();
                    txtAuth.setText("");
                }

            }
        });


    }

    public void inicializarMenus() {

        menus.put(COMBO_UNO, new Menu(1, "Hamburguesa Cheese", 780));
        menus.put(COMBO_DOS, new Menu(2, "Hamburguesa Classic", 580));
        menus.put(COMBO_TRES, new Menu(3, "Tabla Mediterranea", 1200));
        menus.put(COMBO_CUATRO, new Menu(4, "Tabla Tradicional", 2400));
        menus.put(COMBO_CINCO, new Menu(5, "Pizza Muzzarella", 850));
        menus.put(COMBO_SEIS, new Menu(6, "Pizza Jamón y Morrón", 1200));
        menus.put(COMBO_SIETE, new Menu(7, "Pizza Napolitana con Jamón", 1600));
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


