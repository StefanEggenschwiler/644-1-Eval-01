package com.example.ourapplication_kohl_roux_m.ui.management.consumptionInputs;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourapplication_kohl_roux_m.BaseApp;
import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.adapter.RecyclerAdapter;
import com.example.ourapplication_kohl_roux_m.dbClass.AppDataBase;
import com.example.ourapplication_kohl_roux_m.dbClass.Repository.TrajetRepository;
import com.example.ourapplication_kohl_roux_m.dbClass.asynch.trajet.CreateTrajet;
import com.example.ourapplication_kohl_roux_m.dbClass.asynch.trajet.UpdateTrajet;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;
import com.example.ourapplication_kohl_roux_m.ui.BaseActivity;
import com.example.ourapplication_kohl_roux_m.ui.management.CreateTrip;
import com.example.ourapplication_kohl_roux_m.ui.trajet.ListTrajet_BazActivity;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;
import com.example.ourapplication_kohl_roux_m.util.RecyclerViewItemClickListener;
import com.example.ourapplication_kohl_roux_m.viewModel.trajet.TrajetListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NewTrajetConsumptionInput extends BaseActivity {

    private static final String TAG = "InputConsumptionsActivity";

    private Application application;
    private List<TrajetEntity> trajets;
    private TrajetListViewModel viewModel;

    Button addElectButton;
    Button addFuelButton;
    FloatingActionButton save;

    EditText addElectItem;
    EditText addFuelItem;

    private Intent previousIntent;
    private Bundle bundle;
    public Bundle bundleTemp;

    private TrajetRepository trajetRepository;
    private String trajetDate;
    private long carId;

    private RecyclerAdapter<String> electAdapter;
    private List<String> electInputs;

    private RecyclerAdapter<String> fuelAdapter;
    private List<String> fuelInputs;
    private int electInputsHashcode;
    private int fuelInputsHashcode;
    private ViewModel viewmodel;
    private RecyclerView recyclerViewElect;
    private RecyclerView recyclerViewFuel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = getApplication();
        getLayoutInflater().inflate(R.layout.activity_comsumption_input, frameLayout);
        previousIntent = getIntent();
        bundle = previousIntent.getExtras();
        trajetDate = (String) bundle.get("TrajetDate");
        carId = (long) bundle.get("CarId");

        setTitle("Saisie des données de consommation");
        navigationView.setCheckedItem(position);

        addElectItem = (EditText) findViewById(R.id.saisieElect);
        addFuelItem = (EditText) findViewById(R.id.saisieCarb);

        addElectButton = (Button) findViewById(R.id.addElectButton);
        addFuelButton = (Button) findViewById(R.id.addFuelButton);

        recyclerViewElect = findViewById(R.id.electInputsRecyclerView);
        recyclerViewFuel = findViewById(R.id.fuelInputsRecyclerView);


        // use a linear layout manager
        RecyclerView.LayoutManager layoutManagerElect = new LinearLayoutManager(null);
        recyclerViewElect.setLayoutManager(layoutManagerElect);
        RecyclerView.LayoutManager layoutManagerFuel = new LinearLayoutManager(null);
        recyclerViewFuel.setLayoutManager(layoutManagerFuel);

        DividerItemDecoration dividerItemDecorationElect = new DividerItemDecoration(recyclerViewElect.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerViewElect.addItemDecoration(dividerItemDecorationElect);
        DividerItemDecoration dividerItemDecorationFuel = new DividerItemDecoration(recyclerViewFuel.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerViewFuel.addItemDecoration(dividerItemDecorationFuel);

        electInputs = new ArrayList<String>();
        electInputsHashcode = electInputs.hashCode();

        electAdapter = new RecyclerAdapter<>(new RecyclerViewItemClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position:" + position);
                Log.d(TAG, "clicked on: " + electInputs.get(position).toString());

                Toast toast = Toast.makeText(NewTrajetConsumptionInput.this, "Appui long pour modifier", Toast.LENGTH_LONG);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onItemLongClick(View v, int position) {
                Log.d(TAG, "longClicked position:" + position  );
                Log.d(TAG, "longClicked on: " + electInputs.get(position));

                createModifyDialog(position, electInputsHashcode);
            }
        });

        fuelInputs = new ArrayList<String>();
        fuelInputsHashcode = fuelInputs.hashCode();

        fuelAdapter = new RecyclerAdapter<String>(new RecyclerViewItemClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position:" + position);
                Log.d(TAG, "clicked on: " + fuelInputs.get(position).toString());

                Toast toast = Toast.makeText(NewTrajetConsumptionInput.this, "Appui long pour modifier", Toast.LENGTH_LONG);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onItemLongClick(View v, int position) {
                Log.d(TAG, "longClicked position:" + position);
                Log.d(TAG, "longClicked on: " + fuelInputs.get(position).toString());

                createModifyDialog(position, fuelInputsHashcode);
            }
        });

        electAdapter.setData(electInputs);
        fuelAdapter.setData(fuelInputs);

        recyclerViewElect.setAdapter(electAdapter);
        recyclerViewFuel.setAdapter(fuelAdapter);

        addElectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String newValue = addElectItem.getText().toString();

                electInputs.add(newValue);
                electAdapter.setData(electInputs);

                addElectItem.setText("");

                recyclerViewElect.setAdapter(electAdapter);
                recyclerViewElect.refreshDrawableState();

                bundleTemp = getIntent().getBundleExtra("tempSaisiesConsom");
            }
        });

        addFuelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newValue = addFuelItem.getText().toString();

                fuelInputs.add(newValue);
                fuelAdapter.setData(fuelInputs);
                addFuelItem.setText("");

                recyclerViewFuel.setAdapter(fuelAdapter);
                recyclerViewFuel.refreshDrawableState();

                bundleTemp = getIntent().getBundleExtra("tempSaisiesConsom");
            }
        });

        save = findViewById(R.id.saveTrip);
        save.setOnClickListener(view -> {
                saveChanges();
            }
        );
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

    @SuppressLint("StaticFieldLeak")
    private void createModifyDialog(final int position, final int hashcodeInputs) {

        List <String> aModif = getList(hashcodeInputs);
 //       RecyclerView recyclerAmodif =  getRecyclerView(hashcodeInputs);

        final String value = (String) getList(hashcodeInputs).get(position);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View view = inflater.inflate(R.layout.row_delete_item, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.title_activity_modify_Inputs));
        alertDialog.setCancelable(false);

        final TextView modifyMessage = view.findViewById(R.id.editNumberToModify);
        modifyMessage.setText(value);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Modify", (dialog, which) -> {
            Toast toast = Toast.makeText(this, "Valeur modifiée", Toast.LENGTH_LONG);

            String newValue = modifyMessage.getText().toString();

            aModif.set(position, newValue);
            getRecyclerView(hashcodeInputs).refreshDrawableState();

            toast.show();
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Delete", (dialog, which) -> {
            Toast toast = Toast.makeText(this, "Valeur effacée", Toast.LENGTH_LONG);

            aModif.remove(position);
            getRecyclerView(hashcodeInputs).refreshDrawableState();

            toast.show();
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel", (dialog, which) -> alertDialog.dismiss());
        alertDialog.setView(view);
        alertDialog.show();
    }

    private List <String> getList(int hashcode){

        if(hashcode == electInputsHashcode)
            return electInputs;
        return fuelInputs;
    }
    private RecyclerView getRecyclerView(int hashcode){

        if(hashcode == electInputsHashcode){
            electAdapter.setData(electInputs);
            recyclerViewElect.setAdapter(electAdapter);
            return recyclerViewElect;
        }
        fuelAdapter.setData(fuelInputs);
        recyclerViewElect.setAdapter(fuelAdapter);
        return recyclerViewFuel;
    }

    private void saveChanges() {
/*
        TrajetEntity upDTrajet = ((BaseApp)getApplication()).getDatabase().trajetDao().getByDate(trajetDate);

        upDTrajet.electricityTot = calcul(electInputs) ;
        upDTrajet.gasolinTot = calcul(fuelInputs);
        upDTrajet.kmTot = ;
        upDTrajet.totDeep = ;
        upDTrajet.totRise = ;

        new UpdateTrajet(getApplication(), new OnAsyncEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onSuccess() {
                setResponse(true);
                Log.d(TAG, "Enregistrement consommation : succès");
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "Enregistrement consommation : échoué", e);
            }
        }).execute(upDTrajet);

 */
        setResponse(true);
    }
    private void setResponse(Boolean response) {
        if (response) {
            Intent intent = new Intent(NewTrajetConsumptionInput.this, ListTrajet_BazActivity.class);
            intent.putExtra("CarId", carId);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, "Trajet non enregistré", Toast.LENGTH_LONG);

        }
    }

    private double calcul(List<String> saisies) {
        double result = 0;
        for (String item : saisies){
            result+=(Double.valueOf(item));
        }

        return result;
    }


}
