package com.hacktiv8.finalproject4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PilihKursi extends AppCompatActivity {

    private Spinner spinnerSeats;
    private Button btnChooseSeat;
    private String[] seats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_kursi);

        Intent intent = getIntent();
        String busName = intent.getStringExtra("bus_name");
        String selectedFrom = intent.getStringExtra("selected_from");
        String selectedDestination = intent.getStringExtra("selected_destination");
        String selectedDate = intent.getStringExtra("selected_date");
        String total = intent.getStringExtra("total_pembayaran");



        int numRows = 8;
        int numColumns = 4;

        // Generate array untuk kursi
        seats = generateSeatsArray(numRows, numColumns);
        btnChooseSeat = findViewById(R.id.btn_ChooseSeat);
        spinnerSeats = findViewById(R.id.spinner_seats);

        btnChooseSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedSeat = spinnerSeats.getSelectedItem().toString();
                if (!selectedSeat.equals("Pilih Kursi")) {
                    Intent intent = new Intent(PilihKursi.this, DetailTransaksi.class);
                    intent.putExtra("bus_name", busName);
                    intent.putExtra("selected_seat", selectedSeat);
                    intent.putExtra("selected_from", selectedFrom);
                    intent.putExtra("selected_destination", selectedDestination);
                    intent.putExtra("selected_date", selectedDate);
                    intent.putExtra("total_pembayaran", total);
                    startActivity(intent);
                } else {
                    Toast.makeText(PilihKursi.this, "Silakan pilih kursi terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, seats);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeats.setAdapter(adapter);

        spinnerSeats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedSeat = seats[position];
                if (!selectedSeat.equals("Pilih Kursi")) {
                    Toast.makeText(PilihKursi.this, "Kursi yang dipilih: " + selectedSeat, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });
    }

    private String[] generateSeatsArray(int numRows, int numColumns) {
        String[] generatedSeats = new String[numRows * numColumns + 1];
        generatedSeats[0] = "Pilih Kursi";

        char row = 'A';

        int index = 1;
        for (int i = 0; i < numRows; i++) {
            for (int j = 1; j <= numColumns; j++) {
                generatedSeats[index] = String.valueOf(row) + j;
                index++;
            }
            row++;
        }
        return generatedSeats;
    }



}