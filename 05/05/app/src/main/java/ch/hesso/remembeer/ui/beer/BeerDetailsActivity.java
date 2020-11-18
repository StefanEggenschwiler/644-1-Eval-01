package ch.hesso.remembeer.ui.beer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ch.hesso.remembeer.BaseActivity;
import ch.hesso.remembeer.R;
import ch.hesso.remembeer.database.entity.BeerEntity;
import ch.hesso.remembeer.utils.Helpers;
import ch.hesso.remembeer.viewmodel.beer.BeerViewModel;
/**
 * Activite pour la page "Details" pour la biere choisie
 * Etend la classe BaseActivity
 */
public class BeerDetailsActivity extends BaseActivity {
    private static final String TAG = "BeerDetailsActivity";

    private Long beerId;
    private BeerEntity beer;
    private TextView tvName, txt_description, txt_from, txt_capacity, txt_alcool;
    private BeerViewModel beerViewModel;
    private FloatingActionButton fl_fav;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_beer_details, frameLayout);
        setTitle(getString(R.string.beer_details));

        // fav_list = getSharedPreferences(BaseActivity.FAV_LIST, MODE_PRIVATE);

        tvName = findViewById(R.id.tvNameBeer);
        txt_alcool = findViewById(R.id.text_alcool);
        txt_from = findViewById(R.id.text_from);
        txt_capacity = findViewById(R.id.text_capacite);
        txt_description = findViewById(R.id.txt_description);
        image = findViewById(R.id.image_beer);
        fl_fav = findViewById(R.id.fl_like);


        navigationView.setCheckedItem(position);
        beerId = getIntent().getLongExtra("beerId", 0L);
        // isFavoris = fav_list.getBoolean(String.valueOf(beerId), false);
        // isFavoris = fav_list.getLong(String.valueOf(beerId), beerId) != null;
        BeerViewModel.Factory factory = new BeerViewModel.Factory(getApplication(), beerId);
        beerViewModel = ViewModelProviders.of(this, factory).get(BeerViewModel.class);
        beerViewModel.getBeer().observe(this, beer -> {
            if(beer != null) {
                this.beer = beer;
                this.initViewData();
            }
        });

    }

    private void setFavorisButton() {
        fl_fav.setImageResource(beer.isFavoris()?R.drawable.like_black_full:R.drawable.like_black_empty);
    }

    public void initViewData() {
        setFavorisButton();
        tvName.setText(beer.getName());
        txt_from.setText(beer.getFrom());
        txt_capacity.setText(beer.getCapacity() + " L");
        txt_alcool.setText(beer.getAlcool() + " %");
        txt_description.setText(beer.getDescription());
        Helpers.setImageFromPathOrDefault(image, beer.getImage(), beer);
    }

    /**
     * Methode pour lancer l'activite BeerEdit
     */
    public void editBeer(View view) {
        Log.d("BeerEditActivity", "edit");
        Intent intent = new Intent(this, BeerEditActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("beerId", beerId);
        startActivity(intent);
    }


    /**
     * Methode pour supprimer un objet biere
     */
    public void deleteBeer(View view) {
        new MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.beer_delete_title))
                .setMessage(getString(R.string.beer_delete_message, beer.getName()))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        Log.d(TAG, "Delete event");
                        beerViewModel.delete(beer);
                        finish();
                    }
                })
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(R.drawable.delete_yellow)
                .show();
    }



    public void setLikeBeer(View view) {
        beer.setFavoris(!beer.isFavoris());
        beerViewModel.update(beer);
        setFavorisButton();
    }

}
