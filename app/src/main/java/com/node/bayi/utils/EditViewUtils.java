package com.node.bayi.utils;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 孙伟
 * Date: 2018/3/27
 * Email: 1580440730@qq.com
 * Describe: 控制输入框只能输入两位小数
 */

public class EditViewUtils {


    public static void editView(CharSequence s, EditText editText) {
        if (!isPayNumS(s.toString())) {
            s = "";
            editText.setText(s);
        }


        if (s.toString().contains(".")) {
            //只能有一个小数点
            if (s.toString().lastIndexOf(".") != s.toString().indexOf(".")) {
                s = s.toString().subSequence(0,
                        s.length() - 1);
                editText.setText(s);
                editText.setSelection(s.length());//todo
            }

            // 小数点后最多能输入两位
            if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                s = s.toString().subSequence(0,
                        s.toString().indexOf(".") + 3);
                editText.setText(s);
                editText.setSelection(s.length());
            }
        }

        // 如果第一位是.则前面加上0.
        if (s.toString().trim().substring(0).equals(".")) {
            s = "0" + s;
            editText.setText(s);
            editText.setSelection(2);
        }

        // 如果第一位是0则后面必须输入点，否则不能输入
        if (s.toString().startsWith("0")
                && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                editText.setText(s.subSequence(0, 1));
                editText.setSelection(1);
                return;
            }
        }
    }

    public static boolean isPayNumS(String targetString) {
        for (int i = 0; i < targetString.length(); i++) {
            if (!is029OrPoint(targetString.charAt(i) + "")) {
                return false;
            }
        }
        return true;

    }

    public static boolean is029OrPoint(String targetString) {
        Pattern p = Pattern.compile("[.0-9]");

        Matcher m = p.matcher(targetString);

        return m.matches();
    }


    public static void setHintSize(EditText editText, String info) {
        SpannableString ss = new SpannableString(info);//定义hint的值
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(12, true);//设置字体大小 true表示单位是sp
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.setHint(new SpannedString(ss));

    }

    public static void setHintSize(EditText editText, String info, int size) {
        SpannableString ss = new SpannableString(info);//定义hint的值
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, true);//设置字体大小 true表示单位是sp
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        editText.setHint(new SpannedString(ss));

    }


    /**
     * 隐藏软键盘
     */
    public static void hide(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
//            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
