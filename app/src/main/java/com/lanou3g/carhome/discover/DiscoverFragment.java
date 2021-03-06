package com.lanou3g.carhome.discover;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;
import com.lanou3g.carhome.discover.activityzone.ActivityZoneAdapter;
import com.lanou3g.carhome.discover.activityzone.LimitedBuyTextView;
import com.lanou3g.carhome.discover.activityzone.OnZoneRecyclerItemClickListener;
import com.lanou3g.carhome.discover.businessenty.BusinessAdapter;
import com.lanou3g.carhome.discover.businessenty.OnRecyclerItemClickListener;
import com.lanou3g.carhome.discover.businessenty.allbusinessenty.AllBuessActivity;
import com.lanou3g.carhome.discover.formerecommend.ForMeRecommedAdapter;
import com.lanou3g.carhome.discover.goodslist.GoodsListAdapter;
import com.lanou3g.carhome.networkrequest.GsonRequest;
import com.lanou3g.carhome.networkrequest.URLValues;
import com.lanou3g.carhome.networkrequest.VolleySingleton;
import com.lanou3g.carhome.networkrequest.WebViewActivity;
import com.lanou3g.carhome.search.SearchActivity;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

/**
 *
 */
public class DiscoverFragment extends BaseFragment{

    private PullToRefreshListView plvDiscover;
    private GoodsListAdapter goodsListAdapter;
    private TextView tvTitleGoodsList, tvTitleForMe, tvTitleGuess, mTvTitleSale, mTVtitleServicrZone,
                     mTvTextScoll, mTvTitleScoll;
    private ForMeRecommedAdapter forMeRecommedAdapter;
    private RecyclerView rvForMe, rvGuess, rvZone, mRvSale, mServiceZoneRv, mRvEntry;
    private ForMeRecommedAdapter guessAdapter;
    private ImageView imageSmallBar, mIvTextScoll, ivBigImage;
    private ActivityZoneAdapter zoneAdapter;
    private Button mBtnRightBtn;
    private ActivityZoneAdapter mSaleAdapter;
    private ActivityZoneAdapter mServiceZoneAdapter;
    private BusinessAdapter businessAdapter;
    private Banner banner;
    private LinearLayout llDate;
    private ImageButton iBtnSearch;

    @Override
    protected int setLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initView() {
        plvDiscover = bindView(R.id.pLv_discover);


        initHeanView();

        iBtnSearch = bindView(R.id.ibtn_search_custom_title_discover);
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

        iBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(getActivity(), SearchActivity.class);
                searchIntent.putExtra("hint", "搜索车系或服务");
                startActivity(searchIntent);
            }
        });

        initSetAdapter();
        initSendInterent();
    }

    private void initSetAdapter() {

        goodsListAdapter = new GoodsListAdapter(context);
        forMeRecommedAdapter = new ForMeRecommedAdapter(context);
        guessAdapter = new ForMeRecommedAdapter(context);
        zoneAdapter = new ActivityZoneAdapter(context);
        mSaleAdapter = new ActivityZoneAdapter(context);
        mServiceZoneAdapter = new ActivityZoneAdapter(context);
        businessAdapter = new BusinessAdapter(context);


        plvDiscover.setAdapter(goodsListAdapter);
        rvForMe.setAdapter(forMeRecommedAdapter);
        rvGuess.setAdapter(guessAdapter);
        rvZone.setAdapter(zoneAdapter);
        mRvSale.setAdapter(mSaleAdapter);
        mServiceZoneRv.setAdapter(mServiceZoneAdapter);
        mRvEntry.setAdapter(businessAdapter);

        GridLayoutManager forMeManager = new GridLayoutManager(context, 2);
        rvForMe.setLayoutManager(forMeManager);
        GridLayoutManager guessMeManager = new GridLayoutManager(context, 2);
        rvGuess.setLayoutManager(guessMeManager);
        GridLayoutManager zoneManager = new GridLayoutManager(context, 3);
        rvZone.setLayoutManager(zoneManager);
        LinearLayoutManager saleManager = new LinearLayoutManager(context);
        saleManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvSale.setLayoutManager(saleManager);
        GridLayoutManager serviceZoneManager = new GridLayoutManager(context, 2);
        mServiceZoneRv.setLayoutManager(serviceZoneManager);
        GridLayoutManager entryManager = new GridLayoutManager(context, 5);
        mRvEntry.setLayoutManager(entryManager);

        onClick();
    }

    private void onClick() {
        businessAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void click(int postion, BusinessAdapter.ViewHolder holder) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                switch (postion) {
                    case 9:
                        Intent intent1 = new Intent(getActivity(), AllBuessActivity.class);
                        startActivity(intent1);
                        break;
                    case 8:
                        intent.putExtra("urlWv", URLValues.DISCOVER_CAR_VALUATION);
                        startActivity(intent);
                        break;
                    case 7:
                        intent.putExtra("urlWv", URLValues.DISCOVER_GROUP_BUY);
                        startActivity(intent);
                        break;
                    case 6:
                        Toast.makeText(context, "购车计算", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        intent.putExtra("urlWv",URLValues.DISCOVER_QUERY_URL);
                        startActivity(intent);
                        break;
                    case 4:
                        intent.putExtra("urlWv", URLValues.DISCOVER_FIND_CAR);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.putExtra("urlWv", URLValues.DISCOVER_SUBSIDY_HOME);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("urlWv", URLValues.DISCOVER_HIRE_CAR);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("urlWv", URLValues.DISCOVER_CAR_MALL);
                        startActivity(intent);
                        break;
                    case 0:
                        Toast.makeText(context, "汽车音频", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });

        mSaleAdapter.setOnZoneRecyclerItemClickListener(new OnZoneRecyclerItemClickListener() {
            @Override
            public void click(int postion, ActivityZoneAdapter.ViewHolder holder, DiscoverBean discoverBean) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                for (int i = 0; i < discoverBean.getResult().getCardlist().size(); i++) {
                    if (discoverBean.getResult().getCardlist().get(i).getDescription().equals("限时抢购")) {
                        String strUrl = discoverBean.getResult().getCardlist().get(i).getData().get(postion)
                                .getStatistics().getLink();
                        Log.d("DiscoverFragment", strUrl);
                        intent.putExtra("urlWv", strUrl);
                    }
                }
               startActivity(intent);
            }
        });
    }

    private void initSendInterent() {
        final GsonRequest<DiscoverBean> gsonRequest = new GsonRequest<DiscoverBean>(URLValues.GOODS_LIST_URL,
                DiscoverBean.class,
                new Response.Listener<DiscoverBean>() {
                    @Override
                    public void onResponse(DiscoverBean response) {

                        initCycleJudgment(response);

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

    private void initCycleJudgment(DiscoverBean response) {
        for (int i = 0; i < response.getResult().getCardlist().size(); i++) {

            if (response.getResult().getCardlist().get(i).getDescription().equals("商品列表")) {
                goodsListAdapter.setId(i);
                goodsListAdapter.setBean(response);
                tvTitleGoodsList.setText(response.getResult().getCardlist().get(i).getTitle());
            }
            if (response.getResult().getCardlist().get(i).getDescription().equals("模块列表")) {
                if (response.getResult().getCardlist().get(i).getTopblanktype().equals("0")) {
                    forMeRecommedAdapter.setId(i);
                    forMeRecommedAdapter.setBean(response);
                    tvTitleForMe.setText(response.getResult().getCardlist().get(i).getTitle());
                } else if (response.getResult().getCardlist().get(i).getTopblanktype().equals("2")) {
                    guessAdapter.setId(i);
                    guessAdapter.setBean(response);
                    tvTitleGuess.setText(response.getResult().getCardlist().get(i).getTitle());
                }
            }
            if (response.getResult().getCardlist().get(i).getDescription().equals("单帧小号横栏")) {
                if (response.getResult().getCardlist().get(i).getTopblanktype().equals("2")){
                    Picasso.with(context).load(response.getResult().getCardlist().get(i).getData().get(0).getImageurl())
                            .placeholder(R.mipmap.ahlib_carback).error(R.mipmap.ahlib_carback).into(imageSmallBar);
                }
            }

            if (response.getResult().getCardlist().get(i).getDescription().equals("活动专区")) {
                zoneAdapter.setId(i);
                zoneAdapter.setBean(response);
                mTvTitleSale.setText(response.getResult().getCardlist().get(i).getTitle());
                mBtnRightBtn.setText(response.getResult().getCardlist().get(i).getRightbtn().getData());
            }

            if (response.getResult().getCardlist().get(i).getDescription().equals("限时抢购")) {
                mSaleAdapter.setId(i);
                mSaleAdapter.setBean(response);
                mTVtitleServicrZone.setText(response.getResult().getCardlist().get(i).getTitle());
                LimitedBuyTextView limited = new LimitedBuyTextView(context, response.getResult().getCardlist().get(i)
                        .getRightbtn().getData());
                llDate.addView(limited.initTime());
            }

            if (response.getResult().getCardlist().get(i).getDescription().equals("田字小号专区")) {
                mServiceZoneAdapter.setId(i);
                mServiceZoneAdapter.setBean(response);
                mTvTitleScoll.setText(response.getResult().getCardlist().get(i).getTitle());
            }

            if (response.getResult().getCardlist().get(i).getDescription().equals("文字滚动链")) {
                Picasso.with(context).load(response.getResult().getCardlist().get(i).getImageurl())
                        .placeholder(R.mipmap.ahlib_carback).error(R.mipmap.ahlib_carback).into(mIvTextScoll);
            }

            if (response.getResult().getCardlist().get(i).getDescription().equals("业务入口")) {
                businessAdapter.setId(i);
                businessAdapter.setBean(response);
            }

            if (response.getResult().getCardlist().get(i).getDescription().equals("单帧大号横栏")) {
                Picasso.with(context).load(response.getResult().getCardlist().get(i).getData().get(0).getImageurl())
                        .placeholder(R.mipmap.ahlib_carback).error(R.mipmap.ahlib_carback).into(ivBigImage);
            }

            if (response.getResult().getCardlist().get(i).getDescription().equals("焦点图")) {
                ArrayList<String> images = new ArrayList<>();
                for (int j = 0; j < response.getResult().getCardlist().get(i).getData().size(); j++) {
                   images.add(response.getResult().getCardlist().get(i).getData().get(j).getImageurl());
                }

                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                banner.setIndicatorGravity(BannerConfig.CENTER);
                banner.setImages(images);
            }

        }
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

        View viewActivityZone = LayoutInflater.from(context).inflate(R.layout.headview_activity_zone, null);
        rvZone = (RecyclerView) viewActivityZone.findViewById(R.id.rv_headview_activity_zone);

        View viewFlashSale = LayoutInflater.from(context).inflate(R.layout.headview_discover_flash_sale, null);
        mRvSale = (RecyclerView) viewFlashSale.findViewById(R.id.rv_headview_flash_sale);
        mTvTitleSale = (TextView) viewFlashSale.findViewById(R.id.tv_heandview_title_flash_sale);
        mBtnRightBtn = (Button) viewFlashSale.findViewById(R.id.btn_headview_rightbtn_flash_sale);

        View viewServiceZone = LayoutInflater.from(context).inflate(R.layout.headview_discover_service_zone, null);
        mServiceZoneRv = (RecyclerView) viewServiceZone.findViewById(R.id.rv_headview_service_zone);
        mTVtitleServicrZone = (TextView) viewServiceZone.findViewById(R.id.tv_heandview_title_service_zone);
        llDate = (LinearLayout) viewServiceZone.findViewById(R.id.ll_headview_discover_service_zone);

        View viewTextScoll = LayoutInflater.from(context).inflate(R.layout.headview_text_scoll, null);
        mIvTextScoll = (ImageView) viewTextScoll.findViewById(R.id.iv_headview_text_scoll);
        mTvTextScoll = (TextView) viewTextScoll.findViewById(R.id.tv_headview_body_text_scoll);
        mTvTitleScoll = (TextView) viewTextScoll.findViewById(R.id.tv_headview_title_text_scoll);

        View viewBusinessEntry = LayoutInflater.from(context).inflate(R.layout.headview_business_entry, null);
        mRvEntry = (RecyclerView) viewBusinessEntry.findViewById(R.id.rv_headview_business_entry);

        View viewBigImage = LayoutInflater.from(context).inflate(R.layout.headview_big_image, null);
        ivBigImage = (ImageView) viewBigImage.findViewById(R.id.iv_headview_big_image);

        View viewBinner = LayoutInflater.from(context).inflate(R.layout.headview_banner, null);
        banner = new Banner(context);
        banner = (Banner) viewBinner.findViewById(R.id.banner_heandview_banner);

        ListView lv = plvDiscover.getRefreshableView();
        lv.addHeaderView(viewBinner);
        lv.addHeaderView(viewBigImage);
        lv.addHeaderView(viewBusinessEntry);
        lv.addHeaderView(viewTextScoll);
        lv.addHeaderView(viewServiceZone);
        lv.addHeaderView(viewFlashSale);
        lv.addHeaderView(viewActivityZone);
        lv.addHeaderView(viewSmallCrossBar);
        lv.addHeaderView(viewGuess);
        lv.addHeaderView(viewForMe);
    }

}
