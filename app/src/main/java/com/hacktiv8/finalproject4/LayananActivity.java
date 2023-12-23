package com.hacktiv8.finalproject4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LayananActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button pilih_kursi1, pilih_kursi2, pilih_kursi3;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layanan_main);

        String total = "null";


        pilih_kursi1 = findViewById(R.id.btn_kursi1);
        pilih_kursi2 = findViewById(R.id.btn_kursi2);
        pilih_kursi3 = findViewById(R.id.btn_kursi3);


        ImageView iconBack = findViewById(R.id.icon_back);
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button btnDetail1 = findViewById(R.id.btn_detail1);
        btnDetail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetailBus();
            }
        });

        Button btnDetail2 = findViewById(R.id.btn_detail2);
        btnDetail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetailBus2();
            }
        });

        Button btnDetail3 = findViewById(R.id.btn_detail3);
        btnDetail3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetailBus3();
            }
        });


        Intent intent = getIntent();
        String selectedFrom = intent.getStringExtra("selected_from");
        String selectedDestination = intent.getStringExtra("selected_destination");
        String selectedDate = intent.getStringExtra("selected_date");



        pilih_kursi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchPilihKursi("PO.Haryanto SHD","200.000", selectedFrom, selectedDestination, selectedDate);
            }
        });

        pilih_kursi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchPilihKursi("PO.Sinar Jaya - SHD", "Rp 180.000", selectedFrom, selectedDestination, selectedDate);
            }
        });

        pilih_kursi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchPilihKursi("PO.Rosalia Indah", "Rp 300.000", selectedFrom, selectedDestination, selectedDate);
            }
        });
    }
    private void launchPilihKursi(String busName, String total, String selectedFrom, String selectedDestination, String selectedDate) {
        Intent intent = new Intent(LayananActivity.this, PilihKursi.class);
        intent.putExtra("bus_name", busName);
        intent.putExtra("total_pembayaran", total);
        intent.putExtra("selected_from", selectedFrom);
        intent.putExtra("selected_destination", selectedDestination);
        intent.putExtra("selected_date", selectedDate);
        startActivity(intent);
    }

    private void showDetailBus() {
        Intent intent = new Intent(LayananActivity.this, DetailBusActivity1.class);
        startActivity(intent);
    }
    private void showDetailBus2() {
        Intent intent = new Intent(LayananActivity.this, DetailBusActivity2.class);
        startActivity(intent);
    }
    private void showDetailBus3() {
        Intent intent = new Intent(LayananActivity.this, DetailBusActivity3.class);
        startActivity(intent);
    }


}

