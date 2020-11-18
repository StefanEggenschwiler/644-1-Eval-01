package ch.hesso.remembeer.ui.about;


import android.os.Bundle;

import ch.hesso.remembeer.BaseActivity;
import ch.hesso.remembeer.R;
/**
 * Activite pour la page A propos
 * Etend la classe BaseActivity
 */
public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_about, frameLayout);
        //setTitle(getString(R.string.menu_about));
        navigationView.setCheckedItem(position);
    }
}