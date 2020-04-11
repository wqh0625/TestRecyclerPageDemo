package mydemo.com.textinterface.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mydemo.com.textinterface.R;

/**
 * 作者: Wang on 2019/6/8 11:08
 * 寄语：加油！相信自己可以！！！
 */


public class FragmentTwo extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = View.inflate(getActivity(),R.layout.frag_02,null);
        return v;
    }
}
