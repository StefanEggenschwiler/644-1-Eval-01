package ch.hesso.remembeer.ui.search;


import android.os.Bundle;
import android.widget.TextView;

import ch.hesso.remembeer.BaseActivity;
import ch.hesso.remembeer.R;
/**
 * Activite pour la page Recherche
 */
public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_search, frameLayout);
        setTitle(getString(R.string.menu_search));
        navigationView.setCheckedItem(position);
    }
}