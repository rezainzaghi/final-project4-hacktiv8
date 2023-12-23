package com.hacktiv8.finalproject4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AkunActivity extends AppCompatActivity {

    private TextView text_name, text_email;
    private Button btnLogout, btnPesan;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.akun_main);

        mAuth = FirebaseAuth.getInstance();
        btnLogout = findViewById(R.id.btn_logout);
        btnPesan = findViewById(R.id.btn_pesan);

        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        btnLogout.setOnClickListener(view ->{
            logout();
        });

        text_name = findViewById(R.id.text_name);
        text_email = findViewById(R.id.text_email);

        // Ambil informasi nama pengguna dan email dari SharedPreferences atau database lokal
        String username = getUsernameFromLocal();
        String email = getEmailFromLocal();

        // Tampilkan informasi nama pengguna dan email di TextView
        text_name.setText("Welcome, " + username);
        text_email.setText(email);
    }

    // Ambil informasi nama pengguna dari SharedPreferences atau database lokal
    private String getUsernameFromLocal() {
        SharedPreferences preferences = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        return preferences.getString("username", "");
    }

    // Ambil informasi email dari SharedPreferences atau database lokal
    private String getEmailFromLocal() {
        SharedPreferences preferences = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        return preferences.getString("email", "");
    }

    private void logout() {
        mAuth.signOut();
        Intent intent = new Intent(AkunActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
