package com.node.bayi.ui.main.fileupload;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.node.bayi.R;
import com.node.bayi.base.BaseActivity;
import com.node.bayi.utils.sp.PreferencesHelper;

import java.io.File;

/**
 * 文件上传页面
 */
public class FileFileUploadActivity extends BaseActivity {

    /**
     * 进入文件上传的页面
     *
     * @param activity
     */
    public static void startFileFileUploadActivity(Activity activity) {
        activity.startActivity(new Intent(activity, FileFileUploadActivity.class));
    }

    @BindView(R.id.tvTitle)
    TextView tvTitle;//标题
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvyewuFile)
    TextView tvyewuFile;
    @BindView(R.id.tvScreenSaveFile)
    TextView tvScreenSaveFile;

    @Override
    protected int getContentId() {
        return R.layout.activity_file_file_upload;
    }

    File file;
    File file1;

    @Override
    protected void initView() {
        tvTitle.setText("文件路径维护");
    }

    @Override
    protected void initDate() {
        tvyewuFile.setText(PreferencesHelper.getData("bunsinessPath") + "");
        tvScreenSaveFile.setText(PreferencesHelper.getData("screensavePath") + "");
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
                if (ver()) {
                    PreferencesHelper.saveData("bunsinessPath", tvyewuFile.getText().toString());
                    PreferencesHelper.saveData("screensavePath", tvScreenSaveFile.getText().toString());
                    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected int statusColor() {
        return 0;
    }


    /**
     * 校验输入的 目录路径
     *
     * @return
     */
    private boolean ver() {
        File file = new File(tvyewuFile.getText().toString());
        if (!file.exists()) {
            Toast.makeText(this, "该业务文件路径不存在，请重新配置", Toast.LENGTH_SHORT).show();
            return false;
        }
        File file2 = new File(tvScreenSaveFile.getText().toString());
        if (!file2.exists()) {
            Toast.makeText(this, "该轮播图文件不存在，请重新配置", Toast.LENGTH_SHORT).show();
            return false;

        }

        return true;
    }

}
