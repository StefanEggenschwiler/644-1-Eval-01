package project.bookyourtable.database.async.table;

import android.content.Context;
import android.os.AsyncTask;

import project.bookyourtable.BaseApp;
import project.bookyourtable.database.AppDatabase;
import project.bookyourtable.database.entity.TableEntity;
import project.bookyourtable.util.OnAsyncEventListener;

public class DeleteTable extends AsyncTask<TableEntity, Void, Void> {

    private Context context;
    private OnAsyncEventListener callback;
    private Exception exception;

    public DeleteTable(Context context, OnAsyncEventListener callBack) {
        this.context = context;
        this.callback = callback;

    }

    @Override
        protected Void doInBackground(TableEntity... params) {
            try{
                for(TableEntity table : params)
                    ((BaseApp) context).getDatabase().tableDao().delete(table);
            }
            catch (Exception e){
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
