package com.node.bayi.ui.setting;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.OnClick;
import com.node.bayi.R;
import com.node.bayi.base.BaseActivity;
import com.node.bayi.utils.EditViewUtils;
import com.node.bayi.utils.sp.PreferencesHelper;

/**
 * 屏保设置页面
 */
public class ScreenSaveSettingActivity extends BaseActivity {

    /**
     * 启动屏保页面的方法
     *
     * @param activity
     */
    public static void startScreenSaveSettingActivity(Activity activity) {
        activity.startActivity(new Intent(activity, ScreenSaveSettingActivity.class));
    }


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.etInterM)
    EditText etInterM;
    @BindView(R.id.llContent)
    LinearLayout llContent;
    @BindView(R.id.etJionScreen)
    EditText etJionScreen;
    @BindView(R.id.rgContent)
    RadioGroup rgContent;
    @BindView(R.id.rbPic)
    RadioButton rbPic;
    @BindView(R.id.rbVidea)
    RadioButton rbVidea;

    @Override
    protected int getContentId() {
        return R.layout.activity_screen_save_setting;
    }

    @Override
    protected void initView() {
        tvTitle.setText("屏保设置");
    }

    @Override
    protected void initDate() {

        if (PreferencesHelper.getInt("interval") == -1) {
            etInterM.setText("5");
        } else {
            etInterM.setText(PreferencesHelper.getInt("interval") + "");
        }
        if (PreferencesHelper.getBoolean("isPic", true)) {
            rbPic.setChecked(true);
        } else {
            rbVidea.setChecked(true);
        }


        if (PreferencesHelper.getInt("sceenM") == -1) {
            etJionScreen.setText("15");
        } else {
            etJionScreen.setText(PreferencesHelper.getInt("sceenM") + "");
        }
        etInterM.setSelection(etInterM.getText().length());
        etJionScreen.setSelection(etJionScreen.getText().length());
    }

    @Override
    protected void initListeter() {
        rgContent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbPic:
                        PreferencesHelper.saveBoolean("isPic", true);
                        break;
                    case R.id.rbVidea:
                        PreferencesHelper.saveBoolean("isPic", false);
                        break;
                }

            }
        });
    }

    @OnClick({R.id.ivBack, R.id.btnSave})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.btnSave:
                EditViewUtils.hide(this, llContent);
                if (!TextUtils.isEmpty(etInterM.getText().toString())) {
                    if (Integer.valueOf(etInterM.getText().toString()) < 3 || Integer.valueOf(etInterM.getText().toString()) > 5 * 60) {
                        Toast.makeText(this, "请设置屏保的切换页面时间在3秒~5分钟内", Toast.LENGTH_SHORT).show();
                    } else {
                        PreferencesHelper.saveInt("interval", Integer.valueOf(etInterM.getText().toString()));
                        if (!TextUtils.isEmpty(etJionScreen.getText().toString())) {
                            if (Integer.valueOf(etJionScreen.getText().toString()) < 10 || Integer.valueOf(etJionScreen.getText().toString()) > 10 * 60) {
                                Toast.makeText(this, "请设置进入屏保的时间在10秒~10分钟内", Toast.LENGTH_SHORT).show();
                            } else {
                                PreferencesHelper.saveInt("sceenM", Integer.valueOf(etJionScreen.getText().toString()));
                                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } else {
                            Toast.makeText(this, "请输入进入屏保的时间", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(this, "请输入停留时间", Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }

    @Override
    protected int statusColor() {
        return 0;
    }

}
