package com.example.mindbuddy;

public class Jurnal {
    private int id;
    private String judul, isi;
    private int skor;

    public Jurnal(int id, String judul, String isi, int skor) {
        this.id = id;
        this.judul = judul;
        this.isi = isi;
        this.skor = skor;
    }

    // Getter untuk akses data (Encapsulation)
    public int getId() { return id; }
    public String getJudul() { return judul; }
    public String getIsi() { return isi; }
    public int getSkor() { return skor; }
}