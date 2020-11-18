package ch.hesso.remembeer;

import android.app.Application;

import ch.hesso.remembeer.database.ApplicationDatabase;
import ch.hesso.remembeer.database.repository.BeerRepository;
import ch.hesso.remembeer.database.repository.BreweryRepository;
/**
 * Notre application
 * Contient les repository et la db
 * "le point de depart de notre application"
 */

public class RemembeerApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ApplicationDatabase getDatabase() {
        return ApplicationDatabase.getInstance(this);
    }

    public BreweryRepository getBreweryRepository() {
        return BreweryRepository.getInstance();
    }

    public BeerRepository getBeerRepository() {
        return BeerRepository.getInstance();
    }


}
