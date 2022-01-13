package com.example.btl_final_final;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class DangnhapActivity extends AppCompatActivity {
    MaterialEditText password, email;
    Button dangnhap;
    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        dangnhap = findViewById(R.id.dangnhap);
        auth = FirebaseAuth.getInstance();
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(email.getText().toString(),password.getText().toString());
            }
        });
    }

    public void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(DangnhapActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    startActivity(new Intent(DangnhapActivity.this, MainActivity.class));
                    finish();

                }else{
                    Toast.makeText(DangnhapActivity.this, "Khong the dang nhap", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}