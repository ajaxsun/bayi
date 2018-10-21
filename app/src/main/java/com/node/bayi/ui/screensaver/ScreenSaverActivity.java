package com.node.bayi.ui.screensaver;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.bumptech.glide.Glide;
import com.node.bayi.R;
import com.node.bayi.utils.sp.PreferencesHelper;
import com.node.bayi.widght.FullVideoView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 屏保页面
 */
public class ScreenSaverActivity extends AppCompatActivity {
    private static final String TAG = "ScreenSaverActivity";
    @BindView(R.id.mBGABanner)
    Banner mBGABanner;

    Unbinder unbinder;
    @BindView(R.id.mRelative)
    RelativeLayout mRelative;
    @BindView(R.id.mVideoView)
    FullVideoView mVideoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_saver);
        unbinder = ButterKnife.bind(this);
        initDate();
        initListeter();
    }

    protected void initDate() {
        /**
         * true时获取是视频屏保flase是图片轮播屏保
         */
        if (PreferencesHelper.getBoolean("isPic", true)) {
            mVideoView.setVisibility(View.GONE);
            mBGABanner.setVisibility(View.VISIBLE);
//            mBGABanner.setPagingEnabled(false);
            if (PreferencesHelper.getInt("interval") == -1) {
                mBGABanner.setDelayTime(5000);
            } else {
                mBGABanner.setDelayTime(PreferencesHelper.getInt("interval") * 1000);
            }
            try {
                //本地图片数据（资源文件）
                List<Integer> list = new ArrayList<>();
                list.add(R.drawable.icon_01);
                list.add(R.drawable.icon_02);
                list.add(R.drawable.icon_03);
                list.add(R.drawable.icon_04);
                list.add(R.drawable.icon_05);
                list.add(R.drawable.icon_06);
                list.add(R.drawable.icon_07);
                list.add(R.drawable.icon_08);
                list.add(R.drawable.icon_09);
                list.add(R.drawable.icon_10);
                mBGABanner.setImages(list);
                mBGABanner.setImages(list)
                        .setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                Glide.with(context.getApplicationContext())
                                        .load(path)
                                        .into(imageView);
                            }
                        })
                        .start();
                mBGABanner.setBannerStyle(BannerConfig.NOT_INDICATOR);
                mBGABanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.top_out); //指定划入，划出动画；
                    }
                });
            } catch (Exception ex) {
                Log.e(TAG, "initDate: " + ex.getMessage());
            }

        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {//检查是否有了权限
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                } else {
                    //没有权限即动态申请
                    String[] st = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(this, st, 1);
                }
            }

            mBGABanner.setVisibility(View.GONE);
            mVideoView.setVisibility(View.VISIBLE);
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + R.raw.video);
//            mVideoView.setMediaController(new MediaController(this));
            mVideoView.setVideoURI(uri);
            mVideoView.requestFocus();
            mVideoView.start();
        }
    }

    protected void initListeter() {

        mRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.top_out); //指定划入，划出动画；
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + R.raw.video);
//            mVideoView.setMediaController(new MediaController(this));
                mVideoView.setVideoURI(uri);
                mVideoView.requestFocus();
                mVideoView.start();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoView.pause();
        }
        if (mBGABanner != null) {
            mBGABanner.stopAutoPlay();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mVideoView != null) {
            mVideoView.resume();
        }
        if (mBGABanner != null) {
            mBGABanner.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBGABanner != null) {
            mBGABanner.stopAutoPlay();
            mBGABanner.clearAnimation();
            mBGABanner = null;
        }
        if (mVideoView != null) {
            mVideoView.stopPlayback();
        }
    }
}
