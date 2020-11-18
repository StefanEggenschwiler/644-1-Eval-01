package ch.hesso.remembeer.ui.brewery;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

import ch.hesso.remembeer.BaseActivity;
import ch.hesso.remembeer.R;
import ch.hesso.remembeer.database.entity.BeerEntity;
import ch.hesso.remembeer.database.entity.BreweryEntity;
import ch.hesso.remembeer.utils.Helpers;
import ch.hesso.remembeer.viewmodel.beer.BeerViewModel;
import ch.hesso.remembeer.viewmodel.brewery.BreweryViewModel;

public class BreweryEditActivity extends BaseActivity {
    private static final String TAG = "BreweryEditActivity";

    private Long breweryId;
    private BreweryEntity brewery = null;
    private EditText edit_name, edit_desc, edit_from;
    private BreweryViewModel breweryViewModel;
    private ImageView brewery_photo;
    // private boolean createBrewery = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_edit);
        getLayoutInflater().inflate(R.layout.activity_brewery_edit, frameLayout);
        setTitle(getString(R.string.brewery_edit));

        edit_name = findViewById(R.id.edit_name);
        edit_desc = findViewById(R.id.edit_desc);
        edit_from = findViewById(R.id.edit_from);
        brewery_photo = findViewById(R.id.brewery_photo);


        navigationView.setCheckedItem(position);
        breweryId = getIntent().getLongExtra("breweryId", 0L);

        if(breweryId == 0) {
            brewery = new BreweryEntity();
        }

        Log.d(TAG, "clicked on breweryId: " + breweryId);

        BreweryViewModel.Factory factory = new BreweryViewModel.Factory(getApplication(), breweryId);
        breweryViewModel = ViewModelProviders.of(this, factory).get(BreweryViewModel.class);
        breweryViewModel.getBrewery().observe(this, brewery -> {
            if(brewery != null) {
                this.brewery = brewery;
                initViewData();
            }
        });
    }

    public void initViewData() {
        edit_name.setText(brewery.getName());
        edit_from.setText(brewery.getCity());
        edit_desc.setText(brewery.getDescription());
        initImageBrewery();
    }

    private void initImageBrewery() {
        Helpers.setImageFromPathOrDefault(brewery_photo, brewery.getImage(), brewery);
    }

    private void updateBreweryImage(String path) {
        brewery.setImage(path);
        breweryViewModel.update(brewery);
        initImageBrewery();
    }

    private boolean isFormValid() {
        boolean failed = false;
        if(edit_name.getText().toString().trim().length() == 0 ) {
            failed = true;
            edit_name.setError( "Valeur obligatoire" );
        }
        return !failed;
    }


    public void saveCliqued(View view) {
        Log.d("BreweryEditActivity", "cliqued");

        if(isFormValid()){
            brewery.setName(edit_name.getText().toString());
            brewery.setDescription(edit_desc.getText().toString());
            brewery.setCity(edit_from.getText().toString());
            //TODO : sauvegarder l'image
            // brewery.setImage(brewery_photo);
            if(breweryId == 0) {
                breweryViewModel.createBrewery(brewery);
            } else {
                breweryViewModel.update(brewery);
            }
            finish();
        }
    }

    public void changePicture(View view) {
        final CharSequence[] options = { "Prendre une photo", "Choisir depuis la gallerie","Annuler" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choisis ton image");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Prendre une photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choisir depuis la gallerie")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap image = (Bitmap) data.getExtras().get("data");
                        updateBreweryImage(Helpers.saveBitmapToInternalStorage(image, this));
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        try {
                            Uri imageUri = data.getData();
                            Bitmap image = MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                                    imageUri);
                            updateBreweryImage(Helpers.saveBitmapToInternalStorage(image, this));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

        }

    }
}