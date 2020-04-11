package mydemo.com.textinterface;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import mydemo.com.textinterface.fragment.BaseFragment;
import mydemo.com.textinterface.fragment.FragmentOne;
import mydemo.com.textinterface.fragment.FragmentThree;
import mydemo.com.textinterface.fragment.FragmentTwo;
import mydemo.com.textinterface.interfacs.FuncationsManager;
import mydemo.com.textinterface.interfacs.FunctionNoPrarmNoResult;

public class MainActivity extends AppCompatActivity {

    /**
     *  使用接口进行Activity和Fragment进行通信
     * 1. Fragment 定义接口(创建接口)
     * 2. Activity 实现接口(调用接口)
     * 3. 将接口与Fragment和Activity绑定 ，暴露
     * 4. 使用
     */
    private RadioGroup group;
    private FragmentOne pageFragment_01;
    private FragmentTwo pageFragment_02;
    private FragmentThree pageFragment_03;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        group = findViewById(R.id.group);

        pageFragment_01 = new FragmentOne();
        pageFragment_02 = new FragmentTwo();
        pageFragment_03 = new FragmentThree();

        MyHandler myHandler = new MyHandler(this);
        myHandler.sendEmptyMessage(2);
        manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.frame, pageFragment_01).show(pageFragment_01);
        fragmentTransaction.add(R.id.frame, pageFragment_02).hide(pageFragment_02);
        fragmentTransaction.add(R.id.frame, pageFragment_03).hide(pageFragment_03);
        fragmentTransaction.commit();
        group.check(group.getChildAt(0).getId());
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = manager.beginTransaction();
                set(checkedId, transaction);
            }

            private void set(int checkedId, FragmentTransaction transaction) {
                switch (checkedId - 1) {
                    case 0:
                        transaction.show(pageFragment_01).hide(pageFragment_02).hide(pageFragment_03).commit();
                        break;
                    case 1:
                        transaction.show(pageFragment_02).hide(pageFragment_01).hide(pageFragment_03).commit();
                        break;
                    case 2:
                        transaction.show(pageFragment_03).hide(pageFragment_01).hide(pageFragment_02).commit();
                        break;

                }
            }
        });
    }

    public void setFuncaitonForFragment(String tag){
//      FragmentManager fragmentManager = getSupportFragmentManager();
        BaseFragment baseFragment = (BaseFragment) manager.findFragmentByTag(tag);
        FuncationsManager insance = FuncationsManager.getInsance();
        baseFragment.setManager(insance.addFuncation(new FunctionNoPrarmNoResult(FragmentOne.INTERFACE) {
            @Override
            public void funcation() {
                Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    int i;
    static class MyHandler extends Handler {
        WeakReference<MainActivity> mWeakReference;

        public MyHandler(MainActivity activity) {
            mWeakReference = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final MainActivity activity = mWeakReference.get();
            if (activity != null) {
                if (msg.what == 1) {
//                    mWeakReference.get().
//                    activity.i
//                    if (i == 0) {
//
//                    }
//                    mWeakReference
//                    activityh
//                    activity.timeProgress.setProgress(10 * i);
//                    handler.sendEmptyMessageDelayed(1, 100);
                }
            }
        }
    }
}
