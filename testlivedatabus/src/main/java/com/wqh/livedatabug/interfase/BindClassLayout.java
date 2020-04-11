package com.wqh.livedatabug.interfase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者: Wang on 2020/1/5 09:47
 * 寄语：加油！相信自己可以！！！
 */

/**
 * 绑定布局文件
 */

@Retention(RetentionPolicy.CLASS)// 放到类名上
@Target(ElementType.TYPE)
public @interface BindClassLayout {
    int value();
}
