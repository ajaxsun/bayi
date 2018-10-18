package com.node.bayi;

import android.app.Application;
import android.os.Handler;

import com.node.bayi.utils.sp.PreferencesHelper;

import java.io.File;

import es.voghdev.pdfviewpager.library.asset.CopyAsset;
import es.voghdev.pdfviewpager.library.asset.CopyAssetThreadImpl;

/**
 * Created by 孙伟
 * Date: 2018/10/9
 * Email: 1580440730@qq.com
 * Describe:
 */
public class App extends Application {
    final String[] sampleAssets = {"安全用电常识介绍.pdf", "电动汽车充电桩建设.pdf", "电能替代产品介绍.pdf", "节能用电常识介绍.pdf"};

    @Override
    public void onCreate() {
        super.onCreate();

        PreferencesHelper.init(this);
        initSampleAssets();
    }

    private void initSampleAssets() {
        CopyAsset copyAsset = new CopyAssetThreadImpl(this, new Handler());
        for (String asset : sampleAssets) {
            copyAsset.copy(asset, new File(getCacheDir(), asset).getAbsolutePath());
        }
    }
}
