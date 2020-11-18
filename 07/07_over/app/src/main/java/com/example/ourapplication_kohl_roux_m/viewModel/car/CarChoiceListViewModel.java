package com.example.ourapplication_kohl_roux_m.viewModel.car;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.ourapplication_kohl_roux_m.BaseApp;
import com.example.ourapplication_kohl_roux_m.dbClass.Repository.CarRepository;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.CarEntity;

import java.util.List;

public class CarChoiceListViewModel extends AndroidViewModel {

    private Application application;

    private CarRepository repository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<CarEntity>> observableCars;
//    private final MediatorLiveData<List<AccountEntity>> observableOwnAccounts;

    public CarChoiceListViewModel(@NonNull Application application,
                                CarRepository carRepository) {
        super(application);

        this.application = application;

        repository = carRepository;

        observableCars = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        observableCars.setValue(null);

        LiveData<List<CarEntity>> carAllList =
                carRepository.getAllCar(application);
        LiveData<List<CarEntity>> ownAccounts = repository.getAllCar(application);

        // observe the changes of the entities from the database and forward them
        observableCars.addSource(carAllList, observableCars::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final CarRepository carRepository;

        public Factory(@NonNull Application application) {
            this.application = application;
            carRepository = ((BaseApp) application).getCarRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new CarChoiceListViewModel(application, carRepository);
        }
    }

    /**
     * Expose the LiveData ClientAccounts query so the UI can observe it.
     */
    public LiveData<List<CarEntity>> getAllCar() {
        return observableCars;
    }

    /**
     * Expose the LiveData AccountEntities query so the UI can observe it.
     */
    public LiveData<List<CarEntity>> getOwnAccounts() {
        return observableCars;
    }

 /*   public void insertTunedCar(CarEntity carEntity, OnAsyncEventListener callback) {
        repository.insertTunedCar(carEntity, callback, application);
    }

    public void executeTransaction(final AccountEntity sender, final AccountEntity recipient,
                                   OnAsyncEventListener callback) {
        repository.transaction(sender, recipient, callback, application);

    }
  */
}
