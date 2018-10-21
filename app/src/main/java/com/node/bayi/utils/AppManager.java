package com.node.bayi.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import java.util.ConcurrentModificationException;
import java.util.Stack;

public class AppManager {
    private static Stack<Activity> activityStack;
    private static AppManager appManager;

    private AppManager() {

    }

    public static AppManager getInstance() {
        if (appManager == null) {
            appManager = new AppManager();
        }
        return appManager;
    }

    /**
     * @param activity add
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 判断次activity 是否存在
     *
     * @param activity
     * @return
     */
    public boolean IsActivityEExistence(Activity activity) {
        for (int i = 0; i < activityStack.size(); i++) {
            if (activityStack.get(i) == activity) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * get
     */
    public Activity getCurrentActivity() {
        Activity activity = null;
        if (activityStack != null) {
            activity = activityStack.lastElement();
        }
        return activity;

    }

    /**
     * 获取指定的Activity
     *
     * @author kymjs
     */
    public Activity getActivity(Class<?> cls) {
        if (activityStack != null) for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * finish the specific activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            if (!activity.isFinishing()) {
                activity.onBackPressed();
                activity.finish();
                activity = null;

            }
        }
    }

    /**
     * finish all the activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * @param cls finish the specific classes.
     */
    public void finishActivity(Class<?> cls) {
        try {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    finishActivity(activity);
                }
            }
        } catch (ConcurrentModificationException ex) {
        }
    }

    /**
     * exit my application
     */
    @SuppressLint("MissingPermission")
    public void exitApplication(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Service
                    .ACTIVITY_SERVICE);
            activityManager.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {

        }

    }


}
