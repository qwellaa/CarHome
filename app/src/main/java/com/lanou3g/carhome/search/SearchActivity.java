package com.lanou3g.carhome.search;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;

/**
 *
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private EditText et;
    private Button btnCancel;

    @Override
    protected int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        btnCancel = bindView(R.id.btn_search_cancel);
        et = bindView(R.id.et_search);
    }

    @Override
    protected void initData() {
        btnCancel.setOnClickListener(this);

        Intent intent = getIntent();
        String strHint = intent.getStringExtra("hint");
        et.setHint(strHint);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search_cancel:
                finish();
                break;
        }
    }
}
