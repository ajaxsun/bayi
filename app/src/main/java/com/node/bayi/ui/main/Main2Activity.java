package com.node.bayi.ui.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.node.bayi.R;
import com.node.bayi.base.BaseActivity;
import com.node.bayi.ui.main.business.DistributedActivity;
import com.node.bayi.ui.main.business.EnergyActivity;
import com.node.bayi.ui.main.business.PeakVallyPriceActivity;
import com.node.bayi.ui.main.introduce.DistributIntroduceActivity;
import com.node.bayi.ui.main.introduce.EnergyIntroduceActivity;
import com.node.bayi.ui.main.introduce.LectricActivity;
import com.node.bayi.ui.main.introduce.SecurityActivity;
import com.node.bayi.ui.setting.SettingActivity;

public class Main2Activity extends BaseActivity {
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
    @BindView(R.id.mPhotovoltaicContent2)
    RelativeLayout mPhotovoltaicContent2;

    @BindView(R.id.meLectricContent)
    RelativeLayout meLectricContent;//节能用电常识介绍
    @BindView(R.id.mSecurityContent)
    RelativeLayout mSecurityContent;//安全用电常识介绍
    @BindView(R.id.mProductContent)
    RelativeLayout mProductContent;//电能替代产品技术介绍
    @BindView(R.id.mPhotovoltaicContent)
    RelativeLayout mPhotovoltaicContent;//分布式光伏技术项目介绍

    @BindView(R.id.ivSetting)
    ImageView ivSetting;

    @Override
    protected int getContentId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.GONE);
        tvTitle.setText("首页");
        ivSetting.setVisibility(View.GONE);
    }

    @Override
    protected void initDate() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {//检查是否有了权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                //没有权限即动态申请
                String[] st = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                ActivityCompat.requestPermissions(this, st, 1);
            }
        }
    }

    @Override
    protected void initListeter() {

    }

    @Override
    protected int statusColor() {
        return 0;
    }

    long exitTime = 0;

    @OnClick({R.id.tvTitle, R.id.meLectricContent, R.id.mSecurityContent, R.id.mProductContent, R.id.mPhotovoltaicContent2,
            R.id.mPeakVallyPriceContent, R.id.mEnergyContent, R.id.mElectricVehicleContent, R.id.mPhotovoltaicContent})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tvTitle:
                if (System.currentTimeMillis() - exitTime > 2000) {
                    exitTime = System.currentTimeMillis();
                } else {
                    SettingActivity.startSettingActivity(this);
                }
                break;
            case R.id.meLectricContent:
                LectricActivity.startLectricActivity(this);
                break;
            case R.id.mSecurityContent:
                SecurityActivity.startSecurityActivity(this);
                break;
            case R.id.mProductContent:
                EnergyIntroduceActivity.startEnergyIntroduceActivity(this);
                break;
            case R.id.mPhotovoltaicContent2:
                DistributedActivity.startDistributedActivity(this);
                break;
            case R.id.mPeakVallyPriceContent:
                PeakVallyPriceActivity.startPeakVallyPriceActivity(this);
                break;
            case R.id.mEnergyContent:
                EnergyActivity.startEnergyActivity(this);
                break;
            case R.id.mElectricVehicleContent:
//                ChargingpileActivity.startChargingpileActivity(this);
                Toast.makeText(this, "暂未开放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mPhotovoltaicContent:
                DistributIntroduceActivity.startDistributIntroduceActivity(this);
                break;
        }
    }


}
