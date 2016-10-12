package com.lanou3g.carhome.personal.setup;

import android.view.View;
import android.widget.LinearLayout;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;

/**
 *
 */
public class SetUpActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llSetUp;

    @Override
    protected int setLayout() {
        return R.layout.activity_set_up;
    }

    @Override
    protected void initView() {
        llSetUp = bindView(R.id.ll_set_up_push);
    }

    @Override
    protected void initData() {
        llSetUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_set_up_push:
                break;
        }
    }
}
