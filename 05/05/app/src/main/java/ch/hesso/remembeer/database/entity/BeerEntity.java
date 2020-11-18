package ch.hesso.remembeer.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Entite Biere
 * Differents champs choisis
 */

@Entity(
        tableName = "beer"
)
public class BeerEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long idBeer;
    private String name;
    private String description;
    private String from;
    private String image;

    private boolean isFavoris = false;

    private double capacity;
    private double alcool;
    private double acidity;

    // Notes de la biere
    // Note de 1 à 5
    // fonctionnalite a developper dans un second temps
    private int noteGlobal;
    private int noteTaste;
    private int noteSticker;
    private int noteColor;

    public BeerEntity() { }

    public Long getIdBeer() {
        return idBeer;
    }

    public void setIdBeer(Long idBeer) {
        this.idBeer = idBeer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getAlcool() {
        return alcool;
    }

    public void setAlcool(double alcool) {
        this.alcool = alcool;
    }

    public double getAcidity() {
        return acidity;
    }

    public void setAcidity(double acidity) {
        this.acidity = acidity;
    }

    public int getNoteGlobal() {
        return noteGlobal;
    }

    public void setNoteGlobal(int noteGlobal) {
        this.noteGlobal = noteGlobal;
    }

    public int getNoteTaste() {
        return noteTaste;
    }

    public void setNoteTaste(int noteTaste) {
        this.noteTaste = noteTaste;
    }

    public int getNoteSticker() {
        return noteSticker;
    }

    public void setNoteSticker(int noteSticker) {
        this.noteSticker = noteSticker;
    }

    public int getNoteColor() {
        return noteColor;
    }

    public void setNoteColor(int noteColor) {
        this.noteColor = noteColor;
    }

    public boolean isFavoris() {
        return isFavoris;
    }

    public void setFavoris(boolean favoris) {
        isFavoris = favoris;
    }
}
