package ch.hesso.remembeer.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import ch.hesso.remembeer.BaseActivity;
import ch.hesso.remembeer.R;
import ch.hesso.remembeer.ui.brewery.BreweryEditActivity;
import ch.hesso.remembeer.ui.search.SearchActivity;
/**
 * Activite pour la page Home
 */
public class HomeActivity extends BaseActivity {

    private static final String TAG = "HOME_ACTIVITY";

    ImageButton search_btn;

    public HomeActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_home, frameLayout);
        setTitle(getString(R.string.menu_home));
        navigationView.setCheckedItem(position);
        search_btn =(ImageButton)findViewById(R.id.search_btn);

    }

    /**
     * Renvoi a la page SearchActivity lorsque le user clique sur la loupe
     */
    public void goSearch(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}