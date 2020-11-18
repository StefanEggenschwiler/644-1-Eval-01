package ch.hesso.remembeer.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ch.hesso.remembeer.RemembeerApp;
import ch.hesso.remembeer.database.async.CreateBeer;
import ch.hesso.remembeer.database.async.DeleteBeer;
import ch.hesso.remembeer.database.async.UpdateBeer;
import ch.hesso.remembeer.database.entity.BeerEntity;
import ch.hesso.remembeer.utils.OnAsyncEventListener;
/**
 * Classe qui fait le lien entre la DAO et la ViewModel
 * pour les bieres
 */
public class BeerRepository {

    private static BeerRepository instance;

    private BeerRepository() { }

    public static BeerRepository getInstance() {
        if (instance == null) {
            synchronized (BeerRepository.class) {
                if (instance == null) {
                    instance = new BeerRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<BeerEntity> getById(final Long id, Application app) {
        return ((RemembeerApp) app).getDatabase().beerDao().getById(id);
    }

    public LiveData<List<BeerEntity>> getAll(Application app) {
        return ((RemembeerApp) app).getDatabase().beerDao().getAll();
    }

    public void create(final BeerEntity beer, Application app) {
        new CreateBeer(app).execute(beer);
    }

    public void update(final BeerEntity beer, Application app) {
        new UpdateBeer(app).execute(beer);
    }

    public void delete(final BeerEntity beer, Application app) {
        new DeleteBeer(app).execute(beer);
    }

}
