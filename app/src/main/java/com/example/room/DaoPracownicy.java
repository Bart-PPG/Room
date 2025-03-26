package com.example.room;
//data acces object DAO
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DaoPracownicy {
    @Insert
    public void DodajPracownika(Pracownik pracownik);

    @Insert
    public void DodajWieluPracownikow(Pracownik ...pracownicy);

    @Delete
    public void usunPracownika(Pracownik pracownik);

    @Update
    public void zaktualizujDanePracownika(Pracownik pracownik);

    @Query("Select * from pracownicy where jezykOjczysty = 'polski'")
    public List<Pracownik> wpiszPracownikowPolskoJezycznych();

    @Query("Select * from pracownicy where jezykObcyKomunikatywny = :jezyk")
    public List<Pracownik> wpiszPracownikowMowiacychJezykiem(String jezyk);
}
