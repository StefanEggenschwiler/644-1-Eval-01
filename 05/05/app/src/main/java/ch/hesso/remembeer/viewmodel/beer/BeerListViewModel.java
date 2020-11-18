package ch.hesso.remembeer.viewmodel.beer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import ch.hesso.remembeer.RemembeerApp;
import ch.hesso.remembeer.database.entity.BeerEntity;
import ch.hesso.remembeer.database.repository.BeerRepository;
import ch.hesso.remembeer.utils.OnAsyncEventListener;
/**
 * Lien entre l'activité et le repository pour la liste des bières
 */

public class BeerListViewModel extends AndroidViewModel {

    private Application application;
    private BeerRepository beerRepository;
    private final MediatorLiveData<List<BeerEntity>> observableBeer;

    public BeerListViewModel(@NonNull Application application, BeerRepository beerRepository) {
        super(application);
        this.application = application;
        this.beerRepository = beerRepository;
        this.observableBeer = new MediatorLiveData<>();
        init();
    }
    private void init() {
        this.observableBeer.setValue(null);
        LiveData<List<BeerEntity>> beers = beerRepository.getAll(this.application);
        observableBeer.addSource(beers, observableBeer::setValue);
    }

    public LiveData<List<BeerEntity>> getBeers() {
        return observableBeer;
    }



    /**
     * Creer une instance du ViewModel
     */

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;
        private final BeerRepository repository;

        public Factory(@NonNull Application application) {
            this.application = application;
            repository = ((RemembeerApp) application).getBeerRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new BeerListViewModel(application, repository);
        }
    }
}
