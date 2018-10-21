package com.node.bayi.ui.main.menu;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.node.bayi.R;
import com.node.bayi.base.BaseActivity;
import com.node.bayi.utils.sp.PreferencesHelper;

/**
 * 菜单维护
 */
public class MenuActivity extends BaseActivity {

    /**
     * j进入该页面的方法
     *
     * @param activity
     */
    public static void startMenuActivity(Activity activity) {
        activity.startActivity(new Intent(activity, MenuActivity.class));
    }

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.cb001)
    CheckBox cb001;
    @BindView(R.id.cb002)
    CheckBox cb002;
    @BindView(R.id.cb003)
    CheckBox cb003;
    @BindView(R.id.cb004)
    CheckBox cb004;
    @BindView(R.id.cb005)
    CheckBox cb005;
    @BindView(R.id.cb006)
    CheckBox cb006;
    @BindView(R.id.cb007)
    CheckBox cb007;

    @Override
    protected int getContentId() {
        return R.layout.activity_menu;
    }

    @Override
    protected void initView() {
        tvTitle.setText("菜单维护");
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initListeter() {

    }

    @OnClick({R.id.ivBack, R.id.btnSave})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.btnSave:
                saveData();
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PreferencesHelper.getBoolean("cb001", true)) {
            cb001.setChecked(true);
        } else {
            cb001.setChecked(false);
        }
        if (PreferencesHelper.getBoolean("cb002", true)) {
            cb002.setChecked(true);
        } else {
            cb002.setChecked(false);
        }
        if (PreferencesHelper.getBoolean("cb003", true)) {
            cb003.setChecked(true);
        } else {
            cb003.setChecked(false);
        }
        if (PreferencesHelper.getBoolean("cb004", true)) {
            cb004.setChecked(true);
        } else {
            cb004.setChecked(false);
        }
        if (PreferencesHelper.getBoolean("cb005", true)) {
            cb005.setChecked(true);
        } else {
            cb005.setChecked(false);
        }
        if (PreferencesHelper.getBoolean("cb006", true)) {
            cb006.setChecked(true);
        } else {
            cb006.setChecked(false);
        }
        if (PreferencesHelper.getBoolean("cb007", true)) {
            cb007.setChecked(true);
        } else {
            cb007.setChecked(false);
        }
    }

    private void saveData() {
        if (cb001.isChecked()) {
            PreferencesHelper.saveBoolean("cb001", true);
        } else {
            PreferencesHelper.saveBoolean("cb001", false);
        }
        if (cb002.isChecked()) {
            PreferencesHelper.saveBoolean("cb002", true);
        } else {
            PreferencesHelper.saveBoolean("cb002", false);
        }
        if (cb003.isChecked()) {
            PreferencesHelper.saveBoolean("cb003", true);
        } else {
            PreferencesHelper.saveBoolean("cb003", false);
        }
        if (cb004.isChecked()) {
            PreferencesHelper.saveBoolean("cb004", true);
        } else {
            PreferencesHelper.saveBoolean("cb004", false);
        }
        if (cb005.isChecked()) {
            PreferencesHelper.saveBoolean("cb005", true);
        } else {
            PreferencesHelper.saveBoolean("cb005", false);
        }
        if (cb006.isChecked()) {
            PreferencesHelper.saveBoolean("cb006", true);
        } else {
            PreferencesHelper.saveBoolean("cb006", false);
        }
        if (cb007.isChecked()) {
            PreferencesHelper.saveBoolean("cb007", true);
        } else {
            PreferencesHelper.saveBoolean("cb007", false);
        }
    }

    @Override
    protected int statusColor() {
        return 0;
    }

}
