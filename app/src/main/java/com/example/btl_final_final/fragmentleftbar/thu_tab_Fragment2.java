package com.example.btl_final_final.fragmentleftbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.btl_final_final.R;
import com.example.btl_final_final.adapter.loaithu_ctrl;
import com.example.btl_final_final.model.loaithu;
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
 * Use the {@link thu_tab_Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class thu_tab_Fragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public View rootview;
    ListView lvDanhSach;
    ArrayList<loaithu> loaithuArrayList;
    loaithu_ctrl adapter;

    private AlertDialog.Builder dialogbuider,dialogbuider_edit;
    private  AlertDialog alertDialog,alertDialog_edit;
    private EditText loaithu;
    private Button save,close,remove;
    DatabaseReference reference;
    int maxid=0;
    int flag=-1;
    ImageView add;
    public thu_tab_Fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment thu_tab_Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static thu_tab_Fragment2 newInstance(String param1, String param2) {
        thu_tab_Fragment2 fragment = new thu_tab_Fragment2();
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
        View listview=inflater.inflate(R.layout.loaithu_list, container, false);
        FirebaseUser currentuserid= FirebaseAuth.getInstance().getCurrentUser();
        String myid=currentuserid.getUid();
        rootview=inflater.inflate(R.layout.fragment_thu_tab_2, container, false);

        add=rootview.findViewById(R.id.add);
        lvDanhSach=rootview.findViewById(R.id.listloaithu);

        //popupadd
        final View popupxml=inflater.inflate(R.layout.popup_add_loaichi, null);
        loaithu=(EditText)popupxml.findViewById(R.id.loaichi);
        save=popupxml.findViewById(R.id.save);
        close=popupxml.findViewById(R.id.close);
        remove=popupxml.findViewById(R.id.remove);
        dialogbuider=new AlertDialog.Builder(thu_tab_Fragment2.this.getContext());
        dialogbuider.setView(popupxml);
        alertDialog=dialogbuider.create();

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loaithu.setText(loaithuArrayList.get(position).getLoaithu());
                flag=position;
                alertDialog.show();
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
            }
        });

        reference= FirebaseDatabase.getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("loaithu");
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
                                Toast.makeText(thu_tab_Fragment2.this.getContext(), String.valueOf(maxid), Toast.LENGTH_SHORT).show();
                            }
                            HashMap<String,String> hashMap=new HashMap<>();
                            hashMap.put("id",myid);
                            hashMap.put("pos",String.valueOf(flag));
                            hashMap.put("content",loaithu.getText().toString());
                            reference.child(""+((int)flag+1)).setValue(hashMap);
                            alertDialog.dismiss();
                            Toast.makeText(thu_tab_Fragment2.this.getContext(), "da them update loai chi", Toast.LENGTH_SHORT).show();
                        }


                    });
                }else{
                    reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if(task.getResult().exists()){
                                maxid =(int) task.getResult().getChildrenCount();
                                Toast.makeText(thu_tab_Fragment2.this.getContext(), String.valueOf(maxid), Toast.LENGTH_SHORT).show();
                            }
                            HashMap<String,String> hashMap=new HashMap<>();
                            hashMap.put("id",myid);
                            hashMap.put("pos",String.valueOf(maxid));
                            hashMap.put("content",loaithu.getText().toString());
                            reference.child(""+((int)maxid+1)).setValue(hashMap);
                            alertDialog.dismiss();
                            Toast.makeText(thu_tab_Fragment2.this.getContext(), "da them loai chi", Toast.LENGTH_SHORT).show();
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

        DatabaseReference ref= FirebaseDatabase.getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("loaithu");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                loaithuArrayList=new ArrayList<>();
                if(dataSnapshot==null){}
                else{
                    for (DataSnapshot dt : dataSnapshot.getChildren()) {
                        String content = dt.child("content").getValue(String.class);
                        String id = dt.child("pos").getValue(String.class);
                        if(content==null){}else{
                            loaithuArrayList.add(new loaithu(content,id));}
                    }
                    adapter = new loaithu_ctrl(thu_tab_Fragment2.this.getContext(), R.layout.loaithu_list, loaithuArrayList);
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