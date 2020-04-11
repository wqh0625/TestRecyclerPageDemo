package com.wqh.livedatabug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.wqh.livedatabug.interfase.BindClassLayout;
import com.wqh.livedatabug.interfase.BindView;
import com.wqh.livedatabug.utils.BingViewUtils;

@BindClassLayout(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        BingViewUtils.initLayout(this);
        BingViewUtils.initView(this);
        button.setText("王庆浩");
    }

    public static void main(String[] args) {
        int a=-1;
        System.out.println(a++);
    }
}
