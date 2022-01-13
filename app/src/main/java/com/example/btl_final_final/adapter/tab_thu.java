package com.example.btl_final_final.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.btl_final_final.fragmentleftbar.thu_tab_Fragment;
import com.example.btl_final_final.fragmentleftbar.thu_tab_Fragment2;

public class tab_thu extends FragmentStateAdapter {
    public String title[]={"Khoang thu","Loai thu"};
    private thu_tab_Fragment tab1;
    private thu_tab_Fragment2 tab2;

    public tab_thu(@NonNull Fragment fragment) {
        super(fragment);
        tab1=new thu_tab_Fragment();
       tab2=new thu_tab_Fragment2();
    }
//    public tab_thu(@NonNull FragmentActivity fragmentActivity) {
//        super(fragmentActivity);
//        tab1=new thu_tab_Fragment();
//        tab2=new thu_tab_Fragment2();
//
//    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0){
            return tab1;
        }else if(position==1){
            return tab2;
        }
        else{

        }
        return null;
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public  CharSequence getPageTitle(int position){
        return title[position];
    }

}
