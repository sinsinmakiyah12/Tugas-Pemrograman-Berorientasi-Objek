package com.example.mindbuddy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;

public class JurnalAdapter extends ArrayAdapter<Jurnal> {
    public JurnalAdapter(Context context, ArrayList<Jurnal> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Jurnal j = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_jurnal, parent, false);
        }

        LinearLayout container = convertView.findViewById(R.id.container_item);
        TextView tvJudul = convertView.findViewById(R.id.txt_judul_list);
        TextView tvSkor = convertView.findViewById(R.id.txt_skor_list);

        tvJudul.setText(j.getJudul());
        tvSkor.setText("Mood Score: ✨ " + j.getSkor() + "/10");

        // Warna Aesthetic
        String[] colors = {"#FFD1DC", "#D1FFD7", "#E0D1FF"};
        container.setBackgroundColor(Color.parseColor(colors[position % 3]));

        // Klik untuk Detail (Interaktif)
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra("ID", j.getId());
            intent.putExtra("JUDUL", j.getJudul());
            intent.putExtra("ISI", j.getIsi());
            intent.putExtra("SKOR", j.getSkor());
            getContext().startActivity(intent);
        });

        return convertView;
    }
}