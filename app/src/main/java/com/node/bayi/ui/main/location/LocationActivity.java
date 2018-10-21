package com.node.bayi.ui.main.location;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.node.bayi.R;
import com.node.bayi.base.BaseActivity;

/**
 * 经纬度维护页面
 */
public class LocationActivity extends BaseActivity {
    /**
     * 进入该页面的方法
     *
     * @param activity
     */
    public static void startLocationActivity(Activity activity) {
        activity.startActivity(new Intent(activity, LocationActivity.class));
    }

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @Override
    protected int getContentId() {
        return R.layout.activity_location;
    }

    @Override
    protected void initView() {
        tvTitle.setText("经纬度维护");
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initListeter() {

    }

    @OnClick(R.id.ivBack)
    public void Onclick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;

        }
    }

    @Override
    protected int statusColor() {
        return 0;
    }

}
