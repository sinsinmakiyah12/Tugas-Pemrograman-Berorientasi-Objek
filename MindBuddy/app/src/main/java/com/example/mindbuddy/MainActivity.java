package com.example.mindbuddy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvJurnal;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvJurnal = findViewById(R.id.lv_jurnal);
        dbHelper = new DatabaseHelper(this);

        findViewById(R.id.fab_add).setOnClickListener(v ->
                startActivity(new Intent(this, TambahActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        muatData();
    }

    private void muatData() {
        ArrayList<Jurnal> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM tbl_jurnal ORDER BY id DESC", null);

        while (c.moveToNext()) {
            list.add(new Jurnal(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3)));
        }
        c.close();
        lvJurnal.setAdapter(new JurnalAdapter(this, list));
    }
}