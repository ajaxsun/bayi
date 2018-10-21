package com.node.bayi.ui.main.fileupload;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.node.bayi.R;
import com.node.bayi.base.BaseActivity;

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

    @Override
    protected int getContentId() {
        return R.layout.activity_file_file_upload;
    }

    @Override
    protected void initView() {
        tvTitle.setText("文件路径维护");
    }

    @Override
    protected void initDate() {

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
