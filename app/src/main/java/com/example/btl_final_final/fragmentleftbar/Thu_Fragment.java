package com.example.btl_final_final.fragmentleftbar;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.btl_final_final.R;
import com.example.btl_final_final.adapter.tab_thu;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Thu_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Thu_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ViewPager2 myview;
    private TabLayout mtab;
    private tab_thu tabadapter;
    public View rootview;
    public Thu_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Thu_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Thu_Fragment newInstance(String param1, String param2) {
        Thu_Fragment fragment = new Thu_Fragment();
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
        rootview=inflater.inflate(R.layout.thu_fragment, container, false);
        myview=rootview.findViewById(R.id.view_thu);
        mtab=rootview.findViewById(R.id.tab_thu);
        tabadapter=new tab_thu(this);
        myview.setAdapter(tabadapter);
        new TabLayoutMediator(mtab, myview, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Khoan Thu");
                    break;
                case 1: tab.setText("Loai thu");
                break;
            }
        }).attach();

        return rootview;
    }
}