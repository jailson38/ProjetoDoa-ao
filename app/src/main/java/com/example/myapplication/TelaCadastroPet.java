package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TelaCadastroPet extends AppCompatActivity {

    DatabaseHelper helper;

    EditText raca;
    EditText quantidade;
    Spinner tipo;
    Spinner portee;
    Button salvar;
    Button excluir;
    String racao_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_pet);
        racao_id = getIntent().getStringExtra("raca_id");

        raca = findViewById(R.id.editTextTelaCadastroRaca);
        quantidade = findViewById(R.id.editTextTelaCadastroQuantidade);
        tipo = findViewById(R.id.spinnerTelaCadastroTipo);
        portee = findViewById(R.id.spinnerTelaCadastroPorte);
        salvar = findViewById(R.id.buttonTelaCadastroSalvar);
        excluir = findViewById(R.id.buttonTelaCadastroExcluir);
        helper = new DatabaseHelper(this);

        if (racao_id!=null){
            prepararEdicao();
        }else {
            raca.setText("");
            quantidade.setText("");
        }

        raca.setText("");
        quantidade.setText("");

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarPet(view);
            }
        });

        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excluiRacao(view);
            }
        });


    }

    public void prepararEdicao(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT raca, quantidade, tipo, porte from racas WHERE _id = ?",new String[]{racao_id});
        cursor.moveToFirst();
        raca.setText(cursor.getString(0));
        quantidade.setText(cursor.getString(1));
        String[] tipos = getResources().getStringArray(R.array.activity_tela_cadastro_marca_tipo_itens);
        for (int item=0;item< tipos.length;item++){
            if (tipos[item].equals(cursor.getString(2))){
                tipo.setSelection(item);
            }
        }
        String[] portes = getResources().getStringArray(R.array.activity_tela_cadastro_raca_porte_lista);
        for (int item=0;item < portes.length;item++){
            if (portes[item].equals(cursor.getString(3)));
        }
    }

    public void salvarPet(View view){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        if (raca.getText().length() >0 && quantidade.getText().length() >0)
        {
        valores.put("raca",raca.getText().toString());
        valores.put("quantidae", quantidade.getText().toString());
        valores.put("tipo", tipo.getSelectedItem().toString());
        valores.put("porte", portee.getSelectedItem().toString());

        if (racao_id == null){
            long resultado = db.insert("racas",null, valores);
        }else {
            long resultado = db.update("racas",valores,"_id = ?", new String[]{racao_id});
        }


        long resultado = db.insert("racoes", null,valores);

        if (resultado !=1){
            Toast.makeText(this, getString(R.string.activity_tela_cadastro_raca_salvo_com_sucesso),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, getString(R.string.activity_tela_cadastro_rac_erro_ao_salvar), Toast.LENGTH_SHORT).show();

        }

        }else {
            Toast.makeText(this, getString(R.string.activity_tela_cadastro_campos_validos),Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(TelaCadastroPet.this, ListaPet.class));
    }


    public  void excluiRacao(View view){
        SQLiteDatabase db = helper.getWritableDatabase();
        long resultado = db.delete("racas", "_id = ?",new String[]{racao_id});
        if (resultado !=1){
            Toast.makeText(this, "Raça excluida com sucesso",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Falha ao excluir raça", Toast.LENGTH_SHORT).show();

        }
        startActivity(new Intent(TelaCadastroPet.this, ListaPet.class));
    }

}