package com.lanou3g.carhome.personal.setup;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;
import com.lanou3g.carhome.personal.setup.push.PushActivity;

/**
 *
 */
public class SetUpActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llSetUp;
    private TextView tvTitle;
    private ImageButton iBtnBack;

    @Override
    protected int setLayout() {
        return R.layout.activity_set_up;
    }

    @Override
    protected void initView() {
        llSetUp = bindView(R.id.ll_set_up_push);
        tvTitle = bindView(R.id.tv_title_custom_title_selection);
        iBtnBack = bindView(R.id.ibtn_back_custom_title_selection);
    }

    @Override
    protected void initData() {
        llSetUp.setOnClickListener(this);
        iBtnBack.setOnClickListener(this);

        tvTitle.setText("设置");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_set_up_push:
                Intent intent = new Intent(SetUpActivity.this, PushActivity.class);
                startActivity(intent);
                break;
            case R.id.ibtn_back_custom_title_selection:
                finish();
                break;
        }
    }
}
