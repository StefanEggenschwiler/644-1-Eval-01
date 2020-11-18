package ch.hesso.remembeer.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ch.hesso.remembeer.RemembeerApp;
import ch.hesso.remembeer.database.association.BreweryWithBeers;
import ch.hesso.remembeer.database.async.CreateBeer;
import ch.hesso.remembeer.database.async.CreateBrewery;
import ch.hesso.remembeer.database.async.DeleteBeer;
import ch.hesso.remembeer.database.async.DeleteBrewery;
import ch.hesso.remembeer.database.async.UpdateBeer;
import ch.hesso.remembeer.database.async.UpdateBrewery;
import ch.hesso.remembeer.database.entity.BeerEntity;
import ch.hesso.remembeer.database.entity.BreweryEntity;
/**
 * Classe qui fait le lien entre la DAO et la ViewModel
 * pour les brasseries
 */
public class BreweryRepository {

    private static BreweryRepository instance;

    private BreweryRepository() { }

    public static BreweryRepository getInstance() {
        if (instance == null) {
            synchronized (BeerRepository.class) {
                if (instance == null) {
                    instance = new BreweryRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<BreweryEntity> getById(final Long id, Application app) {
        return ((RemembeerApp) app).getDatabase().breweryDao().getById(id);
    }

    public LiveData<List<BreweryEntity>> getAll(Application app) {
        return ((RemembeerApp) app).getDatabase().breweryDao().getAll();
    }

    public LiveData<List<BreweryWithBeers>> getBreweryWithBeers(final Long id, Application application) {
        return ((RemembeerApp) application).getDatabase().breweryDao().getBreweryWithBeers(id);
    }

    public void create(final BreweryEntity brewery, Application app) {
        new CreateBrewery(app).execute(brewery);
    }

    public void update(final BreweryEntity brewery, Application app) {
        new UpdateBrewery(app).execute(brewery);
    }

    public void delete(final BreweryEntity brewery, Application app) {
        new DeleteBrewery(app).execute(brewery);
    }
}
