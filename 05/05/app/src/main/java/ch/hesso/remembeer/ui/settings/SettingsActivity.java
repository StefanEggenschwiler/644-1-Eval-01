package ch.hesso.remembeer.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ch.hesso.remembeer.BaseActivity;
import ch.hesso.remembeer.R;
/**
 * Activite pour la page Reglage
 */
public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_settings, frameLayout);
        setTitle("Settings");
        navigationView.setCheckedItem(position);
    }
}