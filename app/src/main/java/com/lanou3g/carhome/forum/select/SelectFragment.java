package com.lanou3g.carhome.forum.select;

import android.view.LayoutInflater;
import android.view.View;
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

/**
 *
 */
public class SelectFragment extends BaseFragment{

    private PullToRefreshListView plvSelection;
    private SelectionAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_select;
    }

    @Override
    protected void initView() {
        plvSelection = bindView(R.id.pLv_selection);

        View selectView = LayoutInflater.from(context).inflate(R.layout.headview_selection, null);

        ListView listView = plvSelection.getRefreshableView();
        listView.addHeaderView(selectView);
    }

    @Override
    protected void initData() {
        plvSelection.setMode(PullToRefreshBase.Mode.BOTH);
        plvSelection.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                selectionSendInterent();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        adapter = new SelectionAdapter(context);
        plvSelection.setAdapter(adapter);
        selectionSendInterent();
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
}
