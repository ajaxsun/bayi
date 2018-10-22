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
 * 电能替代产品技术介绍
 */
public class EnergyIntroduceActivity extends BaseActivity {

    /**
     * 进入电能替代产品技术介绍的方法
     *
     * @param activity
     */
    public static void startEnergyIntroduceActivity(Activity activity) {
        activity.startActivity(new Intent(activity, EnergyIntroduceActivity.class));
    }

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivImageview)
    ImageView ivImageview;

    @Override
    protected int getContentId() {
        return R.layout.activity_energyintroduce;
    }

    @Override
    protected void initView() {
        tvTitle.setText("电能替代产品技术介绍");
    }

    @Override
    protected void initDate() {
//        ivImageview.setImageBitmap( Utils.readBitMap(this,R.drawable.icon_tidaichanping));
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(PreferencesHelper.getData("bunsinessPath") + "/电能替代产品技术介绍.jpg"));
            ivImageview.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "请配置电能替代产品技术介绍文件", Toast.LENGTH_SHORT).show();
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
