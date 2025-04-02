package com.example.room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private DataBasePracownicy dataBasePracownicy ;

    private EditText editTextImie;
    private EditText editTextNazwisko;
    private Spinner spinnerStanowisko;
    private Button buttondodaj;

    private List<Pracownik> pracownicy;
    private ListView listView;
    private ArrayAdapter<Pracownik> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextImie = findViewById(R.id.editTextTextPersonName);
        editTextNazwisko = findViewById(R.id.editTextTextPersonName2);
        spinnerStanowisko = findViewById(R.id.spinner);
        buttondodaj = findViewById(R.id.button);
        listView = findViewById(R.id.listViewPracownicy);
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



        buttondodaj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String imie = editTextImie.getText().toString();
                        String nazwisko = editTextNazwisko.getText().toString();
                        String stanowisko = spinnerStanowisko.getSelectedItem().toString();
                        Pracownik pracownik = new Pracownik(imie,nazwisko,"Polski","Angielski",6000.0,stanowisko);
                        dodajDaneDoBazyWTle(pracownik);
                    }
                }
        );
        wypiszPracownikow();
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        usunPracownika(pracownicy.get(i));
                    }
                }
        );
    }
    private void usunPracownika(Pracownik pracownik){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        dataBasePracownicy.getDaoPracownicy().usunPracownika(pracownik);
                        pracownicy.remove(pracownik);
                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        arrayAdapter.notifyDataSetChanged();
                                    }
                                }
                        );
                    }
                }
        );
    }


    public void wypiszPracownikow(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        // handler sprawdza czy sie wykonalo
        executorService.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        //TODO odczyt bazy
                        pracownicy = dataBasePracownicy.getDaoPracownicy().wypiszWszystkichPracownikow();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,pracownicy);
                                listView.setAdapter(arrayAdapter);
                            }
                        });
                    }
                }
        );
    }


        private void dodajDaneDoBazyWTle(Pracownik pracownik){
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executorService.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            dataBasePracownicy.getDaoPracownicy().DodajPracownika(pracownik);
                            //dataBasePracownicy.getDaoPracownicy().DodajPracownika(new Pracownik("Jas","Nowak","polski","polski",14000.0,"Programista"));
                           //TODO dodac nowego pracownika z formularza
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    pracownicy.add(pracownik);
                                    arrayAdapter.notifyDataSetChanged();
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