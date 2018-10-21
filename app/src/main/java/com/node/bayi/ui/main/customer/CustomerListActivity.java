package com.node.bayi.ui.main.customer;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.node.bayi.R;
import com.node.bayi.base.BaseActivity;
import com.node.bayi.ui.adapter.AreaAdapter;

import butterknife.BindView;

public class CustomerListActivity extends BaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    AreaAdapter areaAdapter;

    @Override
    protected int getContentId() {
        return R.layout.activity_customer_list;
    }

    @Override
    protected void initView() {
        tvTitle.setText("客户经理信息维护");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        areaAdapter = new AreaAdapter(this);
        mRecyclerView.setAdapter(areaAdapter);
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initListeter() {

    }

    @Override
    protected int statusColor() {
        return 0;
    }

}
