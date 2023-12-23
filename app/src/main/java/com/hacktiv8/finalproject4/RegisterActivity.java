package com.hacktiv8.finalproject4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout textInputLayoutNama,textInputLayoutEmailreg, textInputLayoutPasswordreg, textInputLayoutPasswordcnfreg;
    private Button btn_register,btn_loginreg;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        textInputLayoutNama = findViewById(R.id.textInputLayoutNama);
        textInputLayoutEmailreg = findViewById(R.id.textInputLayoutEmailreg);
        textInputLayoutPasswordreg = findViewById(R.id.textInputLayoutPasswordreg);
        textInputLayoutPasswordcnfreg = findViewById(R.id.textInputLayoutPasswordcnfreg);
        btn_register = findViewById(R.id.btn_register);
        btn_loginreg = findViewById(R.id.btn_loginreg);


        mAuth = FirebaseAuth.getInstance();

        btn_loginreg.setOnClickListener(v -> {
            finish();
        });

        btn_register.setOnClickListener(view -> {
            String nama = textInputLayoutNama.getEditText().getText().toString().trim();
            String email = textInputLayoutEmailreg.getEditText().getText().toString().trim();
            String password = textInputLayoutPasswordreg.getEditText().getText().toString().trim();
            String confirmPassword = textInputLayoutPasswordcnfreg.getEditText().getText().toString().trim();

            if (!nama.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {
                if (password.equals(confirmPassword)) {
                    register(nama, email, password);
                } else {
                    Toast.makeText(getApplicationContext(), "Konfirmasi password tidak sesuai", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Silakan isi semua data", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void register(String nama, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(nama)
                                    .build();

                            firebaseUser.updateProfile(request)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> updateTask) {
                                            if (updateTask.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(), "Registrasi berhasil", Toast.LENGTH_SHORT).show();
                                                // Simpan informasi nama pengguna di SharedPreferences atau database lokal
                                                saveUsernameLocally(nama);
                                                reload();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "Gagal memperbarui profil", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            if (task.getException() != null) {
                                Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Gagal membuat akun", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    // Simpan informasi nama pengguna di SharedPreferences atau database lokal
    private void saveUsernameLocally(String username) {
        // Simpan informasi nama pengguna di SharedPreferences atau database lokal
        // Contoh penggunaan SharedPreferences:
        SharedPreferences preferences = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username);
        editor.apply();
    }



    private void reload() {
        startActivity(new Intent(getApplicationContext(), AkunActivity.class));
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }

    }
}
