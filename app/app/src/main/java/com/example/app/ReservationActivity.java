package com.example.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.models.Menu;
import com.example.app.services.EmailService;
import com.example.app.utils.SessionManager;

import static com.example.app.TwoFactorActivity.menus;
import static com.example.app.utils.Configuration.generateRandomCode;

public class ReservationActivity extends AppCompatActivity {

    final static int COMBO_UNO = 1;
    final static int COMBO_DOS = 2;
    final static int COMBO_TRES = 3;
    final static int COMBO_CUATRO = 4;
    final static int COMBO_CINCO = 5;
    final static int COMBO_SEIS = 6;
    final static int COMBO_SIETE = 7;
    final static double CERO = 0.0;

    final static String DESCUENTO_2X1 = "2X1PRM1";
    final static String DESCUENTO_20 = "20MAS100";

    final static int DOS = 2;
    final static double PORCIENTO20 = 20;

    double precioTotal = CERO;


    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;
    Spinner spinner4;
    Spinner spinner5;
    Spinner spinner6;
    Spinner spinner7;

    Switch switch1;
    Switch switch2;
    Switch switch3;
    Switch switch4;
    Switch switch5;
    Switch switch6;
    Switch switch7;

    EditText textInicio;
    EditText textFin;
    EditText cuponDesc;

    Button btnReserva;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        switch3 = findViewById(R.id.switch3);
        switch4 = findViewById(R.id.switch4);
        switch5 = findViewById(R.id.switch5);
        switch6 = findViewById(R.id.switch6);
        switch7 = findViewById(R.id.switch7);

        btnReserva = findViewById(R.id.btnReserva);

        textInicio = findViewById(R.id.textInicio);
        textFin = findViewById(R.id.textFin);
        cuponDesc = findViewById(R.id.cuponDesc);

        Intent i = getIntent();

        /** Código para inicializar el Spinner con su respectivo dropdown **/

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        spinner5 = findViewById(R.id.spinner5);
        spinner6 = findViewById(R.id.spinner6);
        spinner7 = findViewById(R.id.spinner7);


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.Cantidad, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);
        spinner5.setAdapter(adapter);
        spinner6.setAdapter(adapter);
        spinner7.setAdapter(adapter);

        /** --------------------------------------------------------------- **/


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                String spinner1Obtained = spinner1.getSelectedItem().toString();

                if(!spinner1Obtained.equals("-")){

                    Menu menu1 = menus.get(COMBO_UNO);

                    precioTotal += menu1.getPrecioUnitario() * Integer.parseInt(spinner1Obtained);

                    System.out.println("" + precioTotal);

                }else{

                    precioTotal += CERO;
                }

            }

        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                String spinner1Obtained = spinner2.getSelectedItem().toString();

                if(!spinner1Obtained.equals("-")){

                    Menu menu1 = menus.get(COMBO_DOS);

                    precioTotal += menu1.getPrecioUnitario() * Integer.parseInt(spinner1Obtained);

                    System.out.println("" + precioTotal);

                }else{

                    precioTotal += CERO;
                }

            }

        });

        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                String spinner1Obtained = spinner3.getSelectedItem().toString();

                if(!spinner1Obtained.equals("-")){

                    Menu menu1 = menus.get(COMBO_TRES);

                    precioTotal += menu1.getPrecioUnitario() * Integer.parseInt(spinner1Obtained);

                    System.out.println("" + precioTotal);

                }else{

                    precioTotal += CERO;
                }

            }

        });

        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                String spinner1Obtained = spinner4.getSelectedItem().toString();

                if(!spinner1Obtained.equals("-")){

                    Menu menu1 = menus.get(COMBO_CUATRO);

                    precioTotal += menu1.getPrecioUnitario() * Integer.parseInt(spinner1Obtained);

                    System.out.println("" + precioTotal);

                }else{

                    precioTotal += CERO;
                }

            }

        });

        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                String spinner1Obtained = spinner5.getSelectedItem().toString();

                if(!spinner1Obtained.equals("-")){

                    Menu menu1 = menus.get(COMBO_CINCO);

                    precioTotal += menu1.getPrecioUnitario() * Integer.parseInt(spinner1Obtained);

                    System.out.println("" + precioTotal);

                }else{

                    precioTotal += CERO;
                }

            }

        });

        switch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                String spinner1Obtained = spinner6.getSelectedItem().toString();

                if(!spinner1Obtained.equals("-")){

                    Menu menu1 = menus.get(COMBO_SEIS);

                    precioTotal += menu1.getPrecioUnitario() * Integer.parseInt(spinner1Obtained);

                    System.out.println("" + precioTotal);

                }else{

                    precioTotal += CERO;
                }

            }

        });

        switch7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                String spinner1Obtained = spinner7.getSelectedItem().toString();

                if(!spinner1Obtained.equals("-")){

                    Menu menu1 = menus.get(COMBO_SIETE);

                    precioTotal += menu1.getPrecioUnitario() * Integer.parseInt(spinner1Obtained);

                    System.out.println("" + precioTotal);

                }else{

                    precioTotal += CERO;
                }

            }

        });

        btnReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String horaInicio = textInicio.getText().toString();
                final String horaFin = textFin.getText().toString();
                final String CodDesc = cuponDesc.getText().toString();

                if(precioTotal != CERO) {

                    String random = generateRandomCode();

                    if(CodDesc != null)
                        calcularDescuento(CodDesc);

                    Intent intent = new Intent(ReservationActivity.this, ConfirmationActivity.class);

                    intent.putExtra("randomCode",random);
                    intent.putExtra("monto",String.valueOf(precioTotal));

                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(ReservationActivity.this, "Debe seleccionar al menos un menú para continuar", Toast.LENGTH_LONG).show();
                    return;
                }

            }

        });


    }

    public void calcularDescuento(String desc){

        if(desc.equals(DESCUENTO_2X1))
            precioTotal = precioTotal / DOS;

        if(desc.equals(DESCUENTO_20))
            precioTotal = precioTotal - ( precioTotal* PORCIENTO20);
    }

}
