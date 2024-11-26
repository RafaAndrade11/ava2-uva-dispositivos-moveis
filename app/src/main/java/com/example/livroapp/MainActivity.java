package com.example.livroapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.livroapp.db.LivroDAO;
import com.example.livroapp.model.Livro;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LivroDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new LivroDAO(this);
        Button btnAdicionar = findViewById(R.id.btnAdicionar);

        btnAdicionar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdicionarLivroActivity.class);
            startActivity(intent);
        });

        carregarLista();
    }
    private void carregarLista() {
        ListView listView = findViewById(R.id.listViewLivros);
        dao.open();
        List<Livro> livros = dao.listarLivros();

        ArrayAdapter<Livro> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, livros);
        listView.setAdapter(adapter);
        dao.close();
    }
}
