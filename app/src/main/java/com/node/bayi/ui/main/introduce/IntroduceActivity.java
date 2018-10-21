package com.node.bayi.ui.main.introduce;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.node.bayi.R;
import com.node.bayi.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 业务介绍页面
 */
public class IntroduceActivity extends BaseActivity {


    /**
     * 启动页面介绍的页面
     *
     * @param activity
     */
    public static void startIntroduceActivity(Activity activity) {
        activity.startActivity(new Intent(activity, IntroduceActivity.class));
    }

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.meLectricContent)
    RelativeLayout meLectricContent;//节能用电常识介绍
    @BindView(R.id.mSecurityContent)
    RelativeLayout mSecurityContent;//安全用电常识介绍
    @BindView(R.id.mProductContent)
    RelativeLayout mProductContent;//电能替代产品技术介绍
    @BindView(R.id.mPhotovoltaicContent)
    RelativeLayout mPhotovoltaicContent;//分布式光伏技术项目介绍

    @Override
    protected int getContentId() {
        return R.layout.activity_introduce;
    }

    @Override
    protected void initView() {
        tvTitle.setText("业务介绍");
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initListeter() {

    }

    @OnClick({R.id.ivBack, R.id.meLectricContent, R.id.mSecurityContent, R.id.mProductContent, R.id.mPhotovoltaicContent})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.meLectricContent:
//                Toast.makeText(this, "节能用电常识介绍", Toast.LENGTH_SHORT).show();
                LectricActivity.startLectricActivity(this);
                break;
            case R.id.mSecurityContent:
//                Toast.makeText(this, "安全用电常识介绍", Toast.LENGTH_SHORT).show();
                SecurityActivity.startSecurityActivity(this);
                break;
            case R.id.mProductContent:
//                Toast.makeText(this, "电能替代产品技术介绍", Toast.LENGTH_SHORT).show();
                EnergyIntroduceActivity.startEnergyIntroduceActivity(this);
                break;
            case R.id.mPhotovoltaicContent:
                DistributIntroduceActivity.startDistributIntroduceActivity(this);
                break;
        }
    }

    @Override
    protected int statusColor() {
        return 0;
    }

}
