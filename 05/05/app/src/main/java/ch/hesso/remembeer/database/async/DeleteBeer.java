package ch.hesso.remembeer.database.async;

import android.app.Application;
import android.os.AsyncTask;

import ch.hesso.remembeer.RemembeerApp;
import ch.hesso.remembeer.database.entity.BeerEntity;

/**
 * Classe DeleteBeer
 * Suppression de la biere dans la database
 */

public class DeleteBeer extends AsyncTask<BeerEntity, Void, Void> {


    private Application app;
    private Exception exception;

    public DeleteBeer(Application app) {
        this.app = app;
    }


    @Override
    protected Void doInBackground(BeerEntity...beerEntities) {
        try {
            for(BeerEntity beer: beerEntities)
                ((RemembeerApp) app).getDatabase().beerDao()
                        .delete(beer);
        } catch (Exception e) {
            exception = e;
        }
        return null;
    }

}
