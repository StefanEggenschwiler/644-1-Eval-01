package ch.hesso.remembeer.ui.favori;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ch.hesso.remembeer.BaseActivity;
import ch.hesso.remembeer.R;
/**
 * Activite pour la page Favori
 */
public class FavoriActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_favori, frameLayout);
        setTitle("Favoris");
        navigationView.setCheckedItem(position);
    }
}