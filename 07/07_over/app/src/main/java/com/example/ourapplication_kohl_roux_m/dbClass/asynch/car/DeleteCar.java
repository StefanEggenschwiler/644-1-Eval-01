package com.example.ourapplication_kohl_roux_m.dbClass.asynch.car;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import com.example.ourapplication_kohl_roux_m.BaseApp;
import com.example.ourapplication_kohl_roux_m.dbClass.AppDataBase;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.CarEntity;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;

public class DeleteCar extends AsyncTask<CarEntity, Void, Void> {

    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;

    public DeleteCar(Application application, OnAsyncEventListener callback) {
        this.application = application;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(CarEntity... params) {
        try {
            for (CarEntity carEntity : params)
                ((BaseApp) application).getDatabase().carDao().delete(carEntity);
        } catch (Exception e) {
            exception = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (callback != null) {
            if (exception == null) {
                callback.onSuccess();
            } else {
                callback.onFailure(exception);
            }
        }
    }

}
