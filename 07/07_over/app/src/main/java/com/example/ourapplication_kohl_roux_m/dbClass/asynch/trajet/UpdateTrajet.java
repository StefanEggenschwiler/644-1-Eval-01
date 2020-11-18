package com.example.ourapplication_kohl_roux_m.dbClass.asynch.trajet;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import com.example.ourapplication_kohl_roux_m.BaseApp;
import com.example.ourapplication_kohl_roux_m.dbClass.AppDataBase;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;

public class UpdateTrajet extends AsyncTask<TrajetEntity, Void, Void> {

    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;

    public UpdateTrajet(Application application, OnAsyncEventListener callback) {
        this.application = application;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(TrajetEntity... params) {
        try {
            for (TrajetEntity trajetEntity : params)
                ((BaseApp) application).getDatabase().trajetDao().update(trajetEntity);
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
