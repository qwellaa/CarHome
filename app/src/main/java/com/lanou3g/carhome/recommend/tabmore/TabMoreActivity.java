package com.lanou3g.carhome.recommend.tabmore;

import android.view.View;
import android.widget.ImageButton;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;

/**
 *
 */
public class TabMoreActivity extends BaseActivity{

    private ImageButton ibtnBack;

    @Override
    protected int setLayout() {
        return R.layout.activity_recommend_more;
    }

    @Override
    protected void initView() {
        ibtnBack = bindView(R.id.ibtn_back_recommend_more);
    }

    @Override
    protected void initData() {
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
