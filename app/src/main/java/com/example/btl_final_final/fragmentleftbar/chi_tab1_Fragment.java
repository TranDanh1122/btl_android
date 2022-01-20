package com.example.btl_final_final.fragmentleftbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.btl_final_final.R;
import com.example.btl_final_final.adapter.khoanchi_ctrl;
import com.example.btl_final_final.adapter.loaichi_ctrl;
import com.example.btl_final_final.model.khoanchi;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link chi_tab1_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class chi_tab1_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView lvDanhSach;
    ArrayList<com.example.btl_final_final.model.khoanchi> khoanchiArrayList;
    khoanchi_ctrl adapter;
    public View rootview;
    private AlertDialog.Builder dialogbuider,dialogbuider_edit;
    private  AlertDialog alertDialog,alertDialog_edit;
    private EditText khoanchi;
    private Button save,close,remove;
    DatabaseReference reference;
    int maxid=0;
    int flag=-1;
    private Spinner spinnerloaichi;
    ImageView add;
    String[] loaichi;
    public chi_tab1_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment chi_tab1_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static chi_tab1_Fragment newInstance(String param1, String param2) {
        chi_tab1_Fragment fragment = new chi_tab1_Fragment();
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
        // Inflate the layout for this fragment
        rootview=inflater.inflate(R.layout.fragment_chi_tab1_, container, false);

        FirebaseUser currentuserid= FirebaseAuth.getInstance().getCurrentUser();
        String myid=currentuserid.getUid();
        add=rootview.findViewById(R.id.add);
        lvDanhSach=rootview.findViewById(R.id.listkhoanchi);

        //popupadd
        final View popupxml=inflater.inflate(R.layout.popup_add_khoanthuchi, null);
        khoanchi=(EditText)popupxml.findViewById(R.id.khoanthuchi);
        save=popupxml.findViewById(R.id.save);
        close=popupxml.findViewById(R.id.close);
        remove=popupxml.findViewById(R.id.remove);
        dialogbuider=new AlertDialog.Builder(chi_tab1_Fragment.this.getContext());
        dialogbuider.setView(popupxml);
        alertDialog=dialogbuider.create();





        reference= FirebaseDatabase.getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("khoanchi");

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                khoanchi.setText(khoanchiArrayList.get(position).getKhoanchi());
                flag=position;
                spinnerloaichi = (Spinner) popupxml.findViewById(R.id.loaithuchi);
                loaichi=new String[100];
                DatabaseReference  refgetloaichi= FirebaseDatabase.getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("loaichi");
                refgetloaichi.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.getResult().exists()) {


                            int i=0;
                            for(DataSnapshot dt: task.getResult().getChildren()){
                                if(dt.child("content").getValue().toString()!=null) {
                                    loaichi[i] = dt.child("content").getValue().toString();
                                    i++;
                                    Toast.makeText(chi_tab1_Fragment.this.getContext(),  dt.child("content").getValue().toString(), Toast.LENGTH_SHORT).show();
                                }


                            }
                            String[] arrlc=new String[(int)i];
                            for (int j=0;j<i;j++){
                                arrlc[j]=loaichi[j];
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(chi_tab1_Fragment.this.getContext(),
                                    android.R.layout.simple_spinner_item,
                                    arrlc);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            spinnerloaichi.setAdapter(adapter);

                        }


                    }
                });

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
                spinnerloaichi = (Spinner) popupxml.findViewById(R.id.loaithuchi);
                loaichi=new String[100];
                DatabaseReference  refgetloaichi= FirebaseDatabase.getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("loaichi");
                refgetloaichi.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.getResult().exists()) {


                                int i=0;
                                for(DataSnapshot dt: task.getResult().getChildren()){
                                    if(dt.child("content").getValue().toString()!=null) {
                                        if(dt.child("id").getValue().toString().equals(myid)) {
                                            loaichi[i] = dt.child("content").getValue().toString();
                                            i++;
                                            Toast.makeText(chi_tab1_Fragment.this.getContext(), dt.child("content").getValue().toString(), Toast.LENGTH_SHORT).show();
                                        }
                                        }


                                }
                                String[] arrlc=new String[(int)i];
                                for (int j=0;j<i;j++){
                                    arrlc[j]=loaichi[j];
                                }
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(chi_tab1_Fragment.this.getContext(),
                                        android.R.layout.simple_spinner_item,
                                        arrlc);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                spinnerloaichi.setAdapter(adapter);

                            }


                    }
                });



                alertDialog.show();
                alertDialog.getWindow().setLayout(1550, 1550);
            }
        });


        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(""+((int)flag+1)).removeValue();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag!=-1){
                    reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if(task.getResult().exists()){
                                maxid =(int) task.getResult().getChildrenCount();
                                Toast.makeText(chi_tab1_Fragment.this.getContext(), String.valueOf(maxid), Toast.LENGTH_SHORT).show();
                            }
                            HashMap<String,String> hashMap=new HashMap<>();
                            hashMap.put("id",myid);
                            hashMap.put("pos",String.valueOf(flag));
                            hashMap.put("content",khoanchi.getText().toString());
                            hashMap.put("type",spinnerloaichi.getSelectedItem().toString());
                            reference.child(""+((int)flag+1)).setValue(hashMap);
                            alertDialog.dismiss();
                            Toast.makeText(chi_tab1_Fragment.this.getContext(), "da them update loai chi", Toast.LENGTH_SHORT).show();
                        }


                    });
                }else{
                    reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if(task.getResult().exists()){
                                maxid =(int) task.getResult().getChildrenCount();
                                Toast.makeText(chi_tab1_Fragment.this.getContext(), String.valueOf(maxid), Toast.LENGTH_SHORT).show();
                            }
                            HashMap<String,String> hashMap=new HashMap<>();
                            hashMap.put("id",myid);
                            hashMap.put("pos",String.valueOf(maxid));
                            hashMap.put("content",khoanchi.getText().toString());
                            hashMap.put("type",spinnerloaichi.getSelectedItem().toString());
                            DateFormat dateFormat = new SimpleDateFormat("MM");
                            Date date = new Date();
                            hashMap.put("month", dateFormat.format(date).toString());
                            reference.child(""+((int)maxid+1)).setValue(hashMap);
                            alertDialog.dismiss();
                            Toast.makeText(chi_tab1_Fragment.this.getContext(), "da them loai chi", Toast.LENGTH_SHORT).show();
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

        DatabaseReference  ref= FirebaseDatabase.getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("khoanchi");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                khoanchiArrayList=new ArrayList<>();
                if(dataSnapshot==null){}
                else{
                    for (DataSnapshot dt : dataSnapshot.getChildren()) {
                        String content = dt.child("content").getValue(String.class);
                        String id = dt.child("pos").getValue(String.class);
                        String type = dt.child("type").getValue(String.class);
                        if(content==null){}else{
                            khoanchiArrayList.add(new khoanchi(content,id,type));}
                    }
                    adapter = new khoanchi_ctrl(chi_tab1_Fragment.this.getContext(), R.layout.khoanchi_list, khoanchiArrayList);
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