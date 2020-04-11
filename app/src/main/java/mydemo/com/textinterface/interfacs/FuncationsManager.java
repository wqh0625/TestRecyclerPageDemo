package mydemo.com.textinterface.interfacs;

import android.text.TextUtils;

import java.util.HashMap;

import mydemo.com.textinterface.FuncationException;

/**
 * 作者: Wang on 2019/6/8 11:20
 * 寄语：加油！相信自己可以！！！
 */


public class FuncationsManager {
    private static FuncationsManager manager;
    private HashMap<String, FunctionNoPrarmNoResult> mFunctionNoPrarmNoResult;
    private HashMap<String, FuncationWithResultOnly> mFuncationWithResultOnly;
    private HashMap<String, FuncationWithParamOnly> mFuncationWithParamOnly;
    private HashMap<String, FuncationWithParamWithResult> mFuncationWithParamWithResult;

    public FuncationsManager() {
        mFunctionNoPrarmNoResult = new HashMap<>();
        mFuncationWithResultOnly = new HashMap<>();
        mFuncationWithParamOnly = new HashMap<>();
        mFuncationWithParamWithResult = new HashMap<>();
    }

    public static FuncationsManager getInsance() {
        if (manager == null) {
            manager = new FuncationsManager();
        }
        return manager;
    }

    public FuncationsManager addFuncation(FunctionNoPrarmNoResult function) {
        mFunctionNoPrarmNoResult.put(function.mFunctionName, function);
        return this;
    }

    public void invokeFuncation(String funcaName) {
        if (TextUtils.isEmpty(funcaName)==true) {
            return;
        }
        if (mFunctionNoPrarmNoResult != null) {
            FunctionNoPrarmNoResult f = mFunctionNoPrarmNoResult.get(funcaName);
            if (f != null) {
                f.funcation();
            }
            try {
                throw new FuncationException("Error" + funcaName);
            } catch (FuncationException e) {
                e.printStackTrace();
            }
        }
    }
}
