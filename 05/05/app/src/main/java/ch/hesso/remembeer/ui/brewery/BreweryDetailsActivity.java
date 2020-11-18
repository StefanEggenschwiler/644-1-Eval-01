package ch.hesso.remembeer.ui.brewery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ch.hesso.remembeer.BaseActivity;
import ch.hesso.remembeer.R;
import ch.hesso.remembeer.database.entity.BeerEntity;
import ch.hesso.remembeer.database.entity.BreweryEntity;
import ch.hesso.remembeer.ui.beer.BeerEditActivity;
import ch.hesso.remembeer.utils.Helpers;
import ch.hesso.remembeer.viewmodel.beer.BeerViewModel;
import ch.hesso.remembeer.viewmodel.brewery.BreweryViewModel;
/**
 * Activite pour la page "Details" pour la brasserie choisie
 * Etend la classe BaseActivity
 */
public class BreweryDetailsActivity extends BaseActivity {
    private static final String TAG = "BreweryDetailsActivity";

    private Long breweryId;
    private BreweryEntity brewery;
    private TextView NameBrewery, txt_description, txt_from, text_web;
    private ImageView imageBrewery;
    private BreweryViewModel breweryViewModel;
    private FloatingActionButton fl_fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_brewery_details, frameLayout);
        setTitle(getString(R.string.brewery_details));

        NameBrewery = findViewById(R.id.NameBrewery);
        txt_from = findViewById(R.id.text_from);
        txt_description = findViewById(R.id.txt_description);
        text_web = findViewById(R.id.text_web);
        imageBrewery = findViewById(R.id.imageBrewery);
        fl_fav = findViewById(R.id.fl_like);


        navigationView.setCheckedItem(position);
        breweryId = getIntent().getLongExtra("breweryId", 0L);
        Log.d(TAG, "clicked on breweryId: " + breweryId);

        text_web.setMovementMethod(LinkMovementMethod.getInstance());

        BreweryViewModel.Factory factory = new BreweryViewModel.Factory(getApplication(), breweryId);
        breweryViewModel = ViewModelProviders.of(this, factory).get(BreweryViewModel.class);
        breweryViewModel.getBrewery().observe(this, brewery -> {
            if(brewery != null) {
                this.brewery = brewery;
                this.initViewData();
            }
        });

    }

    private void setFavorisButton() {
       fl_fav.setImageResource(brewery.isFavoris()?R.drawable.like_black_full:R.drawable.like_black_empty);
    }

    public void initViewData() {
        setFavorisButton();
        NameBrewery.setText(brewery.getName());
        txt_from.setText(brewery.getCity());
        txt_description.setText(brewery.getDescription());
        text_web.setText(brewery.getWeb());
        Helpers.setImageFromPathOrDefault(imageBrewery, brewery.getImage(), brewery);
    }

    /**
     * Methode pour lancer l'activite BreweryEdit
     */
    public void editBrewery(View view) {
        Log.d("BreweryEditActivity", "edit");
        Intent intent = new Intent(this, BreweryEditActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("breweryId", breweryId);
        startActivity(intent);
    }

    /**
     * Methode pour supprimer un objet brasserie
     */
    public void deleteBrewery(View view) {
        new MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.brewery_delete_title))
                .setMessage(getString(R.string.brewery_delete_message, brewery.getName()))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        Log.d(TAG, "Delete event");
                        breweryViewModel.delete(brewery);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(R.drawable.delete_yellow)
                .show();
    }
    public void setLikeBrewery(View view) {
        brewery.setFavoris(!brewery.isFavoris());
        breweryViewModel.update(brewery);
        setFavorisButton();
    }
}