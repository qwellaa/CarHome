package com.lanou3g.carhome.welcome;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;
import com.lanou3g.carhome.homepage.MainActivity;

/**
 *
 */
public class WelcomeActivity extends BaseActivity{

    private Button btnSkip;

    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        btnSkip = bindView(R.id.btn_welcome_skip);
    }

    @Override
    protected void initData() {
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
