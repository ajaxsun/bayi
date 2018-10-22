package com.node.bayi.ui.main.introduce;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.node.bayi.R;
import com.node.bayi.base.BaseActivity;
import com.node.bayi.utils.Utils;
import com.node.bayi.utils.sp.PreferencesHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
//        ivImageview.setImageBitmap(Utils.readBitMap(this, R.drawable.icon_jienengyongdian));
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(PreferencesHelper.getData("bunsinessPath") + "/节能用电常识介绍.jpg"));
            ivImageview.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "请配置节能用电常识介绍文件", Toast.LENGTH_SHORT).show();
        }
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
