package com.lanou3g.carhome.personal.setup.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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
    private PopupWindow popupWindow;
    private NumberPicker timeStart;
    private NumberPicker timeEnd;
    private SetPushDateReceiver setPushDateReceiver;
    private SharedPreferences sp;
    private SharedPreferences.Editor spEt;
    private int startHour;
    private int endHour;

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

        setPushDateReceiver = new SetPushDateReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("setPushTime");
        registerReceiver(setPushDateReceiver, filter);

        iBtnBack.setOnClickListener(this);
        llPushDate.setOnClickListener(this);

        tvTitle.setText("推送设置");

        initSwitchBtn();
    }

    private void initSwitchBtn() {

        sp = getSharedPreferences("launch", MODE_PRIVATE);
        spEt = sp.edit();

        Boolean isSysMsg = sp.getBoolean("isSysMsg", true);

        startHour = sp.getInt("startNum", 8);
        endHour = sp.getInt("endNum", 22);

        tvSetDate.setText("每日 " + startHour + ":00 - " + endHour + ":00" );

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
                initPopWinPushDate();
                break;
        }
    }

    private void initPopWinPushDate() {
        View pushView = LayoutInflater.from(this).inflate(R.layout.pop_win_push_date, null);
        Button btnCancel = (Button) pushView.findViewById(R.id.btn_pop_win_cannel);
        Button btnDetermine = (Button) pushView.findViewById(R.id.btn_pop_win_determine);
        timeStart = (NumberPicker) pushView.findViewById(R.id.time_picker_start);
        timeEnd = (NumberPicker) pushView.findViewById(R.id.time_picker_end);

        String[] dates = {"00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00"
                ,"12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};
        timeStart.setDisplayedValues(dates);
        timeStart.setMinValue(0);
        timeStart.setMaxValue(dates.length - 1);

        timeEnd.setDisplayedValues(dates);
        timeEnd.setMinValue(0);
        timeEnd.setMaxValue(dates.length - 1);

        startHour = sp.getInt("startNum", 8);
        endHour = sp.getInt("endNum", 22);

        timeStart.setValue(startHour);
        timeEnd.setValue(endHour);


        popupWindow = new PopupWindow(pushView, ViewGroup.LayoutParams.MATCH_PARENT, 400,true);
        //设置PopupWindow的弹出和消失效果
        popupWindow.setAnimationStyle(R.style.push_date_anim);
        popupWindow.showAtLocation(llPushDate, Gravity.BOTTOM, 0, 0);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        btnDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initNumPicker();
                popupWindow.dismiss();
            }
        });
    }

    private void initNumPicker() {

        timeStart.setValue(timeStart.getValue());
        timeEnd.setValue(timeEnd.getValue());

        if (timeStart.getValue() >= timeEnd.getValue()) {
            Toast.makeText(this, "有病啊! 你家的'起始时间和结束时间'这么添啊, 智障!", Toast.LENGTH_SHORT).show();
        } else {
            spEt.putInt("startNum", timeStart.getValue());
            spEt.putInt("endNum", timeEnd.getValue());
            spEt.commit();

            Intent intent = new Intent("setPushTime");
            intent.putExtra("startTimeHour", "" + timeStart.getValue());
            intent.putExtra("endTimeHour", "" + timeEnd.getValue());
            sendBroadcast(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(setPushDateReceiver);
    }

    private class SetPushDateReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String startHourStr = intent.getStringExtra("startTimeHour");
            String endHourStr = intent.getStringExtra("endTimeHour");

            tvSetDate.setText("每日 " + startHourStr + ":00 - " + endHourStr + ":00" );
        }
    }
}
