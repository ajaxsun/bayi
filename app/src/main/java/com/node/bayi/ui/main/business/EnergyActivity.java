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

/**
 * 家电能耗分析页面
 */
public class EnergyActivity extends BaseActivity {
    public static void startEnergyActivity(Activity activity) {
        activity.startActivity(new Intent(activity, EnergyActivity.class));
    }

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.etTVNum)
    EditText etTVNum;//电视数量
    @BindView(R.id.llContent)
    LinearLayout llContent;//容器
    @BindView(R.id.etTVHour)
    EditText etTVHour;//使用时长
    @BindView(R.id.etACNum)
    EditText etACNum;//空调数量
    @BindView(R.id.etACHour)
    EditText etACHour;//空调使用时长
    @BindView(R.id.etRGNum)
    EditText etRGNum;//冰箱数量
    @BindView(R.id.etRGHour)
    EditText etRGHour;//冰箱使用时长
    @BindView(R.id.etWMNum)
    EditText etWMNum;//洗衣机数量
    @BindView(R.id.etWMHour)
    EditText etWMHour;//洗衣机使用时长
    @BindView(R.id.etEFNum)
    EditText etEFNum;//电磁炉数量
    @BindView(R.id.etEFHour)
    EditText etEFHour;//电磁炉使用时长
    @BindView(R.id.etMONum)
    EditText etMONum;//微波炉数量
    @BindView(R.id.etMOHour)
    EditText etMOHour;//微波炉使用时长
    @BindView(R.id.etRCNum)
    EditText etRCNum;//电饭锅的数量
    @BindView(R.id.etRCHour)
    EditText etRCHour;//电饭锅使用时长
    @BindView(R.id.etHeaterNum)
    EditText etHeaterNum;//热水器的数量
    @BindView(R.id.etHeaterHour)
    EditText etHeaterHour;//热水器的使用时长
    @BindView(R.id.etLanternNum)
    EditText etLanternNum;//节能灯具的数量
    @BindView(R.id.etLanternHour)
    EditText etLanternHour;//节能灯具的使用时长
    @BindView(R.id.btnCalculation)
    Button btnCalculation;//计算
    @BindView(R.id.btnReset)
    Button btnReset;//重置

    @BindView(R.id.tvTVEQ)
    TextView tvTVEQ;//电视使用的电量
    @BindView(R.id.tvTVPrice)
    TextView tvTVPrice;//电费使用金额
    @BindView(R.id.tvACQE)
    TextView tvACQE;//空调使用的电量
    @BindView(R.id.tvACPrice)
    TextView tvACPrice;//空调使用的金额
    @BindView(R.id.tvRGQE)
    TextView tvRGQE;//冰箱使用的电量
    @BindView(R.id.tvRGPrice)
    TextView tvRGPrice;//冰箱使用的金额
    @BindView(R.id.tvWMQE)
    TextView tvWMQE;//洗衣机使用的电量
    @BindView(R.id.tvWMPrice)
    TextView tvWMPrice;//洗衣机使用的电量
    @BindView(R.id.tvEFQE)
    TextView tvEFQE;//电磁炉的使用电量
    @BindView(R.id.tvEFPrice)
    TextView tvEFPrice;//电磁炉的使用金额
    @BindView(R.id.tvMOQE)
    TextView tvMOQE;//微波炉的使用电量
    @BindView(R.id.tvMOPrice)
    TextView tvMOPrice;//微波炉的使用金额
    @BindView(R.id.tvRCQE)
    TextView tvRCQE;//电饭锅的使用电量
    @BindView(R.id.tvRCPrice)
    TextView tvRCPrice;//电饭锅的使用金额
    @BindView(R.id.tvHeaterQE)
    TextView tvHeaterQE;//热水器的使用电量
    @BindView(R.id.tvHeaterPrice)
    TextView tvHeaterPrice;//热水器的使用金额
    @BindView(R.id.tvLanternQE)
    TextView tvLanternQE;//节能灯具
    @BindView(R.id.tvLanternPrice)
    TextView tvLanternPrice;//合计电量
    @BindView(R.id.tvTotalQE)
    TextView tvTotalQE;//合计电量
    @BindView(R.id.tvTotalPrice)
    TextView tvTotalPrice;//合计费用

    BigDecimal bgTVW = new BigDecimal("0.1");//电视的功率
    BigDecimal bgACW = new BigDecimal("1.5");//空调的功率
    BigDecimal bgRGW = new BigDecimal("0.085");//冰箱的功率
    BigDecimal bgWMW = new BigDecimal("1");//洗衣机的功率
    BigDecimal bgEFW = new BigDecimal("2");//电磁炉的功率
    BigDecimal bgMOW = new BigDecimal("1.5");//微波炉的功率
    BigDecimal bgRCW = new BigDecimal("1.5");//电饭锅的功率
    BigDecimal bgHeaterW = new BigDecimal("3.5");//热水器的功率
    BigDecimal bgLanternW = new BigDecimal("0.02");//节能灯具的功率


    //电价
    BigDecimal bgET = new BigDecimal("0.52");


    //合计电量
    BigDecimal bgTotalQE = null;
    //合计费用
    BigDecimal bgTotalPrice = null;

    @Override
    protected int getContentId() {
        return R.layout.activity_energy;
    }

    @Override
    protected void initView() {
        tvTitle.setText("家电能耗分析体验");
        setSelection();

    }

    @Override
    protected void initDate() {
        btnCalculation.performClick();

    }


    @OnClick({R.id.ivBack, R.id.btnCalculation, R.id.btnReset})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.btnCalculation:
                setValidate();
                EditViewUtils.hide(this, llContent);

                break;
            case R.id.btnReset:
                clearData();
                setSelection();
                EditViewUtils.hide(this, llContent);
                setValidate();

                break;
        }
    }


    //输入值的校验
    private void setValidate() {
        bgTotalQE = new BigDecimal("0");
        bgTotalPrice = new BigDecimal("0");
        if (!TextUtils.isEmpty(etTVNum.getText().toString())) {
            if (TextUtils.isEmpty(etTVHour.getText().toString())) {
                Toast.makeText(this, "请输入电视的使用时长", Toast.LENGTH_SHORT).show();
                return;
            } else {
                calculation(etTVNum, etTVNum, bgTVW, tvTVEQ, tvTVPrice);
            }
        }
        if (!TextUtils.isEmpty(etACNum.getText().toString())) {
            if (TextUtils.isEmpty(etACHour.getText().toString())) {
                Toast.makeText(this, "请输入空调的使用时长", Toast.LENGTH_SHORT).show();
                return;
            } else {
                calculation(etACNum, etACHour, bgACW, tvACQE, tvACPrice);
            }
        }
        if (!TextUtils.isEmpty(etRGNum.getText().toString())) {
            if (TextUtils.isEmpty(etRGHour.getText().toString())) {
                Toast.makeText(this, "请输入冰箱的使用时长", Toast.LENGTH_SHORT).show();
                return;
            } else {
                calculation(etRGNum, etRGHour, bgRGW, tvRGQE, tvRGPrice);
            }
        }
        if (!TextUtils.isEmpty(etWMNum.getText().toString())) {
            if (TextUtils.isEmpty(etWMHour.getText().toString())) {
                Toast.makeText(this, "请输入洗衣机的使用时长", Toast.LENGTH_SHORT).show();
                return;
            } else {
                calculation(etWMNum, etWMHour, bgWMW, tvWMQE, tvWMPrice);
            }
        }
        if (!TextUtils.isEmpty(etWMNum.getText().toString())) {
            if (TextUtils.isEmpty(etEFHour.getText().toString())) {
                Toast.makeText(this, "请输入电磁炉的使用时长", Toast.LENGTH_SHORT).show();
                return;
            } else {
                calculation(etEFNum, etEFHour, bgEFW, tvEFQE, tvEFPrice);
            }
        }
        if (!TextUtils.isEmpty(etMONum.getText().toString())) {
            if (TextUtils.isEmpty(etMOHour.getText().toString())) {
                Toast.makeText(this, "请输入微波炉的使用时长", Toast.LENGTH_SHORT).show();
                return;
            } else {
                calculation(etMONum, etMOHour, bgMOW, tvMOQE, tvMOPrice);
            }
        }
        if (!TextUtils.isEmpty(etRCNum.getText().toString())) {
            if (TextUtils.isEmpty(etRCHour.getText().toString())) {
                Toast.makeText(this, "请输入电饭锅的使用时长", Toast.LENGTH_SHORT).show();
                return;
            } else {
                calculation(etRCNum, etRCHour, bgRCW, tvRCQE, tvRCPrice);
            }
        }
        if (!TextUtils.isEmpty(etHeaterNum.getText().toString())) {
            if (TextUtils.isEmpty(etHeaterHour.getText().toString())) {
                Toast.makeText(this, "请输入热水器的使用时长", Toast.LENGTH_SHORT).show();
                return;
            } else {
                calculation(etHeaterNum, etHeaterHour, bgHeaterW, tvHeaterQE, tvHeaterPrice);
            }
        }
        if (!TextUtils.isEmpty(etLanternNum.getText().toString())) {
            if (TextUtils.isEmpty(etLanternHour.getText().toString())) {
                Toast.makeText(this, "请输入热水器的使用时长", Toast.LENGTH_SHORT).show();
                return;
            } else {
                calculation(etLanternNum, etLanternHour, bgLanternW, tvLanternQE, tvLanternPrice);
            }
        }

        //计算合计电费及电量
        tvTotalQE.setText(bgTotalQE.stripTrailingZeros().toPlainString());
        tvTotalPrice.setText(bgTotalPrice.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
    }

    @Override
    protected int statusColor() {
        return 0;
    }


    //点击重置按钮时 清除数据
    private void clearData() {
        bgTotalQE = new BigDecimal("0");
        bgTotalPrice = new BigDecimal("0");
        etTVNum.setText("2");
        etTVHour.setText("2");
        etACNum.setText("2");
        etACHour.setText("8");
        etRGNum.setText("1");
        etRGHour.setText("24");
        etWMNum.setText("1");
        etWMHour.setText("1");
        etEFNum.setText("1");
        etEFHour.setText("0.5");
        etMONum.setText("1");
        etMOHour.setText("0.5");
        etRCNum.setText("1");
        etRCHour.setText("1");
        etHeaterNum.setText("2");
        etHeaterHour.setText("2");
        etLanternNum.setText("10");
        etLanternHour.setText("6");


        tvTVEQ.setText("");
        tvTVPrice.setText("");
        tvACQE.setText("");
        tvACPrice.setText("");
        tvRGQE.setText("");
        tvRGPrice.setText("");
        tvWMQE.setText("");
        tvWMPrice.setText("");
        tvEFQE.setText("");
        tvEFPrice.setText("");
        tvMOQE.setText("");
        tvMOPrice.setText("");
        tvRCQE.setText("");
        tvRCPrice.setText("");
        tvHeaterQE.setText("");
        tvHeaterPrice.setText("");
        tvLanternQE.setText("");
        tvLanternPrice.setText("");
        tvTotalQE.setText("");
        tvTotalPrice.setText("");
        btnCalculation.performClick();

    }

    /**
     * 计算出电量
     *
     * @param editText
     * @param editText2
     * @param big
     * @param textView  攻率的控件
     * @param textView2 价格的控件
     */
    private void calculation(EditText editText, EditText editText2, BigDecimal big, TextView textView, TextView textView2) {
        BigDecimal bigDecimal = new BigDecimal(editText.getText().toString());
        BigDecimal bigDecimal2 = new BigDecimal(editText2.getText().toString());
        BigDecimal result = bigDecimal.multiply(bigDecimal2).multiply(big);
        bgTotalQE = bgTotalQE.add(result);
        textView.setText(result.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
        BigDecimal bgPrice = result.multiply(bgET);
        textView2.setText(bgPrice.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString());
        bgTotalPrice = bgTotalPrice.add(bgPrice);
    }

    @Override
    protected void initListeter() {
        etTVHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditViewUtils.editView(s, etTVHour);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etACHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditViewUtils.editView(s, etACHour);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etRGHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditViewUtils.editView(s, etRGHour);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etWMHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditViewUtils.editView(s, etWMHour);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etEFHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditViewUtils.editView(s, etEFHour);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etMOHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditViewUtils.editView(s, etMOHour);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etRCHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditViewUtils.editView(s, etRCHour);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etHeaterHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditViewUtils.editView(s, etHeaterHour);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etLanternHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditViewUtils.editView(s, etLanternHour);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 设置光标显示做左边
     */
    private void setSelection() {
        etTVNum.setSelection(etTVNum.getText().length());
        etTVHour.setSelection(etTVHour.getText().length());
        etACNum.setSelection(etACNum.getText().length());
        etACHour.setSelection(etACHour.getText().length());
        etRGNum.setSelection(etRGNum.getText().length());
        etRGHour.setSelection(etRGHour.getText().length());
        etWMNum.setSelection(etWMNum.getText().length());
        etWMHour.setSelection(etWMHour.getText().length());
        etEFNum.setSelection(etEFNum.getText().length());
        etEFHour.setSelection(etEFHour.getText().length());
        etMONum.setSelection(etMONum.getText().length());
        etMOHour.setSelection(etMOHour.getText().length());
        etRCNum.setSelection(etRCNum.getText().length());
        etRCHour.setSelection(etRCHour.getText().length());
        etHeaterNum.setSelection(etHeaterNum.getText().length());
        etHeaterHour.setSelection(etHeaterHour.getText().length());
        etLanternNum.setSelection(etLanternNum.getText().length());
        etLanternHour.setSelection(etLanternHour.getText().length());
    }
}
