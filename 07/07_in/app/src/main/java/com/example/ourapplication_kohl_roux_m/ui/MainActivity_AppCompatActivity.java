package com.example.ourapplication_kohl_roux_m.ui;

import android.os.Bundle;

import com.example.ourapplication_kohl_roux_m.dbClass.AppDataBase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.example.ourapplication_kohl_roux_m.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity_AppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "database-name").build();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_car, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // gestion des saisies d'énergies
    EditText editTextCarb = (EditText) findViewById (R.id.saisieCarb);
    long quantityCarb =  Long.valueOf(editTextCarb.getText().toString());

    EditText editTextElect = (EditText) findViewById (R.id.saisieElect);
    long quantityElect =  Long.valueOf(editTextElect.getText().toString());

    // bouton d'ajout de la saisie
    public ArrayList<Long> listCarb;
    public ArrayList <Long> listElect;

    public void addEnergyConsumpt (int power) {

        if(quantityCarb > 0)
            listCarb.add(quantityCarb);

        if(quantityElect > 0)
            listElect.add(quantityElect);

    }

}

