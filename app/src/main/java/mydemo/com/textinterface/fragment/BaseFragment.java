package mydemo.com.textinterface.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import mydemo.com.textinterface.MainActivity;
import mydemo.com.textinterface.interfacs.FuncationsManager;

/**
 * 作者: Wang on 2019/6/8 11:32
 * 寄语：加油！相信自己可以！！！
 */


public class BaseFragment extends Fragment {

    protected FuncationsManager manager;
    private MainActivity mBaseActivity;

    public void setManager(FuncationsManager manager) {
        this.manager = manager;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mBaseActivity = (MainActivity)context;
            Log.v("aaa",""+getTag()+"  00"+mBaseActivity );

            if (getTag() == null) {

                return;
            }else{
                mBaseActivity.setFuncaitonForFragment(getTag());
            }
        }

    }

}
