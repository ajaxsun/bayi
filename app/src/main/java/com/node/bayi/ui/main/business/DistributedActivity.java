package com.node.bayi.ui.main.business;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.OnClick;
import com.node.bayi.R;
import com.node.bayi.base.BaseActivity;
import com.node.bayi.utils.EditViewUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 分布式光伏技术项目体验页面
 */
public class DistributedActivity extends BaseActivity {

    public static void startDistributedActivity(Activity activity) {
        activity.startActivity(new Intent(activity, DistributedActivity.class));
    }

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.btnCalculation)
    Button btnCalculation;
    @BindView(R.id.llContent)
    LinearLayout llContent;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.etArea)
    EditText etArea;

    @BindView(R.id.tvBenjin)
    TextView tvBenjin;//本金
    @BindView(R.id.tvNianfadianliang)
    TextView tvNianfadianliang;//年发电量
    @BindView(R.id.tvLocalButie)
    TextView tvLocalButie;//地方补贴
    @BindView(R.id.tvZiyong)
    TextView tvZiyong;//老百姓自用
    @BindView(R.id.tvDianfei)
    TextView tvDianfei;//省电费
    @BindView(R.id.tvShangwang)
    TextView tvShangwang;//上网部分
    @BindView(R.id.tvButieDianjia)
    TextView tvButieDianjia;//补贴电价
    @BindView(R.id.tvNianshouyi)
    TextView tvNianshouyi;//年收益
    @BindView(R.id.tvHuishou)
    TextView tvHuishou;//多久回收  成本

    BigDecimal bgKW = null;//可安装功率

    BigDecimal bgW = new BigDecimal("5000");//每千瓦造价

    @Override
    protected int getContentId() {
        return R.layout.activity_distributed;
    }

    @Override
    protected void initView() {
        tvTitle.setText("分布式光伏技术项目体验");
        setSelection();
    }

    @Override
    protected void initDate() {
        btnCalculation.performClick();
    }

    @Override
    protected void initListeter() {
        etArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditViewUtils.editView(s, etArea);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.ivBack, R.id.btnCalculation, R.id.btnReset})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.btnCalculation:
                calculationResult();
                EditViewUtils.hide(this, llContent);

                break;
            case R.id.btnReset:
                etArea.setText("50");
                tvBenjin.setText("100");
                tvNianfadianliang.setText("");
                tvLocalButie.setText("");
                tvZiyong.setText("");
                tvDianfei.setText("");
                tvShangwang.setText("");
                tvButieDianjia.setText("");
                tvNianshouyi.setText("");
                tvHuishou.setText("");
                setSelection();
                EditViewUtils.hide(this, llContent);
                calculationResult();
                break;
        }
    }

    @Override
    protected int statusColor() {
        return 0;
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

    private void calculationResult() {
        bgKW = new BigDecimal(etArea.getText().toString()).divide(new BigDecimal("10"));

        BigDecimal bengjin = getEditViewNum(etArea).multiply(bgW).divide(new BigDecimal(10));
        tvBenjin.setText(bengjin.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
        BigDecimal nianfa = new BigDecimal("1300").multiply(bgKW);
        tvNianfadianliang.setText(nianfa.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
        BigDecimal difangbutie = nianfa.multiply(new BigDecimal("0.32"));//地方补贴
        tvLocalButie.setText(difangbutie.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());

        BigDecimal baixingziyong = nianfa.multiply(new BigDecimal("0.15"));//自用
        tvZiyong.setText(baixingziyong.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());

        BigDecimal shengdianfei = baixingziyong.multiply(new BigDecimal("0.5283"));//省电费
        tvDianfei.setText(shengdianfei.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());

        BigDecimal shangwang = nianfa.subtract(baixingziyong);//上网部分
        tvShangwang.setText(shangwang.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());

        BigDecimal butiedianjia = shangwang.multiply(new BigDecimal("0.391"));//补贴电价
        tvButieDianjia.setText(butiedianjia.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());

        BigDecimal nianshouyi = difangbutie.add(shengdianfei).add(butiedianjia);//年收益
        tvNianshouyi.setText(nianshouyi.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());

        BigDecimal huishouchengbeng = bengjin.divide(nianshouyi, 1, RoundingMode.UP);//多久回收成本
        tvHuishou.setText(huishouchengbeng.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
    }

    /**
     * 设置光标显示在做左边
     */
    public void setSelection() {
        etArea.setSelection(etArea.getText().length());

    }

}
