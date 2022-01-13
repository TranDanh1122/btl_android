package com.example.btl_final_final.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.btl_final_final.R;
import com.example.btl_final_final.StartActivity;
import com.example.btl_final_final.fragmentleftbar.chi_tab2_Fragment;
import com.example.btl_final_final.model.khoanchi;
import com.example.btl_final_final.model.loaichi;
import com.example.btl_final_final.model.loaithu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class loaithu_ctrl extends BaseAdapter {
    final private Context context;
    final private int layout;
    final private List<loaithu> loaithuList;
    private AlertDialog.Builder dialogbuider_ctrl;
    private  AlertDialog alertDialog_ctrl;
    int maxid;


    public loaithu_ctrl(Context context, int layout, List<loaithu> loaithuList) {
        this.context = context;
        this.layout = layout;
        this.loaithuList = loaithuList;
    }

    @Override
    public int getCount() {
        return loaithuList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView =inflater.inflate(layout,null);
        TextView content = (TextView) convertView.findViewById(R.id.loaithu);
        loaithu loaithu = loaithuList.get(position);
        content.setText(loaithu.getLoaithu());
        return convertView;

    }
}
