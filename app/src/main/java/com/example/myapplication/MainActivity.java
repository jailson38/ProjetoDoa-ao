package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText usuario;
    EditText senha;
    Button entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (EditText) findViewById(R.id.editTextUsuario);
        senha = (EditText) findViewById(R.id.editTextSenha);
        entrar = (Button) findViewById(R.id.buttonEntrar);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("adimin".equals(usuario.getText().toString()) && "123".equals(senha.getText().toString())) {
                    startActivity(new Intent(MainActivity.this, TelaSelecao.class));
                } else {
                    Toast falhaAutenticacao = Toast.makeText(MainActivity.this, getResources().getString(R.string.activity_main_falha_autenticaco), Toast.LENGTH_SHORT);
                    falhaAutenticacao.show();
                }
            }
        });

    }

    @Override
    protected  void onStart(){
        super.onStart();
        usuario.setText("");
        senha.setText("");
    }



}