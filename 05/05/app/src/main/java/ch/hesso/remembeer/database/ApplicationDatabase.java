package ch.hesso.remembeer.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

import ch.hesso.remembeer.database.dao.BeerDao;
import ch.hesso.remembeer.database.dao.BreweryDao;
import ch.hesso.remembeer.database.entity.BeerEntity;
import ch.hesso.remembeer.database.entity.BreweryEntity;
import ch.hesso.remembeer.utils.Constants;
/**
 * "Coeur de notre base de donnee"
 * Tout ce qui consiste a la création et le maintien de la base de donnée
 * Initialiser la base de donnee
 */
@Database(entities = {BeerEntity.class, BreweryEntity.class}, version = 1, exportSchema = false)
public abstract class ApplicationDatabase extends RoomDatabase {

    private static final String TAG = "ApplicationDatabase";

    public abstract BeerDao beerDao();

    public abstract BreweryDao breweryDao();

    private static ApplicationDatabase instance;

    private final MutableLiveData<Boolean> isDbCreated = new MutableLiveData<>();

    public static ApplicationDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (ApplicationDatabase.class) {
                if (instance == null) {
                    instance = buildDatabase(context.getApplicationContext());
                    instance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return instance;
    }
    /**
     * Methode d'nitionalisation de notre base de donnee
     */
    private static ApplicationDatabase buildDatabase(final Context appContext) {
        Log.i(TAG, "Database will be initialized.");
        return Room.databaseBuilder(appContext, ApplicationDatabase.class, Constants.DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(() -> {
                            ApplicationDatabase database = ApplicationDatabase.getInstance(appContext);
                            initDemoDB(database);
                            // notify that the database was created and it's ready to be used
                            database.setIsDbCreated();
                        });
                    }
                }).build();
    }
    /**
     * Supprimer les donnees actuelles
     * Ajouter les donnnes de demo
     */
    public static void initDemoDB(final ApplicationDatabase db) {
        Log.i(TAG, "initDemoDB");
        Executors.newSingleThreadExecutor().execute(() -> {
            db.runInTransaction(() -> {
                Log.i("ApplicationDatabase", "Wipe database.");
                db.beerDao().deleteAll();
                db.breweryDao().deleteAll();

                DbInitializer.initDB(db);
            });
        });
    }

    /**
     * Mise a jour par la methode setIsDbCreated pour savoir si la base de donnee existe ou pas
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(Constants.DATABASE_NAME).exists()) {
            Log.i(TAG, "Database initialized.");
            setIsDbCreated();
        }
    }

    private void setIsDbCreated() {
        isDbCreated.postValue(true);
    }

    public LiveData<Boolean> getIsDbCreated() {
        return isDbCreated;
    }


}
