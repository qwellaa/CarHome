package com.lanou3g.carhome.forum.selectionall;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;

import java.util.ArrayList;

/**
 *
 */
public class SelectionAllActivity extends BaseActivity{

    private ImageButton ibtnClose;
    private TextView tvTitle;
    private ListView lv;

    @Override
    protected int setLayout() {
        return R.layout.activity_selection_all;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_forum_selection_all);
        tvTitle = bindView(R.id.tv_title_custom_title_selection);
        ibtnClose = bindView(R.id.ibtn_back_custom_title_selection_all);
    }

    @Override
    protected void initData() {

        ArrayList<String> arrayList = new ArrayList<>();
        String[] strTitle = this.getResources().getStringArray(R.array.alls);

        for (int i = 0; i < strTitle.length; i++) {
            arrayList.add(strTitle[i]);
        }
        SelectionAllAdapter allAdapter = new SelectionAllAdapter(this);
        allAdapter.setArrayList(arrayList);
        lv.setAdapter(allAdapter);

        initIbtnClose();
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
