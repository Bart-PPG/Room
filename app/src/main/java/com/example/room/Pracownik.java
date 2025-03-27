package com.example.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "pracownicy")
public class Pracownik {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_pracownika")
    private int id;
    @ColumnInfo(name = "imie_pracownika")
    private String imie;
    private String nazwisko;
    private String jezykOjczysty;
    private String jezykObcyKomunikatywny;
    private Double wynagrodzenie;
    private String stanowisko;

    @Ignore
    public Pracownik(String jas, String nowak, String polski, String s, int i, String programista) {
    }

    public Pracownik(int id, String imie, String nazwisko, String jezykOjczysty, String jezykObcyKomunikatywny, Double wynagrodzenie, String stanowisko) {

        this.id = 0;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.jezykOjczysty = jezykOjczysty;
        this.jezykObcyKomunikatywny = jezykObcyKomunikatywny;
        this.wynagrodzenie = wynagrodzenie;
        this.stanowisko = stanowisko;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getJezykOjczysty() {
        return jezykOjczysty;
    }

    public void setJezykOjczysty(String jezykOjczysty) {
        this.jezykOjczysty = jezykOjczysty;
    }

    public String getJezykObcyKomunikatywny() {
        return jezykObcyKomunikatywny;
    }

    public void setJezykObcyKomunikatywny(String jezykObcyKomunikatywny) {
        this.jezykObcyKomunikatywny = jezykObcyKomunikatywny;
    }

    public Double getWynagrodzenie() {
        return wynagrodzenie;
    }

    public void setWynagrodzenie(Double wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }
}
