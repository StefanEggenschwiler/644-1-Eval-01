package ch.hesso.remembeer.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import ch.hesso.remembeer.database.association.BreweryWithBeers;
import ch.hesso.remembeer.database.entity.BreweryEntity;

/**
 * Data Access Object de la classe Brewery
 */
@Dao
public abstract class BreweryDao {

    @Query("SELECT * FROM brewery WHERE idBrewery = :id")
    public abstract LiveData<BreweryEntity> getById(Long id);

    @Query("SELECT * FROM brewery")
    public abstract LiveData<List<BreweryEntity>> getAll();

    @Transaction
    @Query("SELECT * FROM brewery WHERE idBrewery = :id")
    public abstract LiveData<List<BreweryWithBeers>> getBreweryWithBeers(Long id);

    @Insert
    public abstract long insert(BreweryEntity brewery);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<BreweryEntity> breweries);

    @Update
    public abstract void update(BreweryEntity brewery);

    @Delete
    public abstract void delete(BreweryEntity brewery);

    @Query("DELETE FROM brewery")
    public abstract void deleteAll();

}
