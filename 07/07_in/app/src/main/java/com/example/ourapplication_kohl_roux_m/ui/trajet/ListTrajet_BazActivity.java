package com.example.ourapplication_kohl_roux_m.ui.trajet;

import android.app.AlertDialog;
import android.content.Intent;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModelProviders;

import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import androidx.appcompat.app.AppCompatActivity;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.adapter.RecyclerAdapter;
import com.example.ourapplication_kohl_roux_m.ui.BaseActivity;
import com.example.ourapplication_kohl_roux_m.ui.management.CreateTrip;
import com.example.ourapplication_kohl_roux_m.ui.management.consumptionInputs.NewTrajetConsumptionInput;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;
import com.example.ourapplication_kohl_roux_m.util.RecyclerViewItemClickListener;
import com.example.ourapplication_kohl_roux_m.viewModel.trajet.TrajetListByCarViewModel;
import com.example.ourapplication_kohl_roux_m.viewModel.trajet.TrajetListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListTrajet_BazActivity extends BaseActivity {

    private static final String TAG = "ListTrajet";

    private List<TrajetEntity> trajets;
    private RecyclerAdapter<TrajetEntity> adapter;
    private TrajetListViewModel viewModel;
    private long carId = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_list_trajet, frameLayout);

        setTitle( "Liste Trajets");
        navigationView.setCheckedItem(position);

        RecyclerView recyclerView = findViewById(R.id.trajetsRecyclerView);

        // use a linear layout manager

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        trajets = new ArrayList<>();
        adapter = new RecyclerAdapter<>(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position:" + position);
                Log.d(TAG, "clicked on: " + trajets.get(position).getName());

                TrajetEntity trajet = trajets.get(position);
                Intent intent = new Intent(ListTrajet_BazActivity.this, TrajetActivity.class);
                intent.setFlags(
                        Intent.FLAG_ACTIVITY_NO_ANIMATION |
                                Intent.FLAG_ACTIVITY_NO_HISTORY
                );
                intent.putExtra("Trajet", trajet);
//                intent.putExtra("TrajetId", trajets.get(position).getUid());
//                intent.putExtra("CardId", carId);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View v, int position) {
                Log.d(TAG, "longClicked position:" + position);
                Log.d(TAG, "longClicked on: " + trajets.get(position).getName());
                createDeleteDialog(position);
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(view -> {
                    Intent intent = new Intent(ListTrajet_BazActivity.this, CreateTrip.class);
                    intent.setFlags(
                            Intent.FLAG_ACTIVITY_NO_ANIMATION |
                                    Intent.FLAG_ACTIVITY_NO_HISTORY
                    );
                    intent.putExtra("CarId", carId);
                    startActivity(intent);
                }
        );

        TrajetListViewModel.Factory factory = new TrajetListViewModel.Factory(
                getApplication());
        viewModel = ViewModelProviders.of(this, factory).get(TrajetListViewModel.class);
        viewModel.getTrajetsviewMod().observe(this, trajetsL -> {
            if (trajetsL != null) {
                trajets = trajetsL;
                adapter.setData(trajets);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == BaseActivity.position) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }

        finish();
        return super.onNavigationItemSelected(item);

    }

    private void createDeleteDialog(final int position) {

        final TrajetEntity trajet = trajets.get(position);
        LayoutInflater inflater = LayoutInflater.from(this);

        final View view = inflater.inflate(R.layout.row_delete_item, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Trajet effacé, Trajet perdu");
        alertDialog.setCancelable(true);

        final TextView deleteMessage = view.findViewById(R.id.tv_delete_item);
        deleteMessage.setText("Attention, " + trajet.getName() + ", sera définitivement perdu !");

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Effacer", (dialog, which) -> {
            Toast toast = Toast.makeText(this, "Trajet effacé.", Toast.LENGTH_LONG);

            viewModel.deleteTrajet(trajet, new OnAsyncEventListener() {
                @Override
                public void onSuccess() {
                    Log.d(TAG, "deleteAccount: success");
                }

                @Override
                public void onFailure(Exception e) {
                    Log.d(TAG, "deleteAccount: failure", e);
                }
            }
            );
            toast.show();
        });
        alertDialog.show();
    }

}
