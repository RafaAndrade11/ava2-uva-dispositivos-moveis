package com.example.livroapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LivroDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "livros.db";
    private static final int DATABASE_VERSION = 1;

    public LivroDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSQL = "CREATE TABLE livros (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT, " +
                "autor TEXT, " +
                "ano INTEGER" +
                ")";
        db.execSQL(createTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS livros");
        onCreate(db);
    }
}

