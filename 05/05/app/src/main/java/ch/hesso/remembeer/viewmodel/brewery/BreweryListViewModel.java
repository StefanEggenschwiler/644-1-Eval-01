package ch.hesso.remembeer.viewmodel.brewery;

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
import ch.hesso.remembeer.database.entity.BreweryEntity;
import ch.hesso.remembeer.database.repository.BeerRepository;
import ch.hesso.remembeer.database.repository.BreweryRepository;
import ch.hesso.remembeer.viewmodel.beer.BeerListViewModel;
/**
 * Lien entre l'activité et le repository pour la liste des brasseries
 */


public class BreweryListViewModel extends AndroidViewModel {

    private Application application;
    private BreweryRepository breweryRepository;
    private final MediatorLiveData<List<BreweryEntity>> observableBrewery;

    public BreweryListViewModel(@NonNull Application application, BreweryRepository breweryRepository) {
        super(application);
        this.application = application;
        this.breweryRepository = breweryRepository;
        this.observableBrewery = new MediatorLiveData<>();
        init();
    }
    private void init() {
        this.observableBrewery.setValue(null);
        LiveData<List<BreweryEntity>> brewery = breweryRepository.getAll(this.application);
        observableBrewery.addSource(brewery, observableBrewery::setValue);
    }

    public LiveData<List<BreweryEntity>> getBeers() {
        return observableBrewery;
    }


    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;
        private final BreweryRepository repository;

        public Factory(@NonNull Application application) {
            this.application = application;
            repository = ((RemembeerApp) application).getBreweryRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new BreweryListViewModel(application, repository);
        }
    }
}