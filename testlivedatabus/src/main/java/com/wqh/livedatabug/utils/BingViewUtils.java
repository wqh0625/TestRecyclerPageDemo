package com.wqh.livedatabug.utils;

import android.app.Activity;

import com.wqh.livedatabug.MainActivity;
import com.wqh.livedatabug.interfase.BindClassLayout;
import com.wqh.livedatabug.interfase.BindView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 作者: Wang on 2020/1/5 09:38
 * 寄语：加油！相信自己可以！！！
 */


public class BingViewUtils {
    private BingViewUtils() {

    }

//    public static void init(Object o){
//
//        initLayout((Activity) o);
//    }

    /**
     * 绑定布局文件
     *
     * @param activity
     */
    public static void initLayout(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        if (aClass.isAnnotationPresent(BindClassLayout.class)) {
            BindClassLayout annotation = aClass.getAnnotation(BindClassLayout.class);
            int layoutId = annotation.value();
            try {
                Method setContentView = aClass.getMethod("setContentView", int.class);
                setContentView.setAccessible(true);
                setContentView.invoke(activity, layoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void initView(Activity mainActivity) {
        Class<? extends Activity> aClass = mainActivity.getClass();

        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(BindView.class)) {
                BindView annotation = field.getAnnotation(BindView.class);
                int value = annotation.value();
                try {
                    Method findViewById = aClass.getMethod("findViewById", int.class);
                    findViewById.setAccessible(true);
                    Object invoke = findViewById.invoke(mainActivity, value);
                    field.setAccessible(true);
                    field.set(mainActivity, invoke);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

