package com.node.bayi.ui.main.business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.node.bayi.R;
import com.node.bayi.base.BaseActivity;
import com.node.bayi.ui.adapter.InfoWindowAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 充电桩分布图页面
 */
public class ChargingpileActivity extends BaseActivity implements LocationSource, AMapLocationListener {
    /**
     * 进入充电桩的方法
     *
     * @param activity
     */
    public static void startChargingpileActivity(Activity activity) {
        activity.startActivity(new Intent(activity, ChargingpileActivity.class));
    }

    private static final String TAG = "ChargingpileActivity";
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.mMapView)
    MapView mMapView;

    AMap mAMap;
    Marker mMarker;

    LocationSource.OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);

    @Override
    protected int getContentId() {
        return R.layout.activity_chargingpile;
    }

    @Override
    protected void initView() {
        tvTitle.setText("充电桩分布");

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapView.onCreate(savedInstanceState);
        mAMap = mMapView.getMap();
        // 设置定位监听
        mAMap.setLocationSource(ChargingpileActivity.this);
// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
//        mAMap.setMyLocationEnabled(true);
//        mAMap.setMyLocationEnabled(false);
// 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);

        addMarker(34.6000180000, 119.1788210000, "国家电网海州区区服务中心", "武侯区大石区113号", 20);
        addMarker(34.6022610000, 119.1739500000, "国家电网海州区区服务中心2", "武侯区大石区11号", 12);
        addMarker(34.6006710000, 119.1759030000, "国家电网武侯区服务中心3", "武侯区大石区30号", 8);
        addMarker(34.5993290000, 119.1631570000, "国家电网武侯区服务中心", "武侯区大石区43号", 8);
        addMarker(32.0593520000, 118.7966230000, "国家电网武侯区服务中心2", "武侯区大石区20号", 8);
        addMarker(32.0606250000, 118.7874820000, "国家电网武侯区服务中心3", "武侯区大石区90号", 8);
        addMarker(32.0713540000, 118.8130170000, "国家电网武侯区服务中心4", "武侯区大石区1103号", 32);
        mAMap.moveCamera(CameraUpdateFactory.zoomTo(12));
        mAMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(32.0713540000, 118.8130170000)));
        mAMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                mMarker = marker;
                mAMap.setInfoWindowAdapter(new InfoWindowAdapter(ChargingpileActivity.this));
                return false;
            }
        });

        mAMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (mMarker != null) {
                    mMarker.hideInfoWindow();
                }
            }
        });
        mAMap.setOnMarkerDragListener(new AMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                if (mMarker != null) {
                    mMarker.hideInfoWindow();
                }
            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

            }
        });
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initListeter() {

    }

    @Override
    protected int statusColor() {
        return 0;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        if (mMapView != null) {
            mMapView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        if (mMapView != null) {
            mMapView.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        if (mAMap != null) {
            mAMap = null;
        }
        if (mMapView != null) {
            mMapView.onDestroy();
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(this);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(ChargingpileActivity.this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
            Log.e(TAG, "activate: 启动定位");
        }

    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                Log.e(TAG, "onLocationChanged: " + aMapLocation.getAddress());

            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e(TAG, "onLocationChanged: " + errText);
            }
        }
    }

    /**
     * 添加图层
     *
     * @param lat
     * @param lon
     * @param title
     * @param address
     * @param hour
     */
    public void addMarker(double lat, double lon, String title, String address, int hour) {
        mAMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title(title).snippet(address).period(hour));
//        mAMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title(title).snippet(address).period(hour).icon(bitmapDescriptor));
    }

    @OnClick({R.id.ivBack})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
        }
    }
}
