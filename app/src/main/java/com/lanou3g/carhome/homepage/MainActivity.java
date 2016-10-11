package com.lanou3g.carhome.homepage;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;
import com.lanou3g.carhome.discover.DiscoverFragment;
import com.lanou3g.carhome.findcar.FindCarFragment;
import com.lanou3g.carhome.forum.ForumFragment;
import com.lanou3g.carhome.personal.PersonalFragment;
import com.lanou3g.carhome.recommend.RecommendFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton rBtnRecommend;
    private RadioButton rBtnForum;
    private RadioButton rBtnFindCar;
    private RadioButton rBtnDiscover;
    private RadioButton rBtnPersonal;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        rBtnRecommend = bindView(R.id.rbtn_recommended);
        rBtnForum = bindView(R.id.rbtn_forum);
        rBtnFindCar = bindView(R.id.rbtn_findcar);
        rBtnDiscover = bindView(R.id.rbtn_discover);
        rBtnPersonal = bindView(R.id.rbtn_personal);
    }

    @Override
    protected void initData() {

        rBtnRecommend.setOnClickListener(this);
        rBtnForum.setOnClickListener(this);
        rBtnFindCar.setOnClickListener(this);
        rBtnDiscover.setOnClickListener(this);
        rBtnPersonal.setOnClickListener(this);

        rBtnRecommend.setChecked(true);

        // 默认显示的碎片
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.replace_view, new RecommendFragment());
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (view.getId()){
            case R.id.rbtn_recommended:
                transaction.replace(R.id.replace_view, new RecommendFragment());
                break;
            case R.id.rbtn_forum:
                transaction.replace(R.id.replace_view, new ForumFragment());
                break;
            case R.id.rbtn_findcar:
                transaction.replace(R.id.replace_view, new FindCarFragment());
                break;
            case R.id.rbtn_discover:
                transaction.replace(R.id.replace_view, new DiscoverFragment());
                break;
            case R.id.rbtn_personal:
                transaction.replace(R.id.replace_view, new PersonalFragment());
                break;
        }
        transaction.commit();
    }
}
