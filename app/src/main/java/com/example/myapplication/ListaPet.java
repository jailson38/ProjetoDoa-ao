package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
public class ListaPet extends AppCompatActivity {
    DatabaseHelper helper;
    ArrayList <Racas> racas;


    ListView listapet;

    Button novoPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pet);

        helper = new DatabaseHelper(this);

        racas = new ArrayList<Racas>();


        listapet = (ListView) findViewById(R.id.ListViewListaPet);
        novoPet = (Button) findViewById(R.id.buttonNovoPet);

        novoPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaPet.this, TelaCadastroPet.class));
            }
        });




        ArrayAdapter<String> listapetsadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,lerRacas());
        listapet.setAdapter(listapetsadapter);

        listapet.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(ListaPet.this,ListaPet.class);
                intent.putExtra("pet_id",racas.get(1).getId().toString());




            }


        });



    }

    private String [] lerRacas(){
        SQLiteDatabase db = helper.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT _id,raca,quantidade,tipo,porte from racas", null);
        cursor.moveToFirst();
        String [] racasLidas = new String[cursor.getCount()];
        for (int item = 0; item <cursor.getCount();item++){
           racas.add(new Racas(cursor.getLong(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4)));
            racasLidas [item] = cursor.getString(1);
        }
            cursor.close();
            return racasLidas;
    }
}