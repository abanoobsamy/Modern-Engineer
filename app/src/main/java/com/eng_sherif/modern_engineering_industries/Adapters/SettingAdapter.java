package com.eng_sherif.modern_engineering_industries.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eng_sherif.modern_engineering_industries.Model.Menu;
import com.eng_sherif.modern_engineering_industries.R;

import java.util.List;

public class SettingAdapter extends BaseAdapter {

    private Context context;
    private List<Menu> menus;

    public SettingAdapter(Context context, List<Menu> menus) {
        this.context = context;
        this.menus = menus;
    }

    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public Object getItem(int i) {
        return menus.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {

            view = LayoutInflater.from(context).inflate(R.layout.setting_item, viewGroup, false);
        }

        Menu menu = menus.get(i);

        ImageView image = view.findViewById(R.id.iv_setting_item);
        TextView text = view.findViewById(R.id.tv_setting_item);

        image.setImageResource(menu.getImage());
        text.setText(menu.getTitle());

        return view;
    }
}
