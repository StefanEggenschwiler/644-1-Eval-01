package ch.hesso.remembeer.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
/**
 * Entite Brasserie
 * Differents champs choisis
 */
@Entity(
        tableName = "brewery"
)
public class BreweryEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long idBrewery;
    private String name;
    private String address;
    private String city;
    private String image;

    private String web;
    private boolean isFavoris = false;


    private String description;

    // Note globale
    // Entre 1 et 5
    private int note;

    public Long getIdBrewery() {
        return idBrewery;
    }

    public void setIdBrewery(Long idBrewery) {
        this.idBrewery = idBrewery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getImage() {return image; }

    public void setImage(String image) { this.image = image; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isFavoris() {
        return isFavoris;
    }

    public void setFavoris(boolean favoris) {
        isFavoris = favoris;
    }


    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

}
