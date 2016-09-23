package com.lanou3g.carhome.forum.selectionall;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;
import com.lanou3g.carhome.forum.selectionrecommend.SelectionActivity;

import java.util.ArrayList;

/**
 *
 */
public class SelectionAllActivity extends BaseActivity{

    private ImageButton ibtnClose;
    private ListView lv;
    private ArrayList<String> arrayList;

    @Override
    protected int setLayout() {
        return R.layout.activity_selection_all;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_forum_selection_all);
        ibtnClose = bindView(R.id.ibtn_back_custom_title_selection_all);
    }

    @Override
    protected void initData() {

        arrayList = new ArrayList<>();
        String[] strTitle = this.getResources().getStringArray(R.array.alls);

        for (int i = 0; i < strTitle.length; i++) {
            arrayList.add(strTitle[i]);
        }
        SelectionAllAdapter allAdapter = new SelectionAllAdapter(this);
        allAdapter.setArrayList(arrayList);
        lv.setAdapter(allAdapter);

        initItemOnClisck();
        initIbtnClose();
    }

    private void initItemOnClisck() {
        Log.d("SelectionAllActivity", "1111");
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("SelectionAllActivity", "222");
                SelectionAllUrl allUrl = new SelectionAllUrl();
                ArrayList<String> urlList = allUrl.getUrlList();

                String strIntentTitle = arrayList.get(i);
                String strUrl = urlList.get(i);

                Intent intent = new Intent(SelectionAllActivity.this, SelectionActivity.class);
                intent.putExtra("title", strIntentTitle);
                intent.putExtra("url", strUrl);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initIbtnClose() {
        ibtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
