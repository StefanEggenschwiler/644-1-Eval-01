package ch.hesso.remembeer.ui.beer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import ch.hesso.remembeer.BaseActivity;
import ch.hesso.remembeer.R;
import ch.hesso.remembeer.adapter.BeerRecyclerViewAdapter;
import ch.hesso.remembeer.database.entity.BeerEntity;
import ch.hesso.remembeer.utils.OnClickItem;
import ch.hesso.remembeer.viewmodel.beer.BeerListViewModel;
/**
 * Activite pour la page "Liste des bieres"
 * Etend la classe BaseActivity
 */
public class BeersActivity extends BaseActivity {

    private static final String TAG = "BeerActivity";

    private List<BeerEntity> beers;
    private BeerRecyclerViewAdapter adapter;
    private BeerListViewModel beerListViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public BeersActivity() { }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_beers, frameLayout);

        setTitle(getString(R.string.title_page_beers));
        navigationView.setCheckedItem(position);

        recyclerView = findViewById(R.id.beersRecycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new BeerRecyclerViewAdapter(this, new OnClickItem() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked on: " + beers.get(position).getName());
                Intent intent = new Intent(BeersActivity.this, BeerDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("beerId", beers.get(position).getIdBeer());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View v, int position) {
                Log.d(TAG, "longClicked on: " + beers.get(position).getName());
            }
        });
        BeerListViewModel.Factory factory = new BeerListViewModel.Factory(this.getApplication());
        beerListViewModel = ViewModelProviders.of(this, factory).get(BeerListViewModel.class);
        beerListViewModel.getBeers().observe(this, beers -> {
            if(beers != null) {
                this.beers = beers;
                adapter.setData(beers);
                recyclerView.setAdapter(adapter);
            }
        });
    }


    /**
     * Methode pour lancer la page BeerEdit des le clic
     */
    public void addCliqued(View view) {
        Log.d(TAG, "add");
        Intent intent = new Intent(this, BeerEditActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

}