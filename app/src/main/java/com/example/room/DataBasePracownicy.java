package com.example.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Pracownik.class}, version = 1, exportSchema = false)
public abstract class DataBasePracownicy extends RoomDatabase {
    public abstract DaoPracownicy getDaoPracownicy();
    

}
