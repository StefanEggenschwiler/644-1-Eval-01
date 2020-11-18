package com.example.ourapplication_kohl_roux_m.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.ui.trajet.ListTrajet_BazActivity;

public class Start extends AppCompatActivity {

    private Button choix;

    protected void onCreate(Bundle savecInstacneState){
        super.onCreate(savecInstacneState);
        setContentView(R.layout.activity_start);

        choix = findViewById(R.id.btnstart);

        choix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start.this, InitApp.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_add_car, menu);
        return true;
    }
}
