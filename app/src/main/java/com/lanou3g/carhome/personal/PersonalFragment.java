package com.lanou3g.carhome.personal;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;
import com.lanou3g.carhome.personal.setup.SetUpActivity;

/**
 *
 */
public class PersonalFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvSetUp;

    @Override
    protected int setLayout() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initView() {
        tvSetUp = bindView(R.id.tv_set_up_personal);
    }


    @Override
    protected void initData() {
        tvSetUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_set_up_personal:
                Intent intent = new Intent(getActivity(), SetUpActivity.class);
                startActivity(intent);
                break;
        }
    }
}
