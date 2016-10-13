package com.lanou3g.carhome.personal;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;
import com.lanou3g.carhome.personal.setup.SetUpActivity;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 *
 */
public class PersonalFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvSetUp, tvLoginSina, tvLoginQQ;

    @Override
    protected int setLayout() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initView() {
        ShareSDK.initSDK(context);
        tvSetUp = bindView(R.id.tv_set_up_personal);
        tvLoginSina = bindView(R.id.tv_login_sina);
        tvLoginQQ = bindView(R.id.tv_login_qq);
    }


    @Override
    protected void initData() {
        tvSetUp.setOnClickListener(this);
        tvLoginSina.setOnClickListener(this);
        tvLoginQQ.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        PlatformActionListener paListener = null;
        switch (v.getId()){
            case R.id.tv_set_up_personal:
                Intent intent = new Intent(getActivity(), SetUpActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login_sina:
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                weibo.setPlatformActionListener(paListener);
                //authorize与showUser单独调用一个即可
                weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
                weibo.showUser(null);//授权并获取用户信息
                break;
            case R.id.tv_login_qq:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(paListener);
                //authorize与showUser单独调用一个即可
                qq.authorize();//单独授权,OnComplete返回的hashmap是空的
                qq.showUser(null);//授权并获取用户信息
                break;
        }
    }
}
