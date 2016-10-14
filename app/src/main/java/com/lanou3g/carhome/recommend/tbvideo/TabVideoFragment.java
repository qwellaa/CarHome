package com.lanou3g.carhome.recommend.tbvideo;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;
import com.lanou3g.carhome.networkrequest.GsonRequest;
import com.lanou3g.carhome.networkrequest.URLValues;
import com.lanou3g.carhome.networkrequest.VolleySingleton;
import com.lanou3g.carhome.networkrequest.WebViewActivity;

/**
 *
 */
public class TabVideoFragment extends BaseFragment{

    private PullToRefreshListView plvVideo;
    private TextView tvAll;
    private TabVideoAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView() {
        tvAll = bindView(R.id.tv_all_video);
        plvVideo = bindView(R.id.pLv_recommend_video);
    }

    @Override
    protected void initData() {
        plvVideo.setMode(PullToRefreshBase.Mode.BOTH);
        plvVideo.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                initSendInterent();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        adapter = new TabVideoAdapter(context);
        plvVideo.setAdapter(adapter);
        initSendInterent();

        plvVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TabVideoBean bean = (TabVideoBean) parent.getItemAtPosition(position);
//                int urlId = bean.getResult().getList().get(position - 1).getId();
//                String url = "http://v.autohome.com.cn/v-" + urlId + ".html";
                String url = bean.getResult().getList().get(position - 1).getShareaddress();
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("urlWv", url);
                intent.putExtra("isAccordingShare", true);
                getActivity().startActivity(intent);
            }
        });
    }

    private void initSendInterent() {
        GsonRequest<TabVideoBean> gsonRequest = new GsonRequest<TabVideoBean>(URLValues.VIDEO_URL,
                TabVideoBean.class,
                new Response.Listener<TabVideoBean>() {
                    @Override
                    public void onResponse(TabVideoBean response) {
                        adapter.setBean(response);

                        plvVideo.onRefreshComplete();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
}
