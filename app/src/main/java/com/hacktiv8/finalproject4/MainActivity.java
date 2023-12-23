package com.hacktiv8.finalproject4;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerFrom, spinnerDestination;
    private EditText dateEditText;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerFrom = findViewById(R.id.spinnerfrom);
        spinnerDestination = findViewById(R.id.spinnerdestination);
        dateEditText = findViewById(R.id.dateEditText);
        calendar = Calendar.getInstance();

        List<String> locations = new ArrayList<>();
        locations.add("Pilih Keberangkatan");
        locations.add("Jogja");
        locations.add("Solo");
        locations.add("Madiun");
        locations.add("Ngawi");
        locations.add("Surabaya");

        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locations);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFrom.setAdapter(locationAdapter);
        spinnerFrom.setPrompt(" ");

        List<String> destinations = new ArrayList<>();
        destinations.add("Pilih Tujuan");
        destinations.add("Jogja");
        destinations.add("Solo");
        destinations.add("Madiun");
        destinations.add("Ngawi");
        destinations.add("Surabaya");

        ArrayAdapter<String> destinationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, destinations);
        destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerDestination.setAdapter(destinationAdapter);
        spinnerDestination.setPrompt(" ");

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String selectedDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                dateEditText.setText(selectedDate);
                            }
                        }, year, month, day);

                datePickerDialog.show();
            }
        });

        NumberPicker passengerNumberPicker = findViewById(R.id.passengerNumberPicker);

        String[] numbers = {"1"};
        passengerNumberPicker.setMinValue(0);
        passengerNumberPicker.setMaxValue(numbers.length - 1);
        passengerNumberPicker.setDisplayedValues(numbers);

        passengerNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                String selectedValue = numbers[newVal];
            }
        });

        Button search_button = findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormValid()) {

                    String selectedFrom = spinnerFrom.getSelectedItem().toString();
                    String selectedDestination = spinnerDestination.getSelectedItem().toString();
                    String selectedDate = dateEditText.getText().toString();

                    Intent intent = new Intent(MainActivity.this, LayananActivity.class);
                    intent.putExtra("selected_from", selectedFrom);
                    intent.putExtra("selected_destination", selectedDestination);
                    intent.putExtra("selected_date", selectedDate);
                    startActivity(intent);
                } else {

                }
            }
        });

        ImageButton profileIcon = findViewById(R.id.profileIcon);
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileActivity();
            }
        });
    }

    private void openProfileActivity() {
        Intent intent = new Intent(MainActivity.this, AkunActivity.class);
        startActivity(intent);
    }

    private boolean isFormValid() {
        return !spinnerFrom.getSelectedItem().toString().isEmpty()
                && !spinnerDestination.getSelectedItem().toString().isEmpty()
                && !dateEditText.getText().toString().isEmpty();
    }
}
