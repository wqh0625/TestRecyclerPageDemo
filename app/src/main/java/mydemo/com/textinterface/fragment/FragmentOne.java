package mydemo.com.textinterface.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import mydemo.com.textinterface.R;
import mydemo.com.textinterface.view.TimeProgress;

/**
 * 作者: Wang on 2019/6/8 11:08
 * 寄语：加油！相信自己可以！！！
 */

public class FragmentOne extends BaseFragment {
    public static final String INTERFACE = FragmentOne.class.getName() + "npnr";

    int i = 10;
    private TimeProgress timeProgress;


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.frag_01, null);
        timeProgress = v.findViewById(R.id.time);

        MyHandler myHandler = new MyHandler(getActivity());
        myHandler.post(new Runnable() {
            @Override
            public void run() {

            }
        });
        myHandler.sendEmptyMessage(1);
        v.findViewById(R.id.frag1_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (manager == null) {

                    i = 15;

                } else {
                    manager.invokeFuncation(INTERFACE);
                }
            }
        });
        return v;
    }


    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:

                    break;
            }
        }
    };


    static class MyHandler extends Handler {
        int i;
        WeakReference<FragmentActivity> mWeakReference;

        public MyHandler(FragmentActivity activity) {
            mWeakReference = new WeakReference<FragmentActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final FragmentActivity activity = mWeakReference.get();
            if (activity != null) {
                if (msg.what == 1) {
//                    mWeakReference.get().
                    i--;
                    if (i == 0) {

                    }
//                    mWeakReference
//                    activityh
//                    activity.timeProgress.setProgress(10 * i);
//                    handler.sendEmptyMessageDelayed(1, 100);
                }
            }
        }
    }
}
