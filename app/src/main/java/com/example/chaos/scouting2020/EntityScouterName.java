package com.example.chaos.scouting2020;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(primaryKeys = {"ScouterName"})
public class EntityScouterName {
    @ColumnInfo
    @NonNull String ScouterName;
}
