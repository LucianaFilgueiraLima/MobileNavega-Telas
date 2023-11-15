package com.example.cores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private Button buttonRed, buttonBlue, buttonGreen, buttonRandomColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        buttonRed = findViewById(R.id.redButton);
        buttonBlue = findViewById(R.id.blueButton);
        buttonGreen = findViewById(R.id.greenButton);
        buttonRandomColor = findViewById(R.id.randomColorButton);

        buttonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int corSelecionada = Color.RED;
                changeBackgroundColor(corSelecionada);
                saveColorToPreferences(corSelecionada);
            }
        });

        buttonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int corSelecionada = Color.BLUE;
                changeBackgroundColor(corSelecionada);
                saveColorToPreferences(corSelecionada);
            }
        });

        buttonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int corSelecionada = Color.GREEN;
                changeBackgroundColor(corSelecionada);
                saveColorToPreferences(corSelecionada);
            }
        });

        buttonRandomColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int randomColor = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
                changeBackgroundColor(randomColor);
                saveColorToPreferences(randomColor);
            }
        });



        Button btnTrocarTela = findViewById(R.id.nextButton);
        btnTrocarTela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crie um Intent para iniciar a nova atividade
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                // Inicie a nova atividade
                startActivity(intent);
            }
        });

        Button btnTrocarTelaVoltando = findViewById(R.id.backButton);
        btnTrocarTelaVoltando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crie um Intent para iniciar a nova atividade
                Intent intent = new Intent(MainActivity.this, MainActivity.class);

                // Inicie a nova atividade
                startActivity(intent);
            }
        });


    }


    private void saveColorToPreferences(int cor) {
        SharedPreferences preferences = getSharedPreferences("CorPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("COR_ATUAL", cor);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Recupere a cor do SharedPreferences
        int corSalva = getColorFromPreferences();

        // Defina a cor na tela
        changeBackgroundColor(corSalva);
    }

    private int getColorFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("CorPreferences", MODE_PRIVATE);
        return preferences.getInt("COR_ATUAL", Color.WHITE);
    }

    private void changeBackgroundColor(int color) {
        getWindow().getDecorView().setBackgroundColor(color);
    }






}