package com.example.livroapp.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.livroapp.model.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;

    public LivroDAO(Context context) {
        dbHelper = new LivroDatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

    public long inserir(Livro livro) {
        ContentValues values = new ContentValues();
        values.put("titulo", livro.getTitulo());
        values.put("autor", livro.getAutor());
        values.put("ano", livro.getAno());

        return db.insert("livros", null, values);
    }

    public int atualizar(Livro livro) {
        ContentValues values = new ContentValues();
        values.put("titulo", livro.getTitulo());
        values.put("autor", livro.getAutor());
        values.put("ano", livro.getAno());

        return db.update("livros", values, "id = ?", new String[]{String.valueOf(livro.getId())});
    }

    public int excluir(long id) {
        return db.delete("livros", "id = ?", new String[]{String.valueOf(id)});
    }

    public Livro obterLivro(long id) {
        Cursor cursor = db.query("livros", new String[]{"id", "titulo", "autor", "ano"}, "id = ?",
                new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            @SuppressLint("Range") Livro livro = new Livro(
                    (int) cursor.getLong(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("titulo")),
                    cursor.getString(cursor.getColumnIndex("autor")),
                    cursor.getInt(cursor.getColumnIndex("ano"))
            );
            cursor.close();
            return livro;
        }
        return null;
    }

    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
        Cursor cursor = db.query("livros", new String[]{"id", "titulo", "autor", "ano"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") Livro livro = new Livro(
                    (int) cursor.getLong(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("titulo")),
                    cursor.getString(cursor.getColumnIndex("autor")),
                    cursor.getInt(cursor.getColumnIndex("ano"))
            );
            livros.add(livro);
        }
        cursor.close();
        return livros;
    }
}

