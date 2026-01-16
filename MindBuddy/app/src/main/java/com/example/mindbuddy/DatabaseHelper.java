package com.example.mindbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        // Nama database: mindbuddy.db
        super(context, "mindbuddy.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Nama tabel: tbl_jurnal
        db.execSQL("CREATE TABLE tbl_jurnal (id INTEGER PRIMARY KEY AUTOINCREMENT, judul TEXT, isi TEXT, skor INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old, int n) {
        db.execSQL("DROP TABLE IF EXISTS tbl_jurnal");
        onCreate(db);
    }

    // CREATE: Tambah Data
    public void tambahData(String j, String i, int s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("judul", j);
        v.put("isi", i);
        v.put("skor", s);
        db.insert("tbl_jurnal", null, v);
    }

    // READ: Ambil Semua Data untuk di List
    public Cursor bacaData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM tbl_jurnal", null);
    }

    // UPDATE: Perbarui Data (Penting untuk Fitur Edit!)
    public void updateData(int id, String j, String i, int s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("judul", j);
        v.put("isi", i);
        v.put("skor", s);
        // Pastikan nama tabel sama: tbl_jurnal
        db.update("tbl_jurnal", v, "id=?", new String[]{String.valueOf(id)});
    }

    // DELETE: Hapus Data
    public void hapusData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("tbl_jurnal", "id=?", new String[]{String.valueOf(id)});
    }
}