package com.example.livroapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.livroapp.model.Livro;
import com.example.livroapp.service.LivroService;

public class AdicionarLivroActivity extends AppCompatActivity {

    private LivroService livroService;
    private EditText edtTitulo, edtAutor, edtAno;
    private Button btnAdicionar, btnAtualizar, btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_livro);

        livroService = new LivroService(this);

        edtTitulo = findViewById(R.id.edtTitulo);
        edtAutor = findViewById(R.id.edtAutor);
        edtAno = findViewById(R.id.edtAno);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnAtualizar = findViewById(R.id.btnAtualizar);
        btnExcluir = findViewById(R.id.btnExcluir);

        btnAdicionar.setOnClickListener(v -> {
            String titulo = edtTitulo.getText().toString();
            String autor = edtAutor.getText().toString();
            int ano = Integer.parseInt(edtAno.getText().toString());
            Livro livro = new Livro(titulo, autor, ano);

            long id = livroService.adicionarLivro(livro);
            if (id != -1) {
                Toast.makeText(this, "Livro adicionado com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao adicionar livro.", Toast.LENGTH_SHORT).show();
            }
        });

        btnAtualizar.setOnClickListener(v -> {
            long id = 1;
            Livro livro = livroService.obterLivro(id);
            if (livro != null) {
                livro.setTitulo(edtTitulo.getText().toString());
                livro.setAutor(edtAutor.getText().toString());
                livro.setAno(Integer.parseInt(edtAno.getText().toString()));
                if (livroService.atualizarLivro(livro)) {
                    Toast.makeText(this, "Livro atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Erro ao atualizar livro.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnExcluir.setOnClickListener(v -> {
            long id = 1;
            if (livroService.excluirLivro(id)) {
                Toast.makeText(this, "Livro excluído com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao excluir livro.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



