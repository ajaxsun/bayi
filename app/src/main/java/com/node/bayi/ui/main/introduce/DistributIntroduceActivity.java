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
 * 电动汽车充电桩建设 页面
 */
public class DistributIntroduceActivity extends BaseActivity {

    /**
     * 进入电动汽车充电桩建设的方法
     *
     * @param activity
     */
    public static void startDistributIntroduceActivity(Activity activity) {
        activity.startActivity(new Intent(activity, DistributIntroduceActivity.class));
    }

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivImageview)
    ImageView ivImageview;

    @Override
    protected int getContentId() {
        return R.layout.activity_distributintroduce;
    }

    @Override
    protected void initView() {
        tvTitle.setText("电动汽车充电桩建设");
//        mPDFViewPager = findViewById(R.id.mPDFViewPager);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(PreferencesHelper.getData("bunsinessPath") + "/电动汽车充电桩建设.jpg"));
            ivImageview.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "请配置电动汽车充电桩建设文件", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initDate() {
        ivImageview.setImageBitmap(Utils.readBitMap(this, R.drawable.icon_chongdianzhuang));
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
