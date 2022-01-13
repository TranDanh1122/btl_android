package com.example.btl_final_final.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.btl_final_final.R;
import com.example.btl_final_final.model.khoanthu;

import java.util.List;

public class khoanthu_ctrl extends BaseAdapter {
    final private Context context;
    final private int layout;
    final private List<khoanthu> khoanthuList;

    public khoanthu_ctrl(Context context, int layout, List<khoanthu> khoanthuList) {
        this.context = context;
        this.layout = layout;
        this.khoanthuList = khoanthuList;
    }

    @Override
    public int getCount() {
        return khoanthuList.size();
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
        TextView content = (TextView) convertView.findViewById(R.id.khoanthu);
        khoanthu khoanthu = khoanthuList.get(position);
        content.setText(khoanthu.getKhoanthu());
        return convertView;

    }
}
