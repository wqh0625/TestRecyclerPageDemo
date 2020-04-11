package com.wqh.livedatabug.list.adapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wqh.livedatabug.R;
import com.wqh.livedatabug.list.bean.AnimationBean;
import com.wqh.livedatabug.weight.AuthorLayout;
import com.wqh.livedatabug.weight.AuthorRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: Wang on 2019/11/17 13:45
 * 寄语：加油！相信自己可以！！！
 */


public class HeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int isClick = 0;
    private Context context;
    private List<AnimationBean> datas;

    private List<Holder> holderList;
    private float headCount;

    public HeaderAdapter(Context context ) {
        this.context = context;
        holderList = new ArrayList<>();
    }

    public void setDatas(List<AnimationBean> datas) {
        this.datas = datas;
        for (int i = 0; i < this.datas.size(); i++) {
            headCount = headCount+this.datas.get(i).getCount();
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_header_item, parent, false));
    }

    public class Holder extends RecyclerView.ViewHolder {
//        private   AuthorLayout la;
        private LinearLayout authorRelativeLayout;
        private TextView tv1;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv_header_content);
            authorRelativeLayout = itemView.findViewById(R.id.ll_header_inner);
//            la = itemView.findViewById(R.id.la);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final Holder holder = (Holder) viewHolder;
        holderList.add(holder);
        holder.tv1.setText(datas.get(i).getName());
        if (isClick == 0) {

            holder.authorRelativeLayout.setGravity(Gravity.CENTER);
            holder.tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isClick = 1;
//                    holder.tv1.setText(datas.get(i)+"点击了点击了");
                    notifyDataSetChanged();
                }
            });
        }else{
            startdonghua(holder,i);

//            setProgress(holder,i);
        }

    }

    private void setProgress(Holder holder, int i) {
//        if (datas.get(i).getIsVote() == 1) {
//            holder.authorRelativeLayout.setProgressColor(Color.RED);
//        }else{
//            holder.authorRelativeLayout.setProgressColor(Color.GRAY);
//        }

//        Log.v("WQH","进度："+headCount+":"+(float)datas.get(i).getCount()/headCount+datas.get(i).getName());
//        holder.la.setGradient(false);
//        holder.la.setProgress((float) ((datas.get(i).getCount()/headCount))*100);
    }

    private  void startdonghua(Holder holder, int i) {
        Log.v("WQH",""+holder.tv1.getLeft());
        ObjectAnimator x = ObjectAnimator.ofFloat(holder.tv1, "translationX", 0, -holderList.get(i).tv1.getLeft());
        x.setDuration(1200);
        x.start();


        ValueAnimator valueAnimator = ObjectAnimator.ofFloat( 0, holder.tv1.getLeft());
        valueAnimator.setDuration(1200);
        valueAnimator.start();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
