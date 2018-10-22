package com.node.bayi.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.node.bayi.R;
import com.node.bayi.ui.screensaver.ScreenSaverActivity;
import com.node.bayi.utils.AppManager;
import com.node.bayi.utils.sp.PreferencesHelper;

import java.io.File;
import java.util.Date;

/**
 * Created by 孙伟
 * Date: 2018/9/28
 * Email: 1580440730@qq.com
 * Describe:
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    //加载试图
    protected abstract int getContentId();

    //控件初始化
    protected abstract void initView();

    //加载数据
    protected abstract void initDate();

    //监听
    protected abstract void initListeter();

    protected abstract int statusColor();

    private Handler mHandler01 = new Handler();
    private Handler mHandler02 = new Handler();
    /* 上一次User有动作的Time Stamp */
    private Date lastUpdateTime;
    /* 计算User有几秒没有动作的 */
    private long timePeriod;
    /* 静止超过N秒将自动进入屏保 */
    private float mHoldStillTime = 15;
    /*标识当前是否进入了屏保*/
    private boolean isRunScreenSaver;
    /*时间间隔*/
    private long intervalScreenSaver = 1000;
    private long intervalKeypadeSaver = 1000;

    Unbinder unbinder = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContentId() != 0) {
            setContentView(getContentId());
        }
        unbinder = ButterKnife.bind(this);
        if (PreferencesHelper.getInt("sceenM") == -1) {
            mHoldStillTime = 15;
        } else {
            mHoldStillTime = PreferencesHelper.getInt("sceenM");
        }
        initView();
        /**
         * 获取
         */
        initDate();
        /**
         * 点击事件的方法
         */
        initListeter();
        //activity控制站
        AppManager.getInstance().addActivity(this);
        /* 初始取得User可触碰屏幕的时间 */
        lastUpdateTime = new Date(System.currentTimeMillis());
    }


    /**
     * 计时线程
     */
    private Runnable mTask01 = new Runnable() {
        @Override
        public void run() {
            Date timeNow = new Date(System.currentTimeMillis());
            /* 计算User静止不动作的时间间距 */
            /**当前的系统时间 - 上次触摸屏幕的时间 = 静止不动的时间**/
            timePeriod = (long) timeNow.getTime() - (long) lastUpdateTime.getTime();                        /*将静止时间毫秒换算成秒*/
            float timePeriodSecond = ((float) timePeriod / 1000);
            if (timePeriodSecond > mHoldStillTime) {
//                Log.e(TAG, "run:-time>15秒---- " + timePeriodSecond);
                if (isRunScreenSaver == false) {
                    //说明没有进入屏保
                    /* 启动线程去显示屏保 */
                    mHandler02.postAtTime(mTask02, intervalScreenSaver);
                    /*显示屏保置为true*/
                    isRunScreenSaver = true;
                } else {
                    /*屏保显示*/
                    if (isExitsPic()) {
                        showScreenSaver();
//                        Toast.makeText(BaseActivity.this, "请配置到轮播图文件夹下配置轮播图片", Toast.LENGTH_SHORT).show();
                    } else {

                    }
                }
            } else {
//                Log.e(TAG, "run:-time<15秒---- " + timePeriodSecond);
                /*说明静止之间没有超过规定时长*/
                isRunScreenSaver = false;
            }            /*反复调用自己进行检查*/
            mHandler01.postDelayed(mTask01, intervalKeypadeSaver);
        }
    };
    /**
     * 持续屏保显示线程
     */
    private Runnable mTask02 = new Runnable() {
        @Override
        public void run() {
            if (isRunScreenSaver == true) {
                //如果屏保正在显示，就计算不断持续显示  //
                if (isExitsPic()) {
                    showScreenSaver();
                } else {
                    /*显示屏保置为true*/
                    isRunScreenSaver = false;
//                    Toast.makeText(BaseActivity.this, "请配置到轮播图文件夹下配置轮播图片", Toast.LENGTH_SHORT).show();
                }
                mHandler02.postDelayed(mTask02, intervalScreenSaver);
            } else {
                mHandler02.removeCallbacks(mTask02);  //如果屏保没有显示则移除线程
            }
        }
    };

    /**
     * 显示屏保
     */
    private void showScreenSaver() {
//        Log.d("danxx", "显示屏保------>");
        Intent intent = new Intent(this, ScreenSaverActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out); //指定划入，划出动画；
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        updateUserActionTime();
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        updateUserActionTime();
        return super.dispatchTouchEvent(ev);
    }

    /*用户有操作的时候不断重置静止时间和上次操作的时间*/
    public void updateUserActionTime() {
        Date timeNow = new Date(System.currentTimeMillis());
        timePeriod = timeNow.getTime() - lastUpdateTime.getTime();
        lastUpdateTime.setTime(timeNow.getTime());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                updateUserActionTime();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onResume() {
        /*activity显示的时候启动线程*/
        updateUserActionTime();
        mHandler01.postAtTime(mTask01, intervalKeypadeSaver);
        super.onResume();
    }

    @Override
    protected void onPause() {
        /*activity不可见的时候取消线程*/
        mHandler01.removeCallbacks(mTask01);
        mHandler02.removeCallbacks(mTask02);
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    private boolean isExitsPic() {

        File screensavePath = new File(PreferencesHelper.getData("screensavePath") + "");
        if (screensavePath.isDirectory()) {
            for (File file : screensavePath.listFiles()) {
                String path = file.getAbsolutePath();
                if (path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".png")) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
