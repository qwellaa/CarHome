package com.lanou3g.carhome.discover.businessenty.allbusinessenty;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;
import com.lanou3g.carhome.networkrequest.GsonRequest;
import com.lanou3g.carhome.networkrequest.URLValues;
import com.lanou3g.carhome.networkrequest.VolleySingleton;

/**
 *
 */
public class AllBuessActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton iBtnBack;
    private RecyclerView rvHot;
    private RecyclerView rvBuy;
    private RecyclerView rvService;
    private RecyclerView rvUse;
    private AllBuessAdapter hotAdapter;
    private AllBuessAdapter buyAdapter;
    private AllBuessAdapter serviceAdapter;
    private AllBuessAdapter useAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_all_buess;
    }

    @Override
    protected void initView() {
        iBtnBack = bindView(R.id.ibtn_back_all_business);
        rvHot = bindView(R.id.rv_hot_channel);
        rvBuy = bindView(R.id.rv_buy_car_channel);
        rvService = bindView(R.id.rv_service_channel);
        rvUse = bindView(R.id.rv_use_tool_channel);
    }

    @Override
    protected void initData() {
        iBtnBack.setOnClickListener(this);

        hotAdapter = new AllBuessAdapter(this, 0);
        buyAdapter = new AllBuessAdapter(this, 1);
        serviceAdapter = new AllBuessAdapter(this, 2);
        useAdapter = new AllBuessAdapter(this, 3);

        rvHot.setAdapter(hotAdapter);
        rvBuy.setAdapter(buyAdapter);
        rvService.setAdapter(serviceAdapter);
        rvUse.setAdapter(useAdapter);

        LinearLayoutManager manager0 = new LinearLayoutManager(this);
        manager0.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvHot.setLayoutManager(manager0);

        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvBuy.setLayoutManager(manager1);

        LinearLayoutManager manager2 = new LinearLayoutManager(this);
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvService.setLayoutManager(manager2);

        LinearLayoutManager manager3 = new LinearLayoutManager(this);
        manager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvUse.setLayoutManager(manager3);

        initSendInterent();
    }

    private void initSendInterent() {
        GsonRequest<AllBuessBean> gsonRequest = new GsonRequest<AllBuessBean>(URLValues.DISCOVER_ALL_URL,
                AllBuessBean.class,
                new Response.Listener<AllBuessBean>() {
                    @Override
                    public void onResponse(AllBuessBean response) {
                        hotAdapter.setBean(response);
                        buyAdapter.setBean(response);
                        serviceAdapter.setBean(response);
                        useAdapter.setBean(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtn_back_all_business:
                finish();
                break;
        }
    }
}
