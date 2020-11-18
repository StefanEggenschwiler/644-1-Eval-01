package ch.hesso.remembeer.database.entity;

import androidx.room.Embedded;
import androidx.room.Entity;

@Entity(primaryKeys = {"brewery.idBrewery", "beer.idBeer"})
public class BreweryBeer {

    @Embedded private Long brewery;
    @Embedded private BeerEntity beer;

    public BreweryBeer() { }

    public Long getBrewery() {
        return brewery;
    }

    public void setBrewery(Long brewery) {
        this.brewery = brewery;
    }

    public BeerEntity getBeer() {
        return beer;
    }

    public void setBeer(BeerEntity beer) {
        this.beer = beer;
    }
}
