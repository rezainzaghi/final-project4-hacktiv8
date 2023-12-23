package com.hacktiv8.finalproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DetailTransaksi extends AppCompatActivity {

    private EditText etBusName, etKeberangkatan, etTujuan, etTanggal, etKursi, etTotal;
    private Spinner spinnerPayment;
    private Button btnBayar;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);


        // Inisialisasi UI elements
        etBusName = findViewById(R.id.etNamaArmada);
        etKeberangkatan = findViewById(R.id.etBerangkat);
        etTujuan = findViewById(R.id.etTujuan);
        etTanggal = findViewById(R.id.etTanggal);
        etKursi = findViewById(R.id.etKursi);
        etTotal = findViewById(R.id.etTotal);
        spinnerPayment = findViewById(R.id.spinnerPayment);
        btnBayar = findViewById(R.id.btnBayar);

        // Mendapatkan data transaksi dari Intent atau sumber lainnya
        Intent intent = getIntent();
        String busName = intent.getStringExtra("bus_name");
        String selectedSeat = intent.getStringExtra("selected_seat");
        String selectedFrom = intent.getStringExtra("selected_from");
        String selectedDestination = intent.getStringExtra("selected_destination");
        String selectedDate = intent.getStringExtra("selected_date");
        String total = intent.getStringExtra("total_pembayaran");

        // Menampilkan data transaksi pada TextView
        etBusName.setText("Nama Bus: " + busName);
        etKeberangkatan.setText("Asal: " + selectedFrom);
        etTujuan.setText("Tujuan: " + selectedDestination);
        etTanggal.setText("Tanggal: " + selectedDate);
        etKursi.setText("Kursi: " + selectedSeat);
        etTotal.setText("Total Pembayaran: " + total);


        // Membuat daftar metode pembayaran untuk spinner
        String[] paymentMethods = {"DANA", "OVO", "GOPAY"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, paymentMethods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPayment.setAdapter(adapter);


        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedPaymentMethod = spinnerPayment.getSelectedItem().toString();
                // Lakukan logika pembayaran sesuai metode yang dipilih
                // Misalnya: tampilkan pesan konfirmasi atau lakukan pembayaran sesungguhnya
                Toast.makeText(DetailTransaksi.this, "Pembayaran dengan " + selectedPaymentMethod, Toast.LENGTH_SHORT).show();

                // Mengirim data transaksi ke halaman struk
                Intent intent = new Intent(DetailTransaksi.this, DetailTransaksi2.class);
                intent.putExtra("bus_name", busName);
                intent.putExtra("selected_from", selectedFrom);
                intent.putExtra("selected_destination", selectedDestination);
                intent.putExtra("selected_date", selectedDate);
                intent.putExtra("selected_seat", selectedSeat);
                intent.putExtra("total_pembayaran", total);
                intent.putExtra("payment_method", selectedPaymentMethod);

                // Menambahkan ID random sebagai extra
                intent.putExtra("transaction_id", generateRandomId());

                startActivity(intent);
            }
        });

    }
    private String generateRandomId() {
        // Generate ID acak sesuai kebutuhan aplikasi Anda
        // Di sini, kita menggunakan timestamp untuk membuat ID yang unik
        return String.valueOf(System.currentTimeMillis());
    }
}