package com.lanou3g.carhome.guidepage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;
import com.lanou3g.carhome.welcome.WelcomeActivity;

/**
 *
 */
public class FourthGuidePageFragment extends BaseFragment{

    private Button button;

    @Override
    protected int setLayout() {
        return R.layout.fragment_fourth_guidepage;
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getContext().getSharedPreferences("launch", Context.MODE_PRIVATE);
        SharedPreferences.Editor spET = sp.edit();
        spET.putBoolean("isFirst", true);
        spET.commit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    @Override
    protected void initView() {
        button = bindView(R.id.btn_guide_page_ep, getView());
    }
}
