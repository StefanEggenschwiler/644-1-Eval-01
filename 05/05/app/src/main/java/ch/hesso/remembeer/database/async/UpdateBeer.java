package ch.hesso.remembeer.database.async;

import android.app.Application;
import android.os.AsyncTask;

import ch.hesso.remembeer.RemembeerApp;
import ch.hesso.remembeer.database.entity.BeerEntity;
import ch.hesso.remembeer.utils.OnAsyncEventListener;

/**
 * Classe UpdateBeer
 * Mise a jour de la biere dans la database
 */

public class UpdateBeer extends AsyncTask<BeerEntity, Void, Void> {


    private Application app;
    private Exception exception;

    public UpdateBeer(Application app) {
        this.app = app;
    }


    @Override
    protected Void doInBackground(BeerEntity...beerEntities) {
        try {
            for(BeerEntity beer: beerEntities)
                ((RemembeerApp) app).getDatabase().beerDao()
                        .update(beer);
        } catch (Exception e) {
            exception = e;
        }
        return null;
    }
}
