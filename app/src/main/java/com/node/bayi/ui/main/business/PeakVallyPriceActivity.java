package com.node.bayi.ui.main.business;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.OnClick;
import com.node.bayi.R;
import com.node.bayi.base.BaseActivity;
import com.node.bayi.utils.EditViewUtils;

import java.math.BigDecimal;

/**
 * 峰谷电价实惠体验页面
 */
public class PeakVallyPriceActivity extends BaseActivity {

    /**
     * 启动这个页面的方法
     *
     * @param activity
     */
    public static void startPeakVallyPriceActivity(Activity activity) {
        activity.startActivity(new Intent(activity, PeakVallyPriceActivity.class));
    }

    @BindView(R.id.ivBack)
    ImageView ivBack;//返回按钮
    @BindView(R.id.tvTitle)
    TextView tvTitle;//标题
    @BindView(R.id.llContent)
    LinearLayout llContent;//容器

    @BindView(R.id.etPeak)
    EditText etPeak;//峰时的输入框
    @BindView(R.id.etValley)
    EditText etValley;//谷时的输入框

    @BindView(R.id.btnCalculation)
    Button btnCalculation;//计算按钮
    @BindView(R.id.btnReset)
    Button btnReset;//重置按钮

    @BindView(R.id.tvTotalDegress)
    TextView tvTotalDegress;//合计度数
    @BindView(R.id.tvAverageDegress)
    TextView tvAverageDegress;//平价度数
    @BindView(R.id.tvPeakPrice)
    TextView tvPeakPrice;//峰时价格
    @BindView(R.id.tvValleyPrice)
    TextView tvValleyPrice;//谷时价格
    @BindView(R.id.tvPeakAndValleyPrice)
    TextView tvPeakAndValleyPrice;//峰谷合计价格


    /**
     * 价格计算规格及其公式
     * <p>
     * 峰时度数：X=50/月
     * 谷时度数：Y=100/月
     * <p>
     * <p>
     * <p>
     * 平价电费 （元/度） A=0.5283
     * 峰时电价（元/度） B=0.5583
     * 谷时电价（元/度）C=0.3583
     * <p>
     * 输出价格计算公式
     * <p>
     * 合计度数  D=X+Y;  按照上述计算 就是 D=150元
     * 平价电费  E=D*A;  按照上述计算 就是 E=79.245元
     * 峰时电费  F=X*B;  按照上述计算 就是 F=27.915元
     * 谷时电费  G=Y*C;  按照上述计算 就是 G=35.83 元
     * 峰谷合计电费  H=F+G;  按照上述计算 就是 H=63.745元
     *
     * @return 返回输出的值
     */

    BigDecimal X = new BigDecimal(50);
    BigDecimal Y = new BigDecimal(150);
    BigDecimal A = new BigDecimal(0.5283);
    BigDecimal B = new BigDecimal(0.5583);
    BigDecimal C = new BigDecimal(0.3583);

    @Override
    protected int getContentId() {
        return R.layout.activity_peak_vally_price;
    }

    @Override
    protected void initView() {
        tvTitle.setText("峰谷电价实惠体验");
        setSelection();
    }

    @Override
    protected void initDate() {
        btnCalculation.performClick();
    }

    @Override
    protected void initListeter() {

    }

    @OnClick({R.id.btnCalculation, R.id.btnReset, R.id.ivBack})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btnCalculation:
                if (validataDegress()) {
                    BigDecimal totalDegress = getEditViewNum(etPeak).add(getEditViewNum(etValley));
                    tvTotalDegress.setText(totalDegress.stripTrailingZeros().toPlainString());
                    BigDecimal averageDegress = totalDegress.multiply(A);
                    tvAverageDegress.setText(averageDegress.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
                    tvPeakPrice.setText(getEditViewNum(etPeak).multiply(B).setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
                    tvValleyPrice.setText(getEditViewNum(etValley).multiply(C).setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
                    tvPeakAndValleyPrice.setText(getEditViewNum(etPeak).multiply(B).add(getEditViewNum(etValley).multiply(C)).setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
                }
                EditViewUtils.hide(this, llContent);
                break;
            case R.id.btnReset:
                etPeak.setText("50");
                etValley.setText("100");

                tvTotalDegress.setText("");
                tvAverageDegress.setText("");
                tvPeakPrice.setText("");
                tvValleyPrice.setText("");
                tvPeakAndValleyPrice.setText("");
                setSelection();
                EditViewUtils.hide(this, llContent);
                if (validataDegress()) {
                    BigDecimal totalDegress = getEditViewNum(etPeak).add(getEditViewNum(etValley));
                    tvTotalDegress.setText(totalDegress.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
                    BigDecimal averageDegress = totalDegress.multiply(A);
                    tvAverageDegress.setText(averageDegress.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
                    tvPeakPrice.setText(getEditViewNum(etPeak).multiply(B).setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
                    tvValleyPrice.setText(getEditViewNum(etValley).multiply(C).setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
                    tvPeakAndValleyPrice.setText(getEditViewNum(etPeak).multiply(B).add(getEditViewNum(etValley).multiply(C)).setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
                }
                break;
            case R.id.ivBack:
                finish();
                break;
        }
    }

    /**
     * 获取输入框的数值
     *
     * @param editText
     * @return
     */
    private BigDecimal getEditViewNum(EditText editText) {
        if (TextUtils.isEmpty(editText.getText().toString())) {
            return new BigDecimal("0");
        }

        return new BigDecimal(editText.getText().toString());
    }

    /**
     * 验证是否输入值
     *
     * @return
     */
    private boolean validataDegress() {
        if (TextUtils.isEmpty(etPeak.getText().toString())) {
            Toast.makeText(this, "请输入峰时度数", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(etValley.getText().toString())) {
            Toast.makeText(this, "请输入谷时度数", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected int statusColor() {
        return 0;
    }


    /**
     * 设置光标显示做左边
     */
    private void setSelection() {
        etPeak.setSelection(etPeak.getText().length());
        etValley.setSelection(etValley.getText().length());
    }
}
