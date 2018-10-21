package com.node.bayi.ui.setting;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.node.bayi.R;
import com.node.bayi.base.BaseActivity;
import com.node.bayi.ui.main.business.BussinessManageActivity;
import com.node.bayi.ui.main.customer.CustomerActivity;
import com.node.bayi.ui.main.fileupload.FileFileUploadActivity;
import com.node.bayi.ui.main.location.LocationActivity;
import com.node.bayi.ui.main.menu.MenuActivity;

/**
 * 设置页面
 */
public class SettingActivity extends BaseActivity {
    /**
     * 进入设置页面的方法
     *
     * @param activity
     */
    public static void startSettingActivity(Activity activity) {
        activity.startActivity(new Intent(activity, SettingActivity.class));
    }

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivBack)
    ImageView ivBack;

    @Override
    protected int getContentId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        tvTitle.setText("设置");
    }

    @Override
    protected void initDate() {
        ivBack.setVisibility(View.GONE);
    }

    @Override
    protected void initListeter() {

    }

    @OnClick({R.id.btnBack, R.id.llBussinessContent, R.id.llCustomerRepair, R.id.llPwdUpdata, R.id.llScreenSave, R.id.llMenuOnclick, R.id.llLocationOnclick, R.id.llFilePath})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.llBussinessContent:
                BussinessManageActivity.startBussinessManageActivity(this);
                break;
            case R.id.llCustomerRepair:
                CustomerActivity.startCustomerActivity(this);
                break;
            case R.id.llPwdUpdata:
                PwdActivity.startPwdActivity(this);
                break;
            case R.id.llScreenSave:
                ScreenSaveSettingActivity.startScreenSaveSettingActivity(this);
                break;
            case R.id.llMenuOnclick:
                MenuActivity.startMenuActivity(this);
                break;
            case R.id.llLocationOnclick:
                LocationActivity.startLocationActivity(this);
                break;
            case R.id.llFilePath:
                FileFileUploadActivity.startFileFileUploadActivity(this);
                break;
        }
    }

    @Override
    protected int statusColor() {
        return 0;
    }

}
