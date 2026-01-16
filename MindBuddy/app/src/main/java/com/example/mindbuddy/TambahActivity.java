package com.example.mindbuddy;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TambahActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        // Inisialisasi Database Helper (Penghubung ke Memori)
        DatabaseHelper db = new DatabaseHelper(this);

        EditText etJudul = findViewById(R.id.et_judul);
        EditText etIsi = findViewById(R.id.et_isi);
        EditText etSkor = findViewById(R.id.et_skor);
        Button btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(v -> {
            String judul = etJudul.getText().toString();
            String isi = etIsi.getText().toString();
            String skorStr = etSkor.getText().toString();

            if (!judul.isEmpty() && !skorStr.isEmpty()) {
                // PROSES MENYIMPAN (CRUD - Create)
                db.tambahData(judul, isi, Integer.parseInt(skorStr));

                Toast.makeText(this, "Cerita tersimpan di memori! ✨", Toast.LENGTH_SHORT).show();
                finish(); // Otomatis kembali ke halaman utama setelah simpan
            } else {
                Toast.makeText(this, "Judul & Skor jangan kosong ya!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}