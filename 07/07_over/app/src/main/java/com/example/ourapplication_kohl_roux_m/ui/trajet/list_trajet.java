package com.example.ourapplication_kohl_roux_m.ui.trajet;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.dbClass.Repository.TrajetRepository;
import com.example.ourapplication_kohl_roux_m.ui.BaseActivity;

public class list_trajet extends BaseActivity {

    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        setContentView(R.layout.activity_list_trajet);

        listView = findViewById(R.id.listview);

        TrajetRepository repositTrajet;
//        TrajetListByNameViewModel listTest = new TrajetListByNameViewModel(getApplicationContext(), "Jvéoboulo",repositTrajet);
        String[] values = new String[]{
                "TrajetEntity 24.10.20    12km", "TrajetEntity 25.10.20    12km", "TrajetEntity 26.10.20    13km"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(view.getContext(), stats_dernier_trajet.class);
                    startActivity(intent);
                }
            }
        });

      //  BottomNavigationView bottomNavigationView = findViewById(R.id.nav_vehicule);
      //  bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemReselectedListener);

 */
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
        getMenuInflater().inflate(R.menu.menu_list_trajet, menu);
        return true;
    }
}
