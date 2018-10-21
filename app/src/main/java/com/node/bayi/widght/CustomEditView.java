package com.node.bayi.widght;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by 孙伟
 * Date: 2018/10/9
 * Email: 1580440730@qq.com
 * Describe:
 */
@SuppressLint("AppCompatCustomView")
public class CustomEditView extends EditText {
    public CustomEditView(Context context) {
        super(context);
    }
    public CustomEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
