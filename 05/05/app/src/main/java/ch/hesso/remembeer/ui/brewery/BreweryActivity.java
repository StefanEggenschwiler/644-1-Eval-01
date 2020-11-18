package ch.hesso.remembeer.ui.brewery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ch.hesso.remembeer.BaseActivity;
import ch.hesso.remembeer.R;
import ch.hesso.remembeer.adapter.BeerRecyclerViewAdapter;
import ch.hesso.remembeer.adapter.BreweryRecyclerViewAdapter;
import ch.hesso.remembeer.database.entity.BeerEntity;
import ch.hesso.remembeer.database.entity.BreweryEntity;
import ch.hesso.remembeer.ui.beer.BeerDetailsActivity;
import ch.hesso.remembeer.ui.beer.BeerEditActivity;
import ch.hesso.remembeer.ui.beer.BeersActivity;
import ch.hesso.remembeer.utils.OnClickItem;
import ch.hesso.remembeer.viewmodel.beer.BeerListViewModel;
import ch.hesso.remembeer.viewmodel.brewery.BreweryListViewModel;
/**
 * Activite pour la page "Liste des brasseries"
 * Etend la classe BaseActivity
 */
public class BreweryActivity extends BaseActivity {
    private static final String TAG = "BreweryActivity";

    private List<BreweryEntity> brewery;
    private BreweryRecyclerViewAdapter adapter;
    private BreweryListViewModel breweryListViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public BreweryActivity() { }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_brewery, frameLayout);

        setTitle(getString(R.string.title_page_brewery));
        navigationView.setCheckedItem(position);

        recyclerView = findViewById(R.id.breweryRecycler);
        Log.d(TAG, "RecyclerView: " + recyclerView.toString());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new BreweryRecyclerViewAdapter(new OnClickItem() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked on: " + brewery.get(position).getName());

                Intent intent = new Intent(BreweryActivity.this, BreweryDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("breweryId", brewery.get(position).getIdBrewery());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View v, int position) {
                Log.d(TAG, "longClicked on: " + brewery.get(position).getName());
            }
        });

        BreweryListViewModel.Factory factory = new BreweryListViewModel.Factory(this.getApplication());
        breweryListViewModel = ViewModelProviders.of(this, factory).get(BreweryListViewModel.class);
        breweryListViewModel.getBeers().observe(this, brewery -> {
            if(brewery != null) {
                this.brewery = brewery;
                adapter.setData(brewery);
                recyclerView.setAdapter(adapter);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d("Brewery", item.toString());
        return super.onNavigationItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Log.d("Brewery", "back pressed");
        super.onBackPressed();
    }
    /**
     * Methode pour lancer la page BreweryEdit des le clic
     */

    public void addCliqued(View view) {
        Log.d("BreweryEditActivity", "edit");
        Intent intent = new Intent(this, BreweryEditActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

}