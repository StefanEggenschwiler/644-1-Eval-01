package ch.hesso.remembeer.database.association;


import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import ch.hesso.remembeer.database.entity.BeerEntity;
import ch.hesso.remembeer.database.entity.BreweryBeer;
import ch.hesso.remembeer.database.entity.BreweryEntity;

/**
 * Table assiociative entre nos deux tables
 */
public class BreweryWithBeers {

    @Embedded private BreweryEntity brewery;

    @Relation(
            parentColumn = "idBrewery",
            entityColumn = "idBeer",
            entity = BeerEntity.class
    )
    private List<BeerEntity> beers;

    public BreweryWithBeers() { }

    public BreweryEntity getBrewery() {
        return brewery;
    }

    public void setBrewery(BreweryEntity brewery) {
        this.brewery = brewery;
    }

    public List<BeerEntity> getBeers() {
        return beers;
    }

    public void setBeers(List<BeerEntity> beers) {
        this.beers = beers;
    }
}
