package com.lanou3g.carhome.welcome;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;
import com.lanou3g.carhome.homepage.MainActivity;
import com.lanou3g.carhome.networkrequest.DBTools;
import com.lanou3g.carhome.networkrequest.GsonRequest;
import com.lanou3g.carhome.networkrequest.URLValues;
import com.lanou3g.carhome.networkrequest.VolleySingleton;

/**
 *
 */
public class WelcomeActivity extends BaseActivity{

    private Button mBtnSkip;
    private ImageView mImage;
    private CountDownTimer mTimer;
    private DBTools dbTools;

    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        mBtnSkip = bindView(R.id.btn_welcome_skip);
        mImage = bindView(R.id.iv_welcome);
    }

    @Override
    protected void initData() {

        dbTools = DBTools.getInstance();

        mBtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimer.cancel();
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        sendInterent();
    }

    private void sendInterent() {
        GsonRequest<WelcomeBean> gsonRequest = new GsonRequest<WelcomeBean>(URLValues.WELCOME_URL,
                WelcomeBean.class,
                new Response.Listener<WelcomeBean>() {
                    @Override
                    public void onResponse(WelcomeBean response) {

//                        if (response.getResult().getAd().getImgad().getImgurl().equals("")) {
                         mImage.setBackgroundColor(Color.WHITE);
//                        } else {
//                            ImageLoader.getInstance().displayImage(response.getResult().getAd().getImgad().getImgurl(), mImage);
//                        }
                        initCountDown(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WelcomeActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }

    // 这个方法就是 倒计时反复执行的方法
    private void initCountDown(WelcomeBean response) {
        // 倒计时结束后执行的方法, 在这里执行跳转
        // response.getResult().getAd().getShowtime()
        mTimer = new CountDownTimer(3 * 1000, 1000) {
            @Override
            public void onTick(long l) {

            }
            // 倒计时结束后执行的方法, 在这里执行跳转
            @Override
            public void onFinish() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
