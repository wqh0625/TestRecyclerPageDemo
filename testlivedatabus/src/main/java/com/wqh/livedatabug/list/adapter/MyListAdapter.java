package com.wqh.livedatabug.list.adapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wqh.livedatabug.R;
import com.wqh.livedatabug.list.bean.AnimationBean;
import com.wqh.livedatabug.list.bean.NewslistBean;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private View mHeaderView;
    private Context context;
    private int isVoted = 0;
    //    private ArrayList<BannerBean.DataBean> bannerList;
    private List<NewslistBean> articleList;

    public MyListAdapter(Context context, List<NewslistBean> articleList) {
        this.context = context;

        this.articleList = articleList;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void setArticleList(ArrayList<NewslistBean> articleList) {
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.v(TAG,"onCreate  " + viewType);
        if(mHeaderView != null && viewType == TYPE_HEADER){
            return new BannnerViewHolder(mHeaderView);
        }
        View layout = LayoutInflater.from(context).inflate(R.layout.layout_normal, parent, false);
        return new NormalViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        Log.v(TAG,"onBind  "+i);
//        viewHolder.setIsRecyclable(false);
        if (viewHolder instanceof BannnerViewHolder) {
            BannnerViewHolder normalViewHolder = (BannnerViewHolder) viewHolder;//强转成子类
            if (normalViewHolder.headerAdapter == null) {
                normalViewHolder.headerAdapter = new HeaderAdapter(context );
                normalViewHolder.headerAdapter.setDatas(normalViewHolder.da);
                normalViewHolder.rvHeaderView.setAdapter(normalViewHolder.headerAdapter);
            }



//            bannnerViewHolder.banner.setImages(bannerList);
//            bannnerViewHolder.banner.setImageLoader(new ImageLoader() {
//                @Override
//                public void displayImage(Context context, Object path, ImageView imageView) {
//                    BannerBean.DataBean dataBean = (BannerBean.DataBean) path;
//                    Glide.with(context).load(dataBean.getImagePath()).into(imageView);
//                }
//            });
//            bannnerViewHolder.banner.start();
        } else if (viewHolder instanceof NormalViewHolder) {

            NormalViewHolder normalViewHolder = (NormalViewHolder) viewHolder;


            if (mHeaderView == null) {
                normalViewHolder.tv1.setText(articleList.get(i).getTitle());
                if (isVoted == 0) {

                    normalViewHolder.tv1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                             isVoted = 1;
                             Log.v(TAG,"click --> " + i);
                             notifyDataSetChanged();
                        }
                    });
                }else{
                    startdonghua(normalViewHolder,i);
                    normalViewHolder.tv1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, ""+isVoted, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }else{
                normalViewHolder.tv1.setText(articleList.get(i-1).getTitle());

                normalViewHolder.tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, ""+articleList.get(i-1).getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

//            RequestOptions options = new RequestOptions().circleCrop();
//            Glide.with(context).load(newslistBean.getPicUrl()).apply(options).into(normalViewHolder.iv);
//
//            normalViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    if (setOnClickLongListener != null) {
//                        setOnClickLongListener.OnClickListener(newslistBean);
//                    }
//                    return false;
//                }
//            });
        }
    }
    private  void startdonghua(NormalViewHolder holder, int i) {
        Log.v("WQH",holder.tv1.getText()+"     "+holder.tv1.getLeft());
        ObjectAnimator x = ObjectAnimator.ofFloat(holder.tv1, "translationX", 0, -holder.tv1.getLeft());
        x.setDuration(1200);
        x.start();


        ValueAnimator valueAnimator = ObjectAnimator.ofFloat( 0, holder.tv1.getLeft());
        valueAnimator.setDuration(1200);
        valueAnimator.start();
    }
    //例:文章列表默认加载20条数据,现在我们布局中又多了一个控件,现在的长度就变成了21,所以需要考虑轮播图加载或没加载
    @Override
    public int getItemCount() {
        if (mHeaderView != null) {
            return articleList.size()+1;
        }else{
            return articleList.size();
        }

    }

    //根据条件返回条目的类型
    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_NORMAL;
    }

    public class BannnerViewHolder extends RecyclerView.ViewHolder {
        private   RecyclerView rvHeaderView;
        private   HeaderAdapter headerAdapter;
        private ImageView banner;
        private   List<AnimationBean> da;

        public BannnerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);

            rvHeaderView = itemView.findViewById(R.id.rv_header);
            rvHeaderView.setLayoutManager(new LinearLayoutManager(context));
            da = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                da.add(new AnimationBean(2+i,"小明上课吃东西"+i,0));

            }
//            da.add(new AnimationBean(1,"小花睡觉",0));
//            da.add(new AnimationBean(5,"李吃东西",0));
//            da.add(new AnimationBean(13,"晓东打游戏~~",0));
//            da.add(new AnimationBean(31,"晓东打游戏~~1",0));
//            da.add(new AnimationBean(12,"晓东打游戏~~2",0));
//            da.add(new AnimationBean(11,"晓东打游戏~~3",0));
//            da.add(new AnimationBean(15,"晓东打游戏~~4",0));
//            da.add(new AnimationBean(19,"晓东打游戏~~5",0));
//            da.add(new AnimationBean(21,"晓东打游戏~~6",0));
//            da.add(new AnimationBean(14,"晓东打游戏~~7",0));
        }
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv1;
        private   HeaderAdapter headerAdapter;
        private final RecyclerView rvHeaderView;
        private final List<String> da;
        public NormalViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv1 = itemView.findViewById(R.id.tv1);

            rvHeaderView = itemView.findViewById(R.id.rv_header);
            rvHeaderView.setLayoutManager(new LinearLayoutManager(context));
            da = new ArrayList<>();
            da.add("header");
            da.add("header");
            da.add("header");
            da.add("header");
        }
    }
}