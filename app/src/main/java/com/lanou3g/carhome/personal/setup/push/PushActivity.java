package com.lanou3g.carhome.personal.setup.push;

import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;

import cn.jpush.android.api.JPushInterface;

/**
 *
 */
public class PushActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton iBtnBack;
    private TextView tvTitle, tvSetDate;
    private Switch sBtnSysMsg;
    private LinearLayout llPushDate;

    @Override
    protected int setLayout() {
        return R.layout.activity_push;
    }

    @Override
    protected void initView() {
        tvTitle = bindView(R.id.tv_title_custom_title_selection);
        iBtnBack = bindView(R.id.ibtn_back_custom_title_selection);
        sBtnSysMsg = bindView(R.id.switch_btn_sys_msg);
        llPushDate = bindView(R.id.ll_set_up_push_date);
        tvSetDate = bindView(R.id.tv_set_push_date);
    }

    @Override
    protected void initData() {
        iBtnBack.setOnClickListener(this);
        llPushDate.setOnClickListener(this);

        tvTitle.setText("推送设置");

        initSwitchBtn();
    }

    private void initSwitchBtn() {

        SharedPreferences sp = getSharedPreferences("launch", MODE_PRIVATE);
        final SharedPreferences.Editor spEt = sp.edit();

        Boolean isSysMsg = sp.getBoolean("isSysMsg", true);

        if (isSysMsg == true) {
            sBtnSysMsg.setChecked(true);
        } else {
            sBtnSysMsg.setChecked(false);
        }

        sBtnSysMsg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    spEt.putBoolean("isSysMsg", true);
                    spEt.commit();
                    JPushInterface.resumePush(getApplicationContext());
                } else {
                    spEt.putBoolean("isSysMsg", false);
                    spEt.commit();
                    JPushInterface.stopPush(getApplicationContext());
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtn_back_custom_title_selection:
                finish();
                break;
            case R.id.ll_set_up_push_date:
                showPopFormBottom(llPushDate);
                break;
        }
    }

    public void showPopFormBottom(View view) {
        PushDatePopWin pushDatePopWin = new PushDatePopWin(this);
        pushDatePopWin.showAtLocation(findViewById(R.id.ll_set_up_push_date), Gravity.BOTTOM, 0, 0);
    }

}
