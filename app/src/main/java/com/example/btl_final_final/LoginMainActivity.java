package com.example.btl_final_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class LoginMainActivity extends AppCompatActivity {

    EditText email, password;
    CircularProgressButton loginn;
    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginn = findViewById(R.id.loginn);
        auth = FirebaseAuth.getInstance();
        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    login(email.getText().toString(),password.getText().toString());
                    return true;
                }
                return false;
            }
        });
        loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(email.getText().toString(),password.getText().toString());
            }
        });
    }
    public void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginMainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    startActivity(new Intent(LoginMainActivity.this, MainActivity.class));
                    finish();

                }else{
                    Toast.makeText(LoginMainActivity.this, "Khong the dang nhap", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}