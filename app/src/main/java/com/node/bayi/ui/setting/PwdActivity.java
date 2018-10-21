package com.node.bayi.ui.setting;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.node.bayi.R;
import com.node.bayi.base.BaseActivity;
import com.node.bayi.utils.EditViewUtils;

/**
 * 密码设置页面
 */
public class PwdActivity extends BaseActivity {

    /**
     * 进入密码设置页面的方法
     *
     * @param activity
     */
    public static void startPwdActivity(Activity activity) {
        activity.startActivity(new Intent(activity, PwdActivity.class));
    }

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.etOldPwd)
    TextView etOldPwd;
    @BindView(R.id.etNewPwd)
    TextView etNewPwd;
    @BindView(R.id.etOkPwd)
    TextView etOkPwd;
    @BindView(R.id.llContent)
    LinearLayout llContent;

    @Override
    protected int getContentId() {
        return R.layout.activity_pwd;
    }

    @Override
    protected void initView() {
        tvTitle.setText("修改密码");
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initListeter() {

    }

    @OnClick({R.id.ivBack, R.id.btnSave, R.id.btnReset})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.btnSave:
                EditViewUtils.hide(this, llContent);
                break;

            case R.id.btnReset:
                etNewPwd.setText("");
                etOkPwd.setText("");
                etOldPwd.setText("");
                EditViewUtils.hide(this, llContent);

                break;
        }
    }

    @Override
    protected int statusColor() {
        return 0;
    }

}
