package com.wqh.livedatabug.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.wqh.livedatabug.R;
import com.wqh.livedatabug.list.adapter.MyListAdapter;
import com.wqh.livedatabug.list.bean.NewslistBean;
import com.wqh.livedatabug.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Create on 2019/11/17
 * aothor is 王庆浩
 *
 */
public class Main2Activity extends AppCompatActivity {

    private RecyclerView rvMainView;
    private MyListAdapter myListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        rvMainView = findViewById(R.id.rv_main);

        rvMainView.setLayoutManager(new LinearLayoutManager(this));
        List<NewslistBean> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {

            list.add(new NewslistBean("正常条目"+i
                    ,"描述"));
        }
        myListAdapter = new MyListAdapter(Main2Activity.this, list);
        setHeader(rvMainView);
        LogUtils.w("");
//        rvMainView.setItemViewCacheSize(15);
        rvMainView.setAdapter(myListAdapter);
    }

    private void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(this).inflate(R.layout.layout_header, view, false);
        myListAdapter.setHeaderView(header);
    }
}
