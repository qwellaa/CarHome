package com.lanou3g.carhome.recommend.tabmore;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
public class TabMoreActivity extends BaseActivity{

    private ImageButton ibtnBack;
    private RecyclerView rvMore;
    private TabMoreAdapter moreAdapter;
//    private List<MoreBean> beanList = new ArrayList<>();

    @Override
    protected int setLayout() {
        return R.layout.activity_recommend_more;
    }

    @Override
    protected void initView() {
        ibtnBack = bindView(R.id.ibtn_back_recommend_more);
        rvMore = bindView(R.id.rv_recommend_more);
    }

    @Override
    protected void initData() {
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        moreAdapter = new TabMoreAdapter(this);
        rvMore.setAdapter(moreAdapter);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        rvMore.setLayoutManager(manager);

        ItemTouchHelper.Callback callback = createCallback();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rvMore);

        initSendInterent();
    }

    private ItemTouchHelper.Callback createCallback() {
        return new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //两个参数
                //参数一 决定行布局支持哪种拖拉的手势
                //参数二 决定行布局支持哪种滑动的手势
                return ItemTouchHelper.Callback.makeMovementFlags(
                        ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT |
                                ItemTouchHelper.RIGHT, 0);
            }

            //作用:移动了行布局之后会回调该方法
            //参数一
            //参数二 移动的行布局对应的ViewHolder
            //参数三 移动到的位置所对应的ViewHolder
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                moreAdapter.move(from, to);
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            // 当长按选中item的时候(拖动开始的时候)调用
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE){
                    viewHolder.itemView.setBackgroundResource(R.drawable.btn_write_bg_p);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            // 当手指松开的时候(拖动完成的时候调用
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundResource(R.drawable.btn_write_bg);
            }
        };
    }

    private void initSendInterent() {
        GsonRequest<TabMoreBean> gsonRequest = new GsonRequest<TabMoreBean>(URLValues.TAB_ALL_URL,
                TabMoreBean.class,
                new Response.Listener<TabMoreBean>() {
                    @Override
                    public void onResponse(TabMoreBean response) {
                        moreAdapter.setBean(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
}
