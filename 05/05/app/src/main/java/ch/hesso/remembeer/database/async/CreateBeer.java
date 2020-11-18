package ch.hesso.remembeer.database.async;

import android.app.Application;
import android.os.AsyncTask;

import ch.hesso.remembeer.RemembeerApp;
import ch.hesso.remembeer.database.entity.BeerEntity;
import ch.hesso.remembeer.utils.OnAsyncEventListener;

/**
 * Classe CreateBeer
 * Insertion de la nouvelle biere dans la database
 */

public class CreateBeer extends AsyncTask<BeerEntity, Void, Void> {


    private Application app;
    private Exception exception;

    public CreateBeer(Application app) {
        this.app = app;
    }


    @Override
    protected Void doInBackground(BeerEntity...beerEntities) {
        try {
            for(BeerEntity beer: beerEntities)
                ((RemembeerApp) app).getDatabase().beerDao()
                        .insert(beer);
        } catch (Exception e) {
            exception = e;
        }
        return null;
    }

}
