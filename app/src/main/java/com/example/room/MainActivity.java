package com.example.room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        dodajDaneDoBazyWTle();

    }
        private void dodajDaneDoBazyWTle(){
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executorService.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            dataBasePracownicy.getDaoPracownicy().DodajPracownika(new Pracownik("Jas","Nowak","polski","polski",14000.0,"Programista"));
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "dodano do bazy", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }
            );

        }




}
//1. klasa opisujaca pojedynce rekordy / obiekty
//2.Do klasy adnotacje bazo danowe
//3.Data acces object DAO
//4.klasa z baza