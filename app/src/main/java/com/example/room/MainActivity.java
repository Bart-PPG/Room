package com.example.room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private DataBasePracownicy dataBasePracownicy ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RoomDatabase.Callback mojCallBack = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };
        dataBasePracownicy = Room.databaseBuilder(
                getApplicationContext(),
                DataBasePracownicy.class,
                "PracownicyDB")
                .addCallback(mojCallBack)
                .allowMainThreadQueries()
                .build();
    }
}
//1. klasa opisujaca pojedynce rekordy / obiekty
//2.Do klasy adnotacje bazo danowe
//3.Data acces object DAO
//4.klasa z baza