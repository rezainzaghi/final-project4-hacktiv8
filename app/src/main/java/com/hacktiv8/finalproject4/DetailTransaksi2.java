package com.hacktiv8.finalproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailTransaksi2 extends AppCompatActivity {

    TextView payment_method_tv, asal_tv, tanggal_tv, tujuan_tv, harga_tv, id_ticket, nama_bus_tv, kursi_tv;
    Button btnBackToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi2);


        id_ticket = findViewById(R.id.tvIDTiket);
        payment_method_tv = findViewById(R.id.tvPaymentMethod);
        asal_tv = findViewById(R.id.tvKeberangkatan);
        tujuan_tv = findViewById(R.id.tvTujuan);
        harga_tv = findViewById(R.id.tvTotal);
        nama_bus_tv = findViewById(R.id.tvBusName);
        kursi_tv = findViewById(R.id.tvKursi);
        tanggal_tv = findViewById(R.id.tvTanggal);


        // Mendapatkan data dari Intent
        Intent intent = getIntent();
        String busName = intent.getStringExtra("bus_name");
        String selectedFrom = intent.getStringExtra("selected_from");
        String selectedDestination = intent.getStringExtra("selected_destination");
        String selectedDate = intent.getStringExtra("selected_date");
        String selectedSeat = intent.getStringExtra("selected_seat");
        String total = intent.getStringExtra("total_pembayaran");
        String paymentMethod = intent.getStringExtra("payment_method");

        // Menetapkan data ke TextViews
        nama_bus_tv.setText(busName);
        asal_tv.setText(selectedFrom);
        tujuan_tv.setText(selectedDestination);
        tanggal_tv.setText(selectedDate);
        kursi_tv.setText(selectedSeat);
        harga_tv.setText(total);
        payment_method_tv.setText(paymentMethod);

        // ... (menetapkan data lainnya)

        // Menyimpan data ke SQLite dengan ID random
        saveDataToSQLite(busName, selectedFrom, selectedDestination, selectedDate, selectedSeat, total, paymentMethod);

        btnBackToMenu = findViewById(R.id.back_to_menu);
        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
    private void saveDataToSQLite(String busName, String selectedFrom, String selectedDestination, String selectedDate, String selectedSeat, String total, String paymentMethod) {
        // Membuka koneksi ke database SQLite
        TransaksiDataSource dataSource = new TransaksiDataSource(this);
        dataSource.open();

        // Menyimpan transaksi ke database dengan ID random
        long insertedId = dataSource.insertTransaksi(busName, selectedFrom, selectedDestination, selectedDate, selectedSeat, total, paymentMethod);

        // Menyimpan ID ke TextView untuk ditampilkan
        id_ticket.setText("ID Tiket: " + insertedId);

        // Menutup koneksi ke database SQLite
        dataSource.close();
    }


}