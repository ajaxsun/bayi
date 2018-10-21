package com.node.bayi.ui.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.node.bayi.R;
import com.node.bayi.base.BaseActivity;
import com.node.bayi.ui.main.business.BusinessActivity;
import com.node.bayi.ui.main.introduce.IntroduceActivity;
import com.node.bayi.ui.setting.SettingActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivSetting)
    ImageView ivSetting;

    @Override
    protected int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.GONE);
        tvTitle.setText("台区经理查询");
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

    long exitTime=0;

    @OnClick({R.id.tvTitle, R.id.ivSetting, R.id.tvIntroduce, R.id.tvBusiness})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tvTitle:
                if (System.currentTimeMillis() - exitTime > 2000) {
                    exitTime = System.currentTimeMillis();
                } else {
                    SettingActivity.startSettingActivity(this);
                }
                break;
            case R.id.tvIntroduce:
                IntroduceActivity.startIntroduceActivity(this);
                break;
            case R.id.tvBusiness:
                BusinessActivity.startBusinessActivity(this);
                break;
            case R.id.ivSetting:
                SettingActivity.startSettingActivity(this);
                break;
        }
    }


    @Override
    protected int statusColor() {
        return 0;
    }


}