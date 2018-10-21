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

//import com.joanzapata.pdfview.PDFView;
//import com.joanzapata.pdfview.listener.OnDrawListener;
//import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
//import com.joanzapata.pdfview.listener.OnPageChangeListener;

/**
 * 安全用电的方法
 */
public class SecurityActivity extends BaseActivity {

    /**
     * 进入安全用电的方法
     *
     * @param activity
     */
    public static void startSecurityActivity(Activity activity) {
        activity.startActivity(new Intent(activity, SecurityActivity.class));
    }

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivImageview)
    ImageView ivImageview;


    @Override
    protected int getContentId() {
        return R.layout.activity_security;
    }

    @Override
    protected void initView() {
        tvTitle.setText("安全用电常识介绍");
    }

    @Override
    protected void initDate() {
        ivImageview.setImageBitmap(Utils.readBitMap(this, R.drawable.icon_anquanchangshi));
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
//
//    }
}
