package com.example.livroapp.service;

import android.content.Context;

import com.example.livroapp.db.LivroDAO;
import com.example.livroapp.model.Livro;

import java.util.List;

public class LivroService {

    private LivroDAO livroDAO;

    public LivroService(Context context) {
        livroDAO = new LivroDAO(context);
    }

    public long adicionarLivro(Livro livro) {
        livroDAO.open();
        long id = livroDAO.inserir(livro);
        livroDAO.close();
        return id;
    }

    public boolean atualizarLivro(Livro livro) {
        livroDAO.open();
        int rowsUpdated = livroDAO.atualizar(livro);
        livroDAO.close();
        return rowsUpdated > 0;
    }

    public boolean excluirLivro(long id) {
        livroDAO.open();
        int rowsDeleted = livroDAO.excluir(id);
        livroDAO.close();
        return rowsDeleted > 0;
    }

    public Livro obterLivro(long id) {
        livroDAO.open();
        Livro livro = livroDAO.obterLivro(id);
        livroDAO.close();
        return livro;
    }

    public List<Livro> listarLivros() {
        livroDAO.open();
        List<Livro> livros = livroDAO.listarLivros();
        livroDAO.close();
        return livros;
    }
}
