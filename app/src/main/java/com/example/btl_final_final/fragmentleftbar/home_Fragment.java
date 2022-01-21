package com.example.btl_final_final.fragmentleftbar;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.btl_final_final.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    HashMap<String,Integer> hashMapthu=new HashMap<>();
    HashMap<String,Integer> hashMapchi=new HashMap<>();
    View rootview;
    ArrayList<BarEntry> tongthu,tongchi;
    public home_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static home_Fragment newInstance(String param1, String param2) {
        home_Fragment fragment = new home_Fragment();
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
        rootview=inflater.inflate(R.layout.fragment_home_, container, false);
        BarChart barChartthu=rootview.findViewById(R.id.barchartthu);
        DatabaseReference refgetkhoanthu= FirebaseDatabase.getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("khoanthu");
        refgetkhoanthu.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.getResult().exists()) {
                    tongthu=new ArrayList<>();

                    hashMapthu.put("01",0);
                    hashMapthu.put("02",0);
                    hashMapthu.put("03",0);
                    hashMapthu.put("04",0);
                    hashMapthu.put("05",0);
                    hashMapthu.put("06",0);
                    hashMapthu.put("07",0);
                    hashMapthu.put("08",0);
                    hashMapthu.put("09",0);
                    hashMapthu.put("10",0);
                    hashMapthu.put("11",0);
                    hashMapthu.put("12",0);
                    for(DataSnapshot dt: task.getResult().getChildren()){
                        String month=dt.child("month").getValue().toString();

                        FirebaseUser currentuserid= FirebaseAuth.getInstance().getCurrentUser();
                        String myid=currentuserid.getUid();
                        String id=dt.child("id").getValue().toString();
                        if(myid.equals(id)){
                            int tienthu=Integer.parseInt(dt.child("content").getValue().toString());
                            int daco=hashMapthu.get(month);
                            hashMapthu.put(month,daco+tienthu);}



                    }

                    for(int i=1;i<=12;i++){

                        tongthu.add(new BarEntry(i, i<10?hashMapthu.get("0"+i):hashMapthu.get(String.valueOf(i))));
                    }
                    BarDataSet barDataSetthu=new BarDataSet(tongthu,"Tong thu");
                    barDataSetthu.setColors(Color.RED);
                    barDataSetthu.setValueTextColor(Color.BLACK);
                    barDataSetthu.setValueTextSize(14f);
                    BarData barData=new BarData(barDataSetthu);
                    barChartthu.setFitBars(true);
                    barChartthu.setData(barData);
                    barChartthu.getDescription().setText("Tong thu hang thang");
                    barChartthu.animateY(2000);
                }
            }
        });


        BarChart barChartchi=rootview.findViewById(R.id.barchartchi);





        DatabaseReference refgetkhoanchi= FirebaseDatabase.getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("khoanchi");
        refgetkhoanchi.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.getResult().exists()) {
                    tongchi=new ArrayList<>();

                    hashMapchi.put("01",0);
                    hashMapchi.put("02",0);
                    hashMapchi.put("03",0);
                    hashMapchi.put("04",0);
                    hashMapchi.put("05",0);
                    hashMapchi.put("06",0);
                    hashMapchi.put("07",0);
                    hashMapchi.put("08",0);
                    hashMapchi.put("09",0);
                    hashMapchi.put("10",0);
                    hashMapchi.put("11",0);
                    hashMapchi.put("12",0);
                    for(DataSnapshot dt: task.getResult().getChildren()){
                        String month=dt.child("month").getValue().toString();

                        FirebaseUser currentuserid= FirebaseAuth.getInstance().getCurrentUser();
                        String myid=currentuserid.getUid();
                        String id=dt.child("id").getValue().toString();
                        if(myid.equals(id)){
                            int tienthu=Integer.parseInt(dt.child("content").getValue().toString());
                            int daco=hashMapchi.get(month);
                            hashMapchi.put(month,daco+tienthu);}



                    }

                    for(int i=1;i<=12;i++){

                        tongchi.add(new BarEntry(i, i<10?hashMapchi.get("0"+i):hashMapchi.get(String.valueOf(i))));
                    }
                    BarDataSet barDataSetchi=new BarDataSet(tongchi,"Tong chi");
                    barDataSetchi.setColors(Color.BLUE);
                    barDataSetchi.setValueTextColor(Color.BLACK);
                    barDataSetchi.setValueTextSize(14f);
                    BarData barData=new BarData(barDataSetchi);
                    barChartchi.setFitBars(true);
                    barChartchi.setData(barData);
                    barChartchi.getDescription().setText("Tong chi hang thang");
                    barChartchi.animateY(2000);
                }
            }
        });



        return rootview;
    }
}