//package com.node.bayi.widght;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//
//import cn.bingoogolapple.bgabanner.BGABanner;
//
///**
// * Created by 孙伟
// * Date: 2018/9/29
// * Email: 1580440730@qq.com
// * Describe:  禁止轮播图手动滑动
// */
//public class CusBanner extends BGABanner {
//    private boolean enabled = false;
//
//
//    public CusBanner(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public CusBanner(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        // 触摸事件不触发
//        if (this.enabled) {
//            return super.onTouchEvent(event);
//        }
//        return false;
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
//        // 不处理触摸拦截事件
//        return this.enabled;
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        // 分发事件，这个是必须要的，如果把这个方法覆盖了，那么ViewPager的子View就接收不到事件了
//        return this.enabled;
//    }
//
//    public void setPagingEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }
//
//}
