package com.node.bayi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Marker;
import com.node.bayi.R;

/**
 * Created by 孙伟
 * Date: 2018/10/13
 * Email: 1580440730@qq.com
 * Describe:
 */
public class InfoWindowAdapter implements AMap.InfoWindowAdapter {
    Context context;

    public InfoWindowAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View view = LayoutInflater.from(context).inflate(R.layout.infowindowadapter, null);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvAddress = view.findViewById(R.id.tvAddress);
        TextView tvHour = view.findViewById(R.id.tvHour);
        tvTitle.setText(marker.getTitle() + "");
        tvAddress.setText("地址：" + marker.getSnippet() + "");
        tvHour.setText("营业：" + marker.getPeriod() + "小时");
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
