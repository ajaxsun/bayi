package com.node.bayi;

import android.app.Application;
import android.os.Handler;

import com.node.bayi.utils.sp.PreferencesHelper;

import java.io.File;


/**
 * Created by 孙伟
 * Date: 2018/10/9
 * Email: 1580440730@qq.com
 * Describe:
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PreferencesHelper.init(this);
    }

}
