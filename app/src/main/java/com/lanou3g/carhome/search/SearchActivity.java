package com.lanou3g.carhome.search;

import android.view.View;
import android.widget.Button;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;

/**
 *
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        Button btnCancel = bindView(R.id.btn_search_cancel);

        btnCancel.setOnClickListener(this);
    }

    @Override
    protected void initData() {

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
