package ch.hesso.remembeer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import ch.hesso.remembeer.ui.about.AboutActivity;
import ch.hesso.remembeer.ui.beer.BeersActivity;
import ch.hesso.remembeer.ui.brewery.BreweryActivity;
import ch.hesso.remembeer.ui.favori.FavoriActivity;
import ch.hesso.remembeer.ui.home.HomeActivity;
import ch.hesso.remembeer.ui.search.SearchActivity;
import ch.hesso.remembeer.ui.settings.SettingsActivity;

/**
 * "Orchestrateur de l'ensemble de l'application"
 *  Gere la navigation entre autre
 */

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String USER_SETTINGS = "settings";
    public static final String FAV_LIST = "favoris";

    protected FrameLayout frameLayout;
    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;

    protected static int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        frameLayout = findViewById(R.id.content_main);
        drawerLayout = findViewById(R.id.base_drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.base_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    protected SharedPreferences getUserSettings() {
        return getSharedPreferences(USER_SETTINGS, MODE_PRIVATE);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {

        Log.d("BaseActivity", this.toString());
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        BaseActivity.position = 0;
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }



    /**
     * Methode qui permet de naviguer entre les pages grace a notre navigation view de gauche
     */

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == BaseActivity.position) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//            return false;
//        }
        BaseActivity.position = id;
        Intent intent = null;

        navigationView.setCheckedItem(id);

        if (id == R.id.nav_beers) {
            intent = new Intent(this, BeersActivity.class);
        } else if(id == R.id.nav_home) {
            intent = new Intent(this, HomeActivity.class);
        } else if(id == R.id.nav_brewery) {
            intent = new Intent(this, BreweryActivity.class);
        }else if(id == R.id.nav_search) {
            intent = new Intent(this, SearchActivity.class);
        }else if(id == R.id.nav_fav) {
            intent = new Intent(this, FavoriActivity.class);
//        }else if(id == R.id.nav_settings) {
//            intent = new Intent(this, SettingsActivity.class);
        }else if(id == R.id.nav_about) {
            intent = new Intent(this, AboutActivity.class);
        }


        if (intent != null) {
            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NO_ANIMATION
            );
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
