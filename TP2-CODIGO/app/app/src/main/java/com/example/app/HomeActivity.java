package com.example.app;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.api.RetrofitClient;
import com.example.app.models.EventRegisterResponse;
import com.example.app.models.LoginResponse;
import com.example.app.models.Menu;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    ImageView btnExit;
    Button btnDesc;
    Button btnMenu;
    Button btnRes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        checkBattery();

        btnExit = findViewById(R.id.btnExit);
        btnDesc = findViewById(R.id.btnDesc);
        btnMenu = findViewById(R.id.btnMenu);
        btnRes = findViewById(R.id.btnRes);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        btnDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, DescActivity.class);
                startActivity(i);
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, MenuActivity.class);
                startActivity(i);
            }
        });

        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ReservationActivity.class);
                startActivity(i);
            }
        });

    }

    private void checkBattery() {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent state = getBaseContext().registerReceiver(null, intentFilter);
        int level = state.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = state.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        int percentage = level * 100 / scale;

        Toast.makeText(this, "El nivel de bateria es del " + percentage + "%.", Toast.LENGTH_SHORT).show();


    }

}
