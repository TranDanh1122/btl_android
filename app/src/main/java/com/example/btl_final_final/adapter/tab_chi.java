package com.example.btl_final_final.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.btl_final_final.fragmentleftbar.chi_tab1_Fragment;
import com.example.btl_final_final.fragmentleftbar.chi_tab2_Fragment;
import com.example.btl_final_final.fragmentleftbar.thu_tab_Fragment;
import com.example.btl_final_final.fragmentleftbar.thu_tab_Fragment2;

public class tab_chi extends FragmentStateAdapter {
    public String title[]={"Khoang chi","Loai chi"};
    private chi_tab1_Fragment tab1;
    private chi_tab2_Fragment tab2;

    public tab_chi(@NonNull Fragment fragment) {
        super(fragment);

    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new chi_tab1_Fragment();

            case 1:
            return new chi_tab2_Fragment();

            default:
                return new chi_tab1_Fragment();

        }

    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public  CharSequence getPageTitle(int position){
        return title[position];
    }

}
