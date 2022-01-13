package com.example.btl_final_final;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class DangkyActivity extends AppCompatActivity {
    MaterialEditText username,password,email;
    Button dangki;
    FirebaseAuth auth;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        username= findViewById(R.id.username);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        dangki=findViewById(R.id.dangki);
        auth= FirebaseAuth.getInstance();
        dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_username= username.getText().toString();
                String txt_email= email.getText().toString();
                String txt_password= password.getText().toString();
                if(TextUtils.isEmpty(txt_username)||TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)){
                    Toast.makeText(DangkyActivity.this, "Hay nhap gi do", Toast.LENGTH_SHORT).show();
                }else if(txt_password.length()<6){
                    Toast.makeText(DangkyActivity.this, "Pass co do dai lon hon 6", Toast.LENGTH_SHORT).show();
                }
                else{
                    register(txt_username,txt_email,txt_password);
                }


            }
        });
    }
    public void register (String username , String email, String password){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(DangkyActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser firebaseUser=auth.getCurrentUser();
                    String userid= firebaseUser.getUid();
                    reference= FirebaseDatabase.getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("User").child(userid);
                    HashMap<String,String> hashMap=new HashMap<>();
                    hashMap.put("id",userid);
                    hashMap.put("username",username);
                    hashMap.put("avatar","default");
                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent intent= new Intent(DangkyActivity.this, StartActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(DangkyActivity.this, "Failed Create Database",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{

                    Toast.makeText(DangkyActivity.this, "Failed Registration",Toast.LENGTH_SHORT).show();                }
            }
        });
    }
}