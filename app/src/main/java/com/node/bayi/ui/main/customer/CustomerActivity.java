package com.node.bayi.ui.main.customer;

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
 * 客户经理信息维护
 */
public class CustomerActivity extends BaseActivity {

    public static void startCustomerActivity(Activity activity) {
        activity.startActivity(new Intent(activity, CustomerActivity.class));
    }

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.rlUpload)
    RelativeLayout rlUpload;

    @Override
    protected int getContentId() {
        return R.layout.activity_customer;
    }

    @Override
    protected void initView() {
        tvTitle.setText("客户经理信息维护");
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initListeter() {

    }

    @OnClick({R.id.ivBack, R.id.rlUpload, R.id.rlWeixinUpload})
    public void Onlick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.rlUpload:
                Toast.makeText(this, "上传", Toast.LENGTH_SHORT).show();
                break;

            case R.id.rlWeixinUpload:
                Toast.makeText(this, "微信上传", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected int statusColor() {
        return 0;
    }
}
