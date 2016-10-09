package com.lanou3g.carhome.forum.select;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;
import com.lanou3g.carhome.forum.selectionall.SelectionAllActivity;
import com.lanou3g.carhome.forum.selectionrecommend.SelectionActivity;
import com.lanou3g.carhome.forum.tyfourhours.TyFourHoursActivity;
import com.lanou3g.carhome.networkrequest.GsonRequest;
import com.lanou3g.carhome.networkrequest.URLValues;
import com.lanou3g.carhome.networkrequest.VolleySingleton;
import com.lanou3g.carhome.networkrequest.WebViewActivity;

import java.util.ArrayList;


/**
 *
 */
public class SelectFragment extends BaseFragment implements View.OnClickListener {

    private PullToRefreshListView plvSelection;
    private SelectAdapter adapter;
    private Button btnWife;
    private Button btnBeauty;
    private Button btnHigh;
    private Button btnMo;
    private Button btnChoseCare;
    private Button btnGirlChose;
    private LinearLayout llSr;
    private Button btnTfHour;
    private Button btnRedPeople;
    private Button btnConversion;

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
        btnRedPeople = bindView(R.id.btn_headview_selection_redpeople, selectView);
        btnConversion = bindView(R.id.btn_headview_selection_conversion, selectView);
        llSr = bindView(R.id.ll_headview_selection_sr, selectView);
        btnTfHour = bindView(R.id.btn_headview_selection_tfhour, selectView);

        ListView listView = plvSelection.getRefreshableView();
        listView.addHeaderView(selectView);
    }

    @Override
    protected void initData() {

        onClickThis();

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
        adapter = new SelectAdapter(context);
        plvSelection.setAdapter(adapter);
        selectionSendInterent();

        setBtnBackGround();

        plvSelection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SelectBean bean = (SelectBean) parent.getItemAtPosition(position);
                int urlId = bean.getResult().getList().get(position - 2).getTopicid();
                String url = "http://forum.app.autohome.com.cn/autov5.0.0/forum/club/topiccontent-a2-pm2-v5.0.0-t"
                        + urlId + "-o0-p1-s20-c1-nt0-fs0-sp0-al0-cw320.json";
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("urlWv", url);
                getActivity().startActivity(intent);
            }
        });
    }

    private void onClickThis() {
        btnWife.setOnClickListener(this);
        btnBeauty.setOnClickListener(this);
        btnHigh.setOnClickListener(this);
        btnMo.setOnClickListener(this);
        btnChoseCare.setOnClickListener(this);
        btnGirlChose.setOnClickListener(this);
        btnConversion.setOnClickListener(this);
        btnRedPeople.setOnClickListener(this);
        llSr.setOnClickListener(this);
        btnTfHour.setOnClickListener(this);
    }

    private void setBtnBackGround() {
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(btnWife);
        buttons.add(btnBeauty);
        buttons.add(btnHigh);
        buttons.add(btnMo);
        buttons.add(btnChoseCare);
        buttons.add(btnGirlChose);
        buttons.add(btnConversion);
        buttons.add(btnRedPeople);
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
        GsonRequest<SelectBean> gsonRequest = new GsonRequest<SelectBean>(URLValues.SELECTION_URL,
                SelectBean.class,
                new Response.Listener<SelectBean>() {
                    @Override
                    public void onResponse(SelectBean response) {
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
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_headview_selection_wife:
                String strWife = (String) btnWife.getText();
                intent.setClass(getActivity(), SelectionActivity.class);
                intent.putExtra("url", URLValues.WIFE_URL);
                intent.putExtra("title", strWife);
                startActivity(intent);
                break;
            case R.id.btn_headview_selection_beauty:
                String strBeauty = (String) btnBeauty.getText();
                intent.setClass(getActivity(), SelectionActivity.class);
                intent.putExtra("url", URLValues.BEAUTY_URL);
                intent.putExtra("title", strBeauty);
                startActivity(intent);
                break;
            case R.id.btn_headview_selection_high:
                String strHigh = (String) btnHigh.getText();
                intent.setClass(getActivity(), SelectionActivity.class);
                intent.putExtra("url", URLValues.HIGH_URL);
                intent.putExtra("title", strHigh);
                startActivity(intent);
                break;
            case R.id.btn_headview_selection_mo:
                String strMo = (String) btnMo.getText();
                intent.setClass(getActivity(), SelectionActivity.class);
                intent.putExtra("url", URLValues.MO_URL);
                intent.putExtra("title", strMo);
                startActivity(intent);
                break;
            case R.id.btn_headview_selection_chosecare:
                String strChoseCare = (String) btnChoseCare.getText();
                intent.setClass(getActivity(), SelectionActivity.class);
                intent.putExtra("url", URLValues.CHOSECARE_URL);
                intent.putExtra("title", strChoseCare);
                startActivity(intent);
                break;
            case R.id.btn_headview_selection_girlchose:
                String strGirlChose = (String) btnGirlChose.getText();
                intent.setClass(getActivity(), SelectionActivity.class);
                intent.putExtra("url", URLValues.GIRL_CHOSE_URL);
                intent.putExtra("title", strGirlChose);
                startActivity(intent);
                break;
            case R.id.btn_headview_selection_conversion:
                String strConversion = (String) btnConversion.getText();
                intent.setClass(getActivity(), SelectionActivity.class);
                intent.putExtra("url", URLValues.CONVERSION_URL);
                intent.putExtra("title", strConversion);
                startActivity(intent);
                break;
            case R.id.btn_headview_selection_redpeople:
                String strRedPeople = (String) btnRedPeople.getText();
                intent.setClass(getActivity(), SelectionActivity.class);
                intent.putExtra("url", URLValues.RED_PEOPLE_URL);
                intent.putExtra("title", strRedPeople);
                startActivity(intent);
                break;
            case R.id.ll_headview_selection_sr:
                intent.setClass(getActivity(), SelectionAllActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_headview_selection_tfhour:
                intent.setClass(getActivity(), TyFourHoursActivity.class);
                startActivity(intent);
                break;
        }
    }
}
