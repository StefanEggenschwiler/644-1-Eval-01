package ch.hesso.remembeer.viewmodel.brewery;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ch.hesso.remembeer.RemembeerApp;
import ch.hesso.remembeer.database.entity.BreweryEntity;
import ch.hesso.remembeer.database.entity.BreweryEntity;
import ch.hesso.remembeer.database.repository.BreweryRepository;
import ch.hesso.remembeer.viewmodel.brewery.BreweryViewModel;
/**
 * Lien entre l'activité et le repository pour la liste des brasseries
 */
public class BreweryViewModel extends AndroidViewModel {

    private Long breweryId;
    private Application application;
    private BreweryRepository breweryRepository;
    private final MediatorLiveData<BreweryEntity> observableBrewery;

    public BreweryViewModel(@NonNull Application application, Long breweryId,
                         BreweryRepository breweryRepository) {
        super(application);
        this.breweryId = breweryId;
        this.application = application;
        this.breweryRepository = breweryRepository;
        this.observableBrewery = new MediatorLiveData<>();
        init();
    }
    private void init() {
        this.observableBrewery.setValue(null);
        this.observableBrewery.setValue(null);
    }

    public LiveData<BreweryEntity> getBrewery() {
        observableBrewery.addSource(breweryRepository.getById(this.breweryId, this.application),
                observableBrewery::setValue);
        return observableBrewery;
    }

    public void getBrewery(Long idbrewery) {
        breweryRepository.getById(idbrewery, application);
    }

    public void createBrewery(BreweryEntity brewery) { breweryRepository.create(brewery, application);
    }

    public void update(BreweryEntity brewery) {
        breweryRepository.update(brewery, application);
    }

    public void delete(BreweryEntity brewery) {
        breweryRepository.delete(brewery, application);
    }


    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;
        private final BreweryRepository repository;
        private Long breweryId;

        public Factory(@NonNull Application application, Long breweryId) {
            this.application = application;
            this.breweryId = breweryId;
            repository = ((RemembeerApp) application).getBreweryRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new BreweryViewModel(application, breweryId, repository);
        }
    }
}
