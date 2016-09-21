package com.lanou3g.carhome.forum.select;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;
import com.lanou3g.carhome.networkrequest.GsonRequest;
import com.lanou3g.carhome.networkrequest.URLValues;
import com.lanou3g.carhome.networkrequest.VolleySingleton;

import java.util.ArrayList;


/**
 *
 */
public class SelectFragment extends BaseFragment implements View.OnClickListener {

    private PullToRefreshListView plvSelection;
    private SelectionAdapter adapter;
    private Button btnWife;
    private Button btnBeauty;
    private Button btnHigh;
    private Button btnMo;
    private Button btnChoseCare;
    private Button btnGirlChose;

    @Override
    protected int setLayout() {
        return R.layout.fragment_select;
    }

    @Override
    protected void initView() {
        plvSelection = bindView(R.id.pLv_selection);

        View selectView = LayoutInflater.from(context).inflate(R.layout.headview_selection, null);
        btnWife = bindView(R.id.btn_headview_selection_wife, selectView);
        btnBeauty = bindView(R.id.btn_headview_selection_beauty, selectView);
        btnHigh = bindView(R.id.btn_headview_selection_high, selectView);
        btnMo = bindView(R.id.btn_headview_selection_mo, selectView);
        btnChoseCare = bindView(R.id.btn_headview_selection_chosecare, selectView);
        btnGirlChose = bindView(R.id.btn_headview_selection_girlchose, selectView);

        ListView listView = plvSelection.getRefreshableView();
        listView.addHeaderView(selectView);
    }

    @Override
    protected void initData() {

        btnWife.setOnClickListener(this);
        btnBeauty.setOnClickListener(this);
        btnHigh.setOnClickListener(this);
        btnMo.setOnClickListener(this);
        btnChoseCare.setOnClickListener(this);
        btnGirlChose.setOnClickListener(this);

        plvSelection.setMode(PullToRefreshBase.Mode.BOTH);
        plvSelection.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                setBtnBackGround();
                selectionSendInterent();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        adapter = new SelectionAdapter(context);
        plvSelection.setAdapter(adapter);
        selectionSendInterent();

        setBtnBackGround();
    }

    private void setBtnBackGround() {
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(btnWife);
        buttons.add(btnBeauty);
        buttons.add(btnHigh);
        buttons.add(btnMo);
        buttons.add(btnChoseCare);
        buttons.add(btnGirlChose);
        ArrayList<Integer> backGrounds = new ArrayList<>();
        backGrounds.add(R.drawable.blue_btn_bg);
        backGrounds.add(R.drawable.orange_btn_bg);
        ArrayList<Integer> intColor = new ArrayList<>();
        intColor.add(getResources().getColor(R.color.blue));
        intColor.add(getResources().getColor(R.color.orange));
        for (int i = 0; i < buttons.size(); i++) {
            int color = (int) (Math.random() * 2);
            buttons.get(i).setBackgroundResource(backGrounds.get(color));
            buttons.get(i).setTextColor(intColor.get(color));
        }
    }

    private void selectionSendInterent() {
        GsonRequest<SelectionBean> gsonRequest = new GsonRequest<SelectionBean>(URLValues.SELECTION_URL,
                SelectionBean.class,
                new Response.Listener<SelectionBean>() {
                    @Override
                    public void onResponse(SelectionBean response) {
                        adapter.setBean(response);
                        plvSelection.onRefreshComplete();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_headview_selection_wife:
                break;
            case R.id.btn_headview_selection_beauty:
                break;
            case R.id.btn_headview_selection_high:
                break;
            case R.id.btn_headview_selection_mo:
                break;
            case R.id.btn_headview_selection_chosecare:
                break;
            case R.id.btn_headview_selection_girlchose:
                break;
        }
    }
}