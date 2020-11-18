package ch.hesso.remembeer.database.async;

import android.app.Application;
import android.os.AsyncTask;

import ch.hesso.remembeer.RemembeerApp;
import ch.hesso.remembeer.database.entity.BreweryEntity;

/**
 * Classe UpdateBrewery
 * Mise a jour de la brasserie dans la database
 */

public class UpdateBrewery extends AsyncTask<BreweryEntity, Void, Void> {


    private Application app;
    private Exception exception;

    public UpdateBrewery(Application app) {
        this.app = app;
    }


    @Override
    protected Void doInBackground(BreweryEntity...breweryEntities) {
        try {
            for(BreweryEntity brewery: breweryEntities)
                ((RemembeerApp) app).getDatabase().breweryDao()
                        .update(brewery);
        } catch (Exception e) {
            exception = e;
        }
        return null;
    }
}
