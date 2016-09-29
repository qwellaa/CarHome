package com.lanou3g.carhome.findcar.newcar;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;
import com.lanou3g.carhome.networkrequest.GsonRequest;
import com.lanou3g.carhome.networkrequest.URLValues;
import com.lanou3g.carhome.networkrequest.VolleySingleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 *
 */
public class NewCarFragment extends BaseFragment {

    private HashMap<String, Integer> selector;// 存放含有索引字母的位置
    private LinearLayout layoutIndex;
    private ListView lv;
    private TextView tvShow;
    private NewCarAdapter adapter;
    private String[] indexStr = {"A", "B", "C", "D", "F", "G", "H",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "W", "X", "Y", "Z"};
    private List<CarNameBean> nameList = null;
    private List<CarNameBean> newNameList = new ArrayList<CarNameBean>();
    private int height;// 字体高度
    private boolean flag = false;

    @Override
    protected int setLayout() {
        return R.layout.fragment_findcar_new_car;
    }

    @Override
    protected void initView() {
        layoutIndex = bindView(R.id.ll_new_car);
        layoutIndex.setBackgroundColor(Color.parseColor("#00ffffff"));
        lv = bindView(R.id.lv_new_car);
        tvShow = bindView(R.id.tv_new_car);
        tvShow.setVisibility(View.GONE);
        nameList = new ArrayList<>();

        initSendInterent();
    }

    @Override
    protected void initData() {
        ViewTreeObserver observer = layoutIndex.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                /**************************/
                if (!flag) {
                    height = layoutIndex.getMeasuredHeight() / indexStr.length;
                    getIndexView();
                    flag = true;
                }
                return true;
            }
        });
    }

    private void initSendInterent() {
        GsonRequest<NewCarBean> gsonRequest = new GsonRequest<NewCarBean>(URLValues.ALL_CAR_URL,
                NewCarBean.class,
                new Response.Listener<NewCarBean>() {

                    private CarNameBean nameBean;

                    @Override
                    public void onResponse(NewCarBean response) {
                        for (int i = 0; i < response.getResult().getBrandlist().size(); i++) {
                            for (int j = 0; j < response.getResult().getBrandlist().get(i).getList().size(); j++) {
                                nameBean = new CarNameBean(response.getResult().getBrandlist().get(i).getList().get(j).getName());
                                nameBean.setImageUrl(response.getResult().getBrandlist().get(i).getList().get(j).getImgurl());
                                nameList.add(nameBean);
                            }
                        }
                        String[] allNames = sortIndex(nameList);
                        sortList(allNames);
                        selector = new HashMap<String, Integer>();
                        // 循环字母表，找出newPersons中对应字母的位置
                        for (int j = 0; j < indexStr.length; j++) {
                            for (int i = 0; i < newNameList.size(); i++) {
                                if (newNameList.get(i).getName().equals(indexStr[j])) {
                                    selector.put(indexStr[j], i);
                                }
                            }

                        }
                        adapter = new NewCarAdapter(getContext(), newNameList);
                        lv.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }

    private void sortList(String[] allNames) {
        for (int i = 0; i < allNames.length; i++) {
            if (allNames[i].length() != 1) {
                for (int j = 0; j < nameList.size(); j++) {
                    if (allNames[i].equals(nameList.get(j).getPinYinName())) {
                        CarNameBean p = new CarNameBean(nameList.get(j).getName(), nameList
                                .get(j).getPinYinName());
                        p.setImageUrl(nameList.get(j).getImageUrl());
                        newNameList.add(p);
                    }
                }
            } else {
                newNameList.add(new CarNameBean(allNames[i]));
            }
        }
    }

    /**
     * 获取排序后的新数据
     *
     * @param nameList
     * @return
     */
    private String[] sortIndex(List<CarNameBean> nameList) {
        TreeSet<String> set = new TreeSet<String>();
        // 获取初始化数据源中的首字母，添加到set中
        for (CarNameBean bean : nameList) {
            set.add(StringHelper.getPinYinHeadChar(bean.getName()).substring(
                    0, 1));
        }
        // 新数组的长度为原数据加上set的大小
        String[] names = new String[nameList.size() + set.size()];
        int i = 0;
        for (String string : set) {
            names[i] = string;
            i++;
        }
        String[] pinYinNames = new String[nameList.size()];
        for (int j = 0; j < nameList.size(); j++) {
            nameList.get(j).setPinYinName(
                    StringHelper
                            .getPingYin(nameList.get(j).getName().toString()));
            pinYinNames[j] = StringHelper.getPingYin(nameList.get(j).getName()
                    .toString());
        }
        // 将原数据拷贝到新数据中
        System.arraycopy(pinYinNames, 0, names, set.size(), pinYinNames.length);
        // 自动按照首字母排序
        Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
        return names;

    }

    public void getIndexView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, height);
        for (int i = 0; i < indexStr.length; i++) {
            final TextView tv = new TextView(getContext());
            tv.setLayoutParams(params);
            tv.setText(indexStr[i]);
            tv.setPadding(10, 0, 10, 0);
            layoutIndex.addView(tv);
            layoutIndex.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event)

                {
                    float y = event.getY();
                    int index = (int) (y / height);
                    if (index > -1 && index < indexStr.length) {// 防止越界
                        String key = indexStr[index];
                        if (selector.containsKey(key)) {
                            int pos = selector.get(key);
                            if (lv.getHeaderViewsCount() > 0) {// 防止ListView有标题栏，本例中没有。
                                lv.setSelectionFromTop(
                                        pos + lv.getHeaderViewsCount(), 0);
                            } else {
                                lv.setSelectionFromTop(pos, 0);// 滑动到第一项
                            }
                            tvShow.setVisibility(View.VISIBLE);
                            tvShow.setText(indexStr[index]);
                        }
                    }
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            layoutIndex.setBackgroundColor(Color
                                    .parseColor("#00ffffff"));
                            break;

                        case MotionEvent.ACTION_MOVE:

                            break;
                        case MotionEvent.ACTION_UP:
                            layoutIndex.setBackgroundColor(Color
                                    .parseColor("#00ffffff"));
                            tvShow.setVisibility(View.GONE);
                            break;
                    }
                    return true;
                }
            });
        }
    }
}