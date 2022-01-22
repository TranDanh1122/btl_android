package com.example.btl_final_final.fragmentleftbar;

import static android.content.Intent.getIntent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.btl_final_final.DangkyActivity;
import com.example.btl_final_final.R;
import com.example.btl_final_final.StartActivity;
import com.example.btl_final_final.adapter.loaichi_ctrl;
import com.example.btl_final_final.model.loaichi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link chi_tab2_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class chi_tab2_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView lvDanhSach;
    ArrayList<loaichi> loaichiArrayList;
    loaichi_ctrl adapter;
    public View rootview;
    private AlertDialog.Builder dialogbuider,dialogbuider_edit;
    private  AlertDialog alertDialog,alertDialog_edit;
    private EditText loaichi;
    private Button save,close,remove;
    DatabaseReference reference;
    int maxid=0;
    int flag=-1;
    ImageView add;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public chi_tab2_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment chi_tab2_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static chi_tab2_Fragment newInstance(String param1, String param2) {
        chi_tab2_Fragment fragment = new chi_tab2_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_chi_tab2_, container, false);
        View listview=inflater.inflate(R.layout.loaichi_list, container, false);
        FirebaseUser currentuserid= FirebaseAuth.getInstance().getCurrentUser();
        String myid=currentuserid.getUid();
        add=rootview.findViewById(R.id.add);
        lvDanhSach=rootview.findViewById(R.id.listloaichi);
        FirebaseDatabase.
                getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference()
                .child("loaichi").orderByKey()
                .limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                    maxid=Integer.parseInt(childSnapshot.getKey());
                }
                Toast.makeText(chi_tab2_Fragment.this.getContext(), String.valueOf(maxid) , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
        //popupadd
        final View popupxml=inflater.inflate(R.layout.popup_add_loaichi, null);
        loaichi=(EditText)popupxml.findViewById(R.id.loaichi);
        save=popupxml.findViewById(R.id.save);
        close=popupxml.findViewById(R.id.close);
        remove=popupxml.findViewById(R.id.remove);
        dialogbuider=new AlertDialog.Builder(chi_tab2_Fragment.this.getContext());
        dialogbuider.setView(popupxml);
        alertDialog=dialogbuider.create();

            lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    loaichi.setText(loaichiArrayList.get(position).getLoaichi());
                    flag=Integer.parseInt(loaichiArrayList.get(position).getId());
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(1550, 1550);
                }
            });
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                flag=-1;
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.show();
                alertDialog.getWindow().setLayout(1550, 1550);
            }
        });

        reference= FirebaseDatabase.getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("loaichi");
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(""+((int)flag)).removeValue();
                alertDialog.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag!=-1){
                    reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {

                            HashMap<String,String> hashMap=new HashMap<>();
                            hashMap.put("id",myid);
                            hashMap.put("pos",String.valueOf(flag));
                            hashMap.put("content",loaichi.getText().toString());
                            reference.child(""+((int)flag)).setValue(hashMap);
                            alertDialog.dismiss();
                            Toast.makeText(chi_tab2_Fragment.this.getContext(), "da them update loai chi", Toast.LENGTH_SHORT).show();
                        }


                    });
                }else{
                reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        HashMap<String,String> hashMap=new HashMap<>();
                        hashMap.put("id",myid);
                        hashMap.put("pos",String.valueOf(maxid+1));

                        hashMap.put("content",loaichi.getText().toString());
                        reference.child(""+((int)maxid+1)).setValue(hashMap);
                        alertDialog.dismiss();
                        Toast.makeText(chi_tab2_Fragment.this.getContext(), "da them loai chi", Toast.LENGTH_SHORT).show();
                    }


                });
                }



            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        DatabaseReference  ref= FirebaseDatabase.getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("loaichi");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                loaichiArrayList=new ArrayList<>();
                if(dataSnapshot==null){}
                else{
                    for (DataSnapshot dt : dataSnapshot.getChildren()) {
                        String content = dt.child("content").getValue(String.class);
                        String id = dt.getKey();
                        if(content==null){}else {
                            if (dt.child("id").getValue().toString().equals(myid)) {
                                loaichiArrayList.add(new loaichi(content, id));
                            }
                        }
                    }
                adapter = new loaichi_ctrl(chi_tab2_Fragment.this.getContext(), R.layout.loaichi_list, loaichiArrayList);
                adapter.notifyDataSetChanged();
                lvDanhSach.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return rootview;
    }
}