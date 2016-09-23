package com.lanou3g.carhome.discover;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;
import com.lanou3g.carhome.discover.formerecommend.ForMeRecommedAdapter;
import com.lanou3g.carhome.discover.goodslist.GoodsListAdapter;
import com.lanou3g.carhome.networkrequest.GsonRequest;
import com.lanou3g.carhome.networkrequest.URLValues;
import com.lanou3g.carhome.networkrequest.VolleySingleton;
import com.squareup.picasso.Picasso;

/**
 *
 */
public class DiscoverFragment extends BaseFragment{

    private PullToRefreshListView plvDiscover;
    private GoodsListAdapter goodsListAdapter;
    private TextView tvTitleGoodsList;
    private ForMeRecommedAdapter forMeRecommedAdapter;
    private RecyclerView rvForMe;
    private ForMeRecommedAdapter guessAdapter;
    private RecyclerView rvGuess;
    private TextView tvTitleForMe;
    private TextView tvTitleGuess;
    private ImageView imageSmallBar;

    @Override
    protected int setLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initView() {
        plvDiscover = bindView(R.id.pLv_discover);


        initHeanView();

    }

    @Override
    protected void initData() {
        plvDiscover.setMode(PullToRefreshBase.Mode.BOTH);

        plvDiscover.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                initSendInterent();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

        goodsListAdapter = new GoodsListAdapter(context);
        forMeRecommedAdapter = new ForMeRecommedAdapter(context,10);
        guessAdapter = new ForMeRecommedAdapter(context, 9);


        initSendInterent();

        plvDiscover.setAdapter(goodsListAdapter);
        rvForMe.setAdapter(forMeRecommedAdapter);
        rvGuess.setAdapter(guessAdapter);

        GridLayoutManager forMeManager = new GridLayoutManager(context, 2);
        rvForMe.setLayoutManager(forMeManager);
        GridLayoutManager guessMeManager = new GridLayoutManager(context, 2);
        rvGuess.setLayoutManager(guessMeManager);
    }

    private void initSendInterent() {
        final GsonRequest<DiscoverBean> gsonRequest = new GsonRequest<DiscoverBean>(URLValues.GOODS_LIST_URL,
                DiscoverBean.class,
                new Response.Listener<DiscoverBean>() {
                    @Override
                    public void onResponse(DiscoverBean response) {
                        goodsListAdapter.setBean(response);
                        forMeRecommedAdapter.setBean(response);
                        guessAdapter.setBean(response);

                        Picasso.with(context).load(response.getResult().getCardlist().get(8).getData().get(0).getImageurl()).into(imageSmallBar);
                        initSetTitle(response);
                        plvDiscover.onRefreshComplete();
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }

    private void initHeanView() {

        View viewForMe = LayoutInflater.from(context).inflate(R.layout.headview_for_me_recommend, null);
        rvForMe = (RecyclerView) viewForMe.findViewById(R.id.rv_headview_for_me_recommend);
        tvTitleGoodsList = (TextView) viewForMe.findViewById(R.id.tv_title_goods_list);

        View viewGuess = LayoutInflater.from(context).inflate(R.layout.headview_for_me_recommend, null);
        rvGuess = (RecyclerView) viewGuess.findViewById(R.id.rv_headview_for_me_recommend);
        tvTitleForMe = (TextView) viewGuess.findViewById(R.id.tv_title_goods_list);

        View viewSmallCrossBar = LayoutInflater.from(context).inflate(R.layout.headview_small_cross_bar, null);
        imageSmallBar = (ImageView) viewSmallCrossBar.findViewById(R.id.iv_headview_small_cross_bar);
        tvTitleGuess = (TextView) viewSmallCrossBar.findViewById(R.id.tv_headview_title_for_me);


        ListView lv = plvDiscover.getRefreshableView();
        lv.addHeaderView(viewSmallCrossBar);
        lv.addHeaderView(viewGuess);
        lv.addHeaderView(viewForMe);
    }

    private void initSetTitle(DiscoverBean response) {
        tvTitleGoodsList.setText(response.getResult().getCardlist().get(11).getTitle());
        tvTitleForMe.setText(response.getResult().getCardlist().get(9).getTitle());
        tvTitleGuess.setText(response.getResult().getCardlist().get(8).getTitle());
    }
}
