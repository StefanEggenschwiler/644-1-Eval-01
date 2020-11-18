package com.example.ourapplication_kohl_roux_m.dbClass.pojo;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.ourapplication_kohl_roux_m.dbClass.entities.CarEntity;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;

public class TrajetByThisCar {
    @Embedded
    public TrajetEntity trajetEntity;

    @Relation(parentColumn = "Voiture_id", entityColumn = "uid", entity = CarEntity.class)
    public CarEntity carEntity;
}
