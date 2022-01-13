package com.example.btl_final_final.fragmentleftbar;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.btl_final_final.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

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
    View rootview;
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
        BarChart barChart=rootview.findViewById(R.id.barchart);
        ArrayList<BarEntry> tongthu=new ArrayList<>();
        tongthu.add(new BarEntry(1,100000));
        tongthu.add(new BarEntry(2,103000));
        tongthu.add(new BarEntry(3,1200000));
        tongthu.add(new BarEntry(4,1600000));
        tongthu.add(new BarEntry(5,300000));
        tongthu.add(new BarEntry(6,700000));
        tongthu.add(new BarEntry(7,11100000));
        tongthu.add(new BarEntry(8,1200000));
        tongthu.add(new BarEntry(9,200000));
        tongthu.add(new BarEntry(10,2100000));
        tongthu.add(new BarEntry(11,10110000));
        tongthu.add(new BarEntry(12,28100000));

        BarDataSet barDataSet=new BarDataSet(tongthu,"Tong thu");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(14f);
        BarData barData=new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Tong thu hang thang");
        barChart.animateY(2000);

        return rootview;
    }
}