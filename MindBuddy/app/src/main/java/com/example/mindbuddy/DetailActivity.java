package com.example.mindbuddy;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Inisialisasi komponen
        EditText etJudul = findViewById(R.id.detail_judul);
        EditText etSkor = findViewById(R.id.detail_skor);
        EditText etIsi = findViewById(R.id.detail_isi);
        TextView tvSaran = findViewById(R.id.detail_saran);
        Button btnEdit = findViewById(R.id.btn_edit);
        Button btnHapus = findViewById(R.id.btn_hapus);

        // Ambil data dari Intent
        int id = getIntent().getIntExtra("ID", 0);
        String judul = getIntent().getStringExtra("JUDUL");
        String isi = getIntent().getStringExtra("ISI");
        int skor = getIntent().getIntExtra("SKOR", 0);

        // Tampilkan data ke EditText
        etJudul.setText(judul);
        etIsi.setText(isi);
        etSkor.setText(String.valueOf(skor));

        // LOGIKA SARAN: Tinggi = Bahagia
        String pesanSaran;
        if (skor <= 3) {
            pesanSaran = "MindBuddy: Hari yang berat? Kamu hebat sudah bertahan sejauh ini! 🫂";
        } else if (skor <= 7) {
            pesanSaran = "MindBuddy: Kamu melakukan yang terbaik! Teruslah melangkah. 🌿";
        } else {
            pesanSaran = "MindBuddy: Wah, harimu luar biasa! Pertahankan energi ini! 🌈✨";
        }

        // Tampilkan saran ke TextView
        tvSaran.setText(pesanSaran);

        // Klik Simpan (Update)
        btnEdit.setOnClickListener(v -> {
            DatabaseHelper db = new DatabaseHelper(this);
            String j = etJudul.getText().toString();
            String i = etIsi.getText().toString();
            int s = Integer.parseInt(etSkor.getText().toString());

            db.updateData(id, j, i, s);
            Toast.makeText(this, "Berhasil diperbarui!", Toast.LENGTH_SHORT).show();
            finish();
        });

        // Klik Hapus (Delete)
        btnHapus.setOnClickListener(v -> {
            DatabaseHelper db = new DatabaseHelper(this);
            db.hapusData(id);
            Toast.makeText(this, "Catatan dihapus", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}