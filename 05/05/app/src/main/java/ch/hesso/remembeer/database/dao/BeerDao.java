package ch.hesso.remembeer.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ch.hesso.remembeer.database.entity.BeerEntity;


/**
 * Data Access Object de la classe Beer
 */


@Dao
public abstract class BeerDao {

    @Query("SELECT * FROM beer WHERE idBeer = :id")
    public abstract LiveData<BeerEntity> getById(Long id);

    @Query("SELECT * FROM beer")
    public abstract LiveData<List<BeerEntity>> getAll();

    @Insert
    public abstract void insert(BeerEntity beer);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<BeerEntity> beers);

    @Update
    public abstract void update(BeerEntity beer);

    @Delete
    public abstract void delete(BeerEntity beer);

    @Query("DELETE FROM beer")
    public abstract void deleteAll();
}
