package com.node.bayi.ui.main.introduce;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.node.bayi.R;
import com.node.bayi.base.BaseActivity;
import com.node.bayi.utils.Utils;

/**
 * 节能用电常识
 */
public class LectricActivity extends BaseActivity {

    /**
     * 启动节能节点的页面
     *
     * @param activity
     */
    public static void startLectricActivity(Activity activity) {
        activity.startActivity(new Intent(activity, LectricActivity.class));
    }

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivImageview)
    ImageView ivImageview;

    @Override
    protected int getContentId() {
        return R.layout.activity_lectric;
    }

    @Override
    protected void initView() {
        tvTitle.setText("节能用电常识介绍");

    }

    @Override
    protected void initDate() {
//
        ivImageview.setImageBitmap(Utils.readBitMap(this, R.drawable.icon_jienengyongdian));
    }

    @Override
    protected void initListeter() {

    }

    @OnClick({R.id.ivBack})
    public void onclick(View view) {
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
