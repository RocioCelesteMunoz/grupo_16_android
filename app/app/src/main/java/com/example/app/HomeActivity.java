package com.example.app;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        checkBattery();
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
