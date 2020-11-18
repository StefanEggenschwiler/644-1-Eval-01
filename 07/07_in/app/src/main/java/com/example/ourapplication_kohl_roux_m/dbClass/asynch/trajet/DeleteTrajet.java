package com.example.ourapplication_kohl_roux_m.dbClass.asynch.trajet;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import com.example.ourapplication_kohl_roux_m.BaseApp;
import com.example.ourapplication_kohl_roux_m.dbClass.AppDataBase;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;

public class DeleteTrajet extends AsyncTask<TrajetEntity, Void, Void> {

    private Application application;
    private AppDataBase database;
    private OnAsyncEventListener callback;
    private Exception exception;

    public DeleteTrajet(Application application , OnAsyncEventListener callback) {
 //       database = AppDataBase.getInstance(context);
        this.application = application;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(TrajetEntity... params) {
        try {
            for (TrajetEntity trajetEntity : params)
                ((BaseApp) application).getDatabase().trajetDao().delete(trajetEntity);
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
