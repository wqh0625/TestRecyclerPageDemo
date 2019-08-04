package mydemo.com.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Fruit> mFruitList;
    private Context mContext;
    private RecyclerOnScrollerListener mOnScrollListener;

    public static final int TYPE_HEADER = 0; //带有Header
    public static final int TYPE_FOOTER = 1; //带有Footer
    public static final int TYPE_NORMAL = 2; //不带有header和footer

    private View mHeaderView;
    private View mFooterView;

    private boolean isCanLoadMore = true;

    private Animation rotateAnimation;

    private static final int PER_PAGE = 10;

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    protected void setFooterView(View footerView) {
        mFooterView = footerView;
    }

    public FruitAdapter(List<Fruit> fruitList) {
        this.mFruitList = fruitList;
    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null && mFooterView == null) {
            return mFruitList.size();
        } else if (mHeaderView == null && mFooterView != null) {
            return mFruitList.size() + 1;
        } else if (mHeaderView != null && mFooterView == null) {
            return mFruitList.size() + 1;
        } else {
            return mFruitList.size() + 2;
        }
    }

    @Override
    public int getItemViewType(int position) {
//        if (position == getItemCount() - 1) {
//            return VIEW_TYPE_FOOTER;
//        }
//        return VIEW_TYPE_CONTENT;
        if (mHeaderView == null && mFooterView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0) { //第一个item应该加载Header
            return TYPE_HEADER;
        }
        if (position == getItemCount() - 1) { //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        if (rotateAnimation == null) {
            rotateAnimation = AnimationUtils.loadAnimation(mContext, R.anim.loading);
            rotateAnimation.setInterpolator(new LinearInterpolator());
        }
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new HeaderViewHolder(mHeaderView);
        }
        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return new FooterViewHolder(mFooterView);
        }
        return new ContentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_footer, null, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if (holder.getItemViewType() == VIEW_TYPE_CONTENT) {
        if (holder instanceof ContentViewHolder) {
            Fruit fruit = mFruitList.get(position - 1);
            //这里必须强制转换
            //如果外层的判断条件改为if(holder instance ContentViewHolder)，这里输入holder后会自动转换
            ((ContentViewHolder) holder).tvName.setText(fruit.getName());
            ((ContentViewHolder) holder).ivImage.setImageResource(fruit.getImageId());
        } else if (holder instanceof FooterViewHolder) {
            Log.d("mytest", "isCanLoadMore: " + isCanLoadMore);
            if (isCanLoadMore) {
                ((FooterViewHolder) holder).showLoading();
            } else {
                ((FooterViewHolder) holder).showTextOnly("无更多数据");
            }
        } else if (holder instanceof HeaderViewHolder) {
            Log.v("NULL shuyu", "");
        }
    }

    /*
     * 本例中所有数据加载完毕后还是保留footerView并显示已加载完全，所以footerView一直存在。
     * */

    //ContentView，水果们
    class ContentViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivImage;

        public ContentViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            ivImage = itemView.findViewById(R.id.iv_image);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);

        }
    }

    //底部的FooterView
    class FooterViewHolder extends RecyclerView.ViewHolder {

        ImageView ivLoading;
        TextView tvLoading;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ivLoading = itemView.findViewById(R.id.iv_loading);
            tvLoading = itemView.findViewById(R.id.tv_loading);
        }

        void showTextOnly(String s) {
            Log.d("mytest", "showTextOnly: " + s);
            ivLoading.setVisibility(View.INVISIBLE);
            tvLoading.setText(s);
        }

        void showLoading() {
            Log.i("mytest", "show loading");
            ivLoading.setImageResource(R.mipmap.ic_launcher);
            tvLoading.setText("正在加载...");
            if (ivLoading != null) {
                ivLoading.startAnimation(rotateAnimation);
            }
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mOnScrollListener = new RecyclerOnScrollerListener(recyclerView) {
            @Override
            public void onLoadMore(int currentPage) {
                Log.i("loadingtest", "currentPage: " + currentPage);
                mOnLoadMoreListener.onLoadMore(currentPage);
            }
        };
        recyclerView.addOnScrollListener(mOnScrollListener);
        //初始化的时候如果未填满一页，那么肯定就没有更多数据了
        if (mFruitList.size() < PER_PAGE) {
            setCanLoadMore(false);
        } else {
            setCanLoadMore(true);
        }
    }


    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mOnScrollListener != null) {
            recyclerView.removeOnScrollListener(mOnScrollListener);
        }
//        if (mAdapterDataObserver != null) {
//            unregisterAdapterDataObserver(mAdapterDataObserver);
//        }
        mOnScrollListener = null;
    }

    public void setData(List<Fruit> list) {
        mFruitList = list;
        notifyDataSetChanged();
    }

    /*
     * 数据加载完毕时执行setCanLoadMore()，此时isLoading都置为false
     * */
    public void setCanLoadMore(boolean isCanLoadMore) {
        this.isCanLoadMore = isCanLoadMore;
        mOnScrollListener.setCanLoadMore(isCanLoadMore);
        mOnScrollListener.setLoading(false);
    }


    public interface OnLoadMoreListener {
        void onLoadMore(int currentPage);
    }

    private OnLoadMoreListener mOnLoadMoreListener;

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mOnLoadMoreListener = listener;
    }

}