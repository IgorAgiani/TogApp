package com.example.togapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TogApp.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USUARIOS = "usuarios";
    private static final String COL_ID = "id";
    private static final String COL_CPF = "cpf";
    private static final String COL_NOME = "nome";
    private static final String COL_EMAIL = "email";
    private static final String COL_DATA_NASC = "data_nascimento";
    private static final String COL_CELULAR = "celular";
    private static final String COL_SENHA = "senha";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USUARIOS_TABLE = "CREATE TABLE " + TABLE_USUARIOS + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_CPF + " TEXT,"
                + COL_NOME + " TEXT,"
                + COL_EMAIL + " TEXT,"
                + COL_DATA_NASC + " TEXT,"
                + COL_CELULAR + " TEXT,"
                + COL_SENHA + " TEXT" + ")";
        db.execSQL(CREATE_USUARIOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }

    public boolean adicionarUsuario(String cpf, String nome, String email, String dataNasc, String celular, String senha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CPF, cpf);
        values.put(COL_NOME, nome);
        values.put(COL_EMAIL, email);
        values.put(COL_DATA_NASC, dataNasc);
        values.put(COL_CELULAR, celular);
        values.put(COL_SENHA, senha);

        long result = db.insert(TABLE_USUARIOS, null, values);
        db.close();
        return result != -1;
    }

    public boolean checkUser(String cpf, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { COL_ID };
        String selection = COL_CPF + " = ?" + " AND " + COL_SENHA + " = ?";
        String[] selectionArgs = { cpf, senha };

        Cursor cursor = db.query(TABLE_USUARIOS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;
    }

    public boolean atualizarSenha(String celular, String novaSenha) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_SENHA, novaSenha);

        int linhasAfetadas = db.update(TABLE_USUARIOS, values, COL_CELULAR + " = ?", new String[]{celular});

        db.close();

        return linhasAfetadas > 0;
    }

    public String buscarNomePorCpf(String cpf) {
        SQLiteDatabase db = this.getReadableDatabase();
        String nome = "";

        Cursor cursor = db.query(TABLE_USUARIOS, new String[]{COL_NOME}, COL_CPF + "=?",
                new String[]{cpf}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            nome = cursor.getString(0);
            cursor.close();
        }

        db.close();
        return nome;
    }

}
