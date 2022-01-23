package com.example.btl_final_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_final_final.fragmentleftbar.Thu_Fragment;
import com.example.btl_final_final.fragmentleftbar.chi_Fragment;
import com.example.btl_final_final.fragmentleftbar.home_Fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView,back;
    DrawerLayout draw;
    public static final int home=1;
    public static final int thu=2;
    public static final int chi=3;
    public static  int current=home;
    Button homebtn,thubtn,chibtn,logout;
    TextView mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Trang chủ");

        super.onCreate(savedInstanceState);
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
                .build();

        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.imageView);
        back=(ImageView)findViewById(R.id.back);
        draw=findViewById(R.id.draw);
        imageView.setOnClickListener(this::onClick);
        back.setOnClickListener(this::onClick);
        NavigationView nv=findViewById(R.id.sidemenu);
        homebtn=(Button)findViewById(R.id.home);
        thubtn=(Button)findViewById(R.id.thu);
        chibtn=(Button)findViewById(R.id.chi);
        logout=(Button)findViewById(R.id.logout);
        homebtn.setOnClickListener(this);
        thubtn.setOnClickListener(this);
        chibtn.setOnClickListener(this);
        logout.setOnClickListener(this);
        TextView name, email;
        name=findViewById(R.id.username);
        email=findViewById(R.id.email);
        //first load fragment (home)
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.maincontent,new home_Fragment());
        fragmentTransaction.commit();

        //load current user
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference= FirebaseDatabase.getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();

        String currentuseremail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        mail=findViewById(R.id.mail);
        mail.setText(currentuseremail);
        reference.child("User").child(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                String info=task.getResult().child("username").getValue(String.class);
                name.setText(info);
                }
            });
        }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.imageView:
                draw.openDrawer(Gravity.LEFT);
                break;
            case R.id.back:
                draw.closeDrawers();
                break;
            case R.id.home:
                if (current != home) {
                    getSupportActionBar().setTitle("Trang Chủ");
                    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.maincontent, new home_Fragment());
                    fragmentTransaction.commit();
                    current = home;
                }
                draw.closeDrawers();
                break;

            case R.id.chi:
                if (current != chi) {
                    getSupportActionBar().setTitle("Khoản Chi");
                    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction().addToBackStack(null);
                    fragmentTransaction.replace(R.id.maincontent, new chi_Fragment());
                    fragmentTransaction.commit();
                    current = chi;
                }
                draw.closeDrawers();
                break;

            case R.id.thu:
                if (current != thu) {
                    getSupportActionBar().setTitle("Khoản Thu");
                    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction().addToBackStack(null);
                    fragmentTransaction.replace(R.id.maincontent, new Thu_Fragment());
                    fragmentTransaction.commit();
                    current = thu;
                }
                draw.closeDrawers();
                break;
            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Đăng xuất").setMessage("Bạn có chắc muốn đăng xuất không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this,StartActivity.class));
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();



                break;
        }

    }
}