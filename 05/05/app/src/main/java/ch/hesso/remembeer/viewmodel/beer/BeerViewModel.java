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
 * Lien entre l'activité et le repository pour un objet unique
 */

public class BeerViewModel extends AndroidViewModel {

    private Long beerId;
    private Application application;
    private BeerRepository beerRepository;
    private final MediatorLiveData<BeerEntity> observableBeer;

    public BeerViewModel(@NonNull Application application, Long beerId,
                         BeerRepository beerRepository) {
        super(application);
        this.beerId = beerId;
        this.application = application;
        this.beerRepository = beerRepository;
        this.observableBeer = new MediatorLiveData<>();
        init();
    }
    private void init() {
        this.observableBeer.setValue(null);
        this.observableBeer.setValue(null);
    }

    public LiveData<BeerEntity> getBeer() {
        observableBeer.addSource(beerRepository.getById(this.beerId, this.application),
                observableBeer::setValue);
        return observableBeer;
    }

    public void getBeer(Long idBeer) {
        beerRepository.getById(idBeer, application);
    }

    public void createBeer(BeerEntity beer) {
        beerRepository.create(beer, application);
    }

    public void update(BeerEntity beer) {
        beerRepository.update(beer, application);
    }

    public void delete(BeerEntity beer) {
        beerRepository.delete(beer, application);
    }


    /**
     * Creer une instance du ViewModel
     */

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;
        private final BeerRepository repository;
        private Long beerId;

        public Factory(@NonNull Application application, Long beerId) {
            this.application = application;
            this.beerId = beerId;
            repository = ((RemembeerApp) application).getBeerRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new BeerViewModel(application, beerId, repository);
        }
    }
}
