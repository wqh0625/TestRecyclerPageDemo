package com.wqh.livedatabug.interfase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者: Wang on 2020/1/5 09:24
 * 寄语：加油！相信自己可以！！！
 */

@Retention(RetentionPolicy.RUNTIME)// 作用域：作用于运行时注解
@Target(ElementType.FIELD)// 作用域：作用于全局变量
public @interface BindView {
    int value();
}
