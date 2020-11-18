package ch.hesso.remembeer.database.async;

import android.app.Application;
import android.os.AsyncTask;

import ch.hesso.remembeer.RemembeerApp;
import ch.hesso.remembeer.database.entity.BreweryEntity;

/**
 * Classe DeleteBrewery
 * Suppression de la brasserie dans la database
 */

public class DeleteBrewery extends AsyncTask<BreweryEntity, Void, Void> {


    private Application app;
    private Exception exception;

    public DeleteBrewery(Application app) {
        this.app = app;
    }


    @Override
    protected Void doInBackground(BreweryEntity...breweryEntities) {
        try {
            for(BreweryEntity brewery: breweryEntities)
                ((RemembeerApp) app).getDatabase().breweryDao()
                        .delete(brewery);
        } catch (Exception e) {
            exception = e;
        }
        return null;
    }
}
