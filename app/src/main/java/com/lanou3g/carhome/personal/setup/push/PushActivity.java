package com.lanou3g.carhome.personal.setup.push;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;

/**
 *
 */
public class PushActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton iBtnBack;
    private TextView tvTitle;
    private Switch sBtnSysMsg;

    @Override
    protected int setLayout() {
        return R.layout.activity_push;
    }

    @Override
    protected void initView() {
        tvTitle = bindView(R.id.tv_title_custom_title_selection);
        iBtnBack = bindView(R.id.ibtn_back_custom_title_selection);
        sBtnSysMsg = bindView(R.id.switch_btn_sys_msg);
    }

    @Override
    protected void initData() {
        iBtnBack.setOnClickListener(this);

        tvTitle.setText("推送设置");

        initSwitchBtn();
    }

    private void initSwitchBtn() {

        SharedPreferences sp = getSharedPreferences("launch", MODE_PRIVATE);
        final SharedPreferences.Editor spEt = sp.edit();

        Boolean isSysMsg = sp.getBoolean("isSysMsg", false);

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
                } else {
                    spEt.putBoolean("isSysMsg", false);
                    spEt.commit();
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
        }
    }
}
