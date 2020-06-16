package com.wb.java8;

/**
 * @author wb
 * @date 2020/6/15 23:57
 */

/**
 * 注解修饰的接口只有一个函数
 *
 * 称为 函数式接口
 */
@FunctionalInterface
public interface MyFun {

    String getValue(String string);

}
