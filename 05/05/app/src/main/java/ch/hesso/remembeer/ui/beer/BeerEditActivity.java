package ch.hesso.remembeer.ui.beer;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import ch.hesso.remembeer.BaseActivity;
import ch.hesso.remembeer.R;
import ch.hesso.remembeer.database.entity.BeerEntity;
import ch.hesso.remembeer.utils.Helpers;
import ch.hesso.remembeer.utils.OnAsyncEventListener;
import ch.hesso.remembeer.viewmodel.beer.BeerViewModel;

/**
 * Activite pour la page "Modification" pour la liste des bieres
 * Etend la classe BaseActivity
 */
public class BeerEditActivity extends BaseActivity {
    private static final String TAG = "BeerEditActivity";

    private Long beerId;
    private BeerEntity beer = null;
    private EditText editName, editDesc, editFrom;
    private ImageView beerPhoto;
    private BeerViewModel beerViewModel;
    // private boolean createBeer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_edit);
        getLayoutInflater().inflate(R.layout.activity_beer_edit, frameLayout);
        setTitle(getString(R.string.beer_edit));

        editName = findViewById(R.id.edit_name);
        editDesc = findViewById(R.id.edit_desc);
        editFrom = findViewById(R.id.edit_from);
        beerPhoto = findViewById(R.id.beer_photo);

        navigationView.setCheckedItem(position);
        beerId = getIntent().getLongExtra("beerId", 0L);

        if(beerId == 0) {
            beer = new BeerEntity();
        }

        Log.d(TAG, "clicked on beerId: " + beerId);

        BeerViewModel.Factory factory = new BeerViewModel.Factory(getApplication(), beerId);
        beerViewModel = ViewModelProviders.of(this, factory).get(BeerViewModel.class);
        beerViewModel.getBeer().observe(this, beer -> {
            if(beer != null) {
                this.beer = beer;
                initViewData();
            }
        });
    }

    /**
     * Methode pour attribuer les informations aux différents champs
     */
    public void initViewData() {
        editName.setText(beer.getName());
        editDesc.setText(beer.getDescription());
        editFrom.setText(beer.getFrom());
        initImageBeer();
    }

    private void initImageBeer() {
        Helpers.setImageFromPathOrDefault(beerPhoto, beer.getImage(), beer);
//        File imgFile = new  File(beer.getImage());
//
//        if(imgFile.exists()) {
//            Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//            beerPhoto.setImageBitmap(bitmap);
//        } else {
//            beerPhoto.setBackgroundResource(R.drawable.remembeer_icon_light);
//        }
    }
    private void updateBeerImage(String path) {
        beer.setImage(path);
        beerViewModel.update(beer);
        initImageBeer();
    }

    /**
     * Methode pour verification des champs
     */
    private boolean isFormValid() {
        boolean failed = false;
        if(editName.getText().toString().trim().length() == 0 ) {
            failed = true;
            editName.setError( "Valeur obligatoire" );
        }
        return !failed;
    }

    /**
     * Methode pour attribuer les nouvelles valeurs choisies par le user
     */
    public void saveCliqued(View view) {
        Log.d("BeerEditActivity", "cliqued");

        if(isFormValid()){
            beer.setName(editName.getText().toString());
            beer.setDescription(editDesc.getText().toString());
            beer.setFrom(editFrom.getText().toString());
            if(beerId == 0) {
                beerViewModel.createBeer(beer);
            } else {
                beerViewModel.update(beer);
            }
            finish();
        }
    }

    /**
     * Methode pour modifier l'image
     * Propose plusieurs choix (prendre la photo, importation..)
     */
    public void changePicture(View view) {
        final CharSequence[] options = { "Prendre une photo", "Choisir depuis la gallerie","Annuler" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Prendre une photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choisir depuis la gallerie")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);

                } else if (options[item].equals("Annuler")) {
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
                        updateBeerImage(Helpers.saveBitmapToInternalStorage(image, this));
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        try {
                            Uri imageUri = data.getData();
                            Bitmap image = MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                                    imageUri);
                            updateBeerImage(Helpers.saveBitmapToInternalStorage(image, this));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }

            }

    }
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

}