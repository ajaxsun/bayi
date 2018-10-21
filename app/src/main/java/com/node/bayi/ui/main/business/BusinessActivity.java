package com.node.bayi.ui.main.business;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.node.bayi.R;
import com.node.bayi.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 业务介绍页面
 */
public class BusinessActivity extends BaseActivity {

    /**
     * 进入业务体验页面的方法
     *
     * @param activity
     */
    public static void startBusinessActivity(Activity activity) {
        activity.startActivity(new Intent(activity, BusinessActivity.class));
    }

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.mPeakVallyPriceContent)
    RelativeLayout mPeakVallyPriceContent;
    @BindView(R.id.mEnergyContent)
    RelativeLayout mEnergyContent;
    @BindView(R.id.mElectricVehicleContent)
    RelativeLayout mElectricVehicleContent;
    @BindView(R.id.mPhotovoltaicContent)
    RelativeLayout mPhotovoltaicContent;

    @Override
    protected int getContentId() {
        return R.layout.activity_business;
    }

    @Override
    protected void initView() {
        tvTitle.setText("业务体验");
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

    @OnClick({R.id.ivBack, R.id.mPeakVallyPriceContent, R.id.mEnergyContent, R.id.mElectricVehicleContent, R.id.mPhotovoltaicContent})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.mPeakVallyPriceContent:
                PeakVallyPriceActivity.startPeakVallyPriceActivity(BusinessActivity.this);
                break;
            case R.id.mEnergyContent:
                EnergyActivity.startEnergyActivity(this);
                break;
            case R.id.mElectricVehicleContent:
//                Toast.makeText(this, "电动汽车节能分析体验", Toast.LENGTH_SHORT).show();
                ChargingpileActivity.startChargingpileActivity(this);
                break;
            case R.id.mPhotovoltaicContent:
                DistributedActivity.startDistributedActivity(this);
                break;
        }
    }

}
