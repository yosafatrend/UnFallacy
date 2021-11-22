package com.yorren.unfallacies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class StarterActivity extends AppCompatActivity {
    Button btnMasuk;
    EditText edtNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);

        edtNama = findViewById(R.id.edtNama);
        btnMasuk = findViewById(R.id.btnMasuk);

        btnMasuk.setOnClickListener(v -> {
            String name = edtNama.getText().toString();
            if(!name.isEmpty()){
                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                        .putString("username", name).apply();
                startActivity(new Intent(StarterActivity.this, DashboardActivity.class));
                finish();
            }else{
                edtNama.setError("Nama panggilan harus diisi");
            }

        });
    }
}