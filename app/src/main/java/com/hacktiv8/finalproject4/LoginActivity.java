package com.hacktiv8.finalproject4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout textInputLayoutEmail, textInputLayoutPassword;
    private Button btnLogin, btnRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);

        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        });
        btnLogin.setOnClickListener(view -> {
            EditText editTextEmail = textInputLayoutEmail.getEditText();
            EditText editTextPassword = textInputLayoutPassword.getEditText();

            if (editTextEmail != null && editTextPassword != null &&
                    !editTextEmail.getText().toString().isEmpty() &&
                    !editTextPassword.getText().toString().isEmpty()) {

                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                login(email, password);
            } else {
                Toast.makeText(getApplicationContext(), "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Metode untuk melakukan login menggunakan Firebase Authentication
    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Login berhasil
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            // Jika login berhasil, lakukan aksi yang diinginkan
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    } else {
                        // Login gagal
                        Toast.makeText(getApplicationContext(), "Login gagal, silakan cek kembali email dan password", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void reload() {
        startActivity(new Intent(getApplicationContext(), AkunActivity.class));
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Cek apakah pengguna sudah login sebelumnya
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // Jika sudah login sebelumnya, langsung arahkan ke halaman yang diinginkan
            reload();
        }
    }
}
