package com.example.ourapplication_kohl_roux_m.ui;

import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.AlertDialog;
import com.example.ourapplication_kohl_roux_m.R;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 //       getLayoutInflater().inflate(R.layout.activity_main_app, frameLayout);
        getLayoutInflater().inflate(R.layout.activity_list_trajet, frameLayout);

        setTitle(getString(R.string.app_name));
        navigationView.setCheckedItem(R.id.nav_none);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(getString(R.string.app_name));
        navigationView.setCheckedItem(R.id.nav_none);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
//        alertDialog.setTitle(getString(R.string.action_logout));

        alertDialog.setTitle("Ceci devrait afficher une boite de dialogue d'alerte !");

        alertDialog.setCancelable(false);
//        alertDialog.setMessage(getString(R.string.logout_msg));
//        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.action_logout), (dialog, which) -> logout());
 //       alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.action_cancel), (dialog, which) -> alertDialog.dismiss());
        alertDialog.show();
    }
}
