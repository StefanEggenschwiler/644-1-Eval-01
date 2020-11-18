package com.example.ourapplication_kohl_roux_m.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import com.example.ourapplication_kohl_roux_m.BaseApp;
import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.adapter.RecyclerAdapter;
import com.example.ourapplication_kohl_roux_m.dbClass.AppDataBase;
import com.example.ourapplication_kohl_roux_m.dbClass.Repository.CarRepository;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.CarEntity;
import com.example.ourapplication_kohl_roux_m.ui.trajet.ListTrajet_BazActivity;
import com.example.ourapplication_kohl_roux_m.viewModel.car.CarMyListViewModel;
import com.example.ourapplication_kohl_roux_m.viewModel.trajet.TrajetListViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class InitApp extends BaseActivity {

    private Button choix1;
    private Button choix2;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main_app, frameLayout);

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.i8);

        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.i8);

        choix1 = findViewById(R.id.btn);
        choix2 = findViewById(R.id.addFuelButton);

        choix1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitApp.this, ListTrajet_BazActivity.class);
                startActivity(intent);
            }
        });


        choix2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(InitApp.this, ListTrajet_BazActivity.class);
                    startActivity(intent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
/*        fab.setOnClickListener(view -> {
                    Intent intent = new Intent(InitApp.this, AddNewMyCar.class);
                    startActivity(intent);
                }
        );
*/
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_add_car, menu);
        return true;
    }

}
