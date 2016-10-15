package com.lanou3g.carhome.personal.setup;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;
import com.lanou3g.carhome.personal.setup.clearcache.DataCleanManager;
import com.lanou3g.carhome.personal.setup.push.PushActivity;

/**
 *
 */
public class SetUpActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llSetUpPush, llClearCache;
    private TextView tvTitle, tvClearCache;
    private ImageButton iBtnBack;

    @Override
    protected int setLayout() {
        return R.layout.activity_set_up;
    }

    @Override
    protected void initView() {
        llSetUpPush = bindView(R.id.ll_set_up_push);
        tvTitle = bindView(R.id.tv_title_custom_title_selection);
        iBtnBack = bindView(R.id.ibtn_back_custom_title_selection);
        llClearCache = bindView(R.id.ll_set_up_clear_cache);
        tvClearCache = bindView(R.id.tv_set_up_clear_cache);
    }

    @Override
    protected void initData() {
        llSetUpPush.setOnClickListener(this);
        iBtnBack.setOnClickListener(this);
        llClearCache.setOnClickListener(this);

        tvTitle.setText("设置");

        try {
            // 获取缓存大小
            String file = DataCleanManager.getTotalCacheSize(this);
            tvClearCache.setText(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            case R.id.ll_set_up_clear_cache:

                final AlertDialog dialog = new AlertDialog.Builder(this).create();

                View viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog_remove, null);
                Button btnCancel = (Button) viewDialog.findViewById(R.id.btn_cancel_dialog);
                Button btnDetermine = (Button) viewDialog.findViewById(R.id.btn_determine_dialog);
                TextView tvClearTitle = (TextView) viewDialog.findViewById(R.id.tv_clear_search_history);

                tvClearTitle.setText("您确定要清除缓存?");

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                btnDetermine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 清除
                        DataCleanManager.clearAllCache(SetUpActivity.this);
                        try {
                            // 获取大小
                            String file = DataCleanManager.getTotalCacheSize(SetUpActivity.this);
                            tvClearCache.setText(file);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        dialog.cancel();
                    }
                });

                dialog.setView(viewDialog);
                dialog.show();
                break;
        }
    }
}
