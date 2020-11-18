package com.example.ourapplication_kohl_roux_m.ui.trajet;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.ui.BaseActivity;

public class map_dernier_trajet extends BaseActivity {


    protected void onCreate(Bundle savecInstacneState){
        super.onCreate(savecInstacneState);
        getLayoutInflater().inflate(R.layout.activity_map_dernier_trajet, frameLayout);

        setTitle( "Map Trajets");
        navigationView.setCheckedItem(position);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == BaseActivity.position) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }
        /*
        The activity has to be finished manually in order to guarantee the navigation hierarchy working.
        */
        finish();
        return super.onNavigationItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_map_trajet, menu);
        return true;
    }
}
