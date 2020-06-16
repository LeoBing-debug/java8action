package com.wb.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author wb
 * @date 2020/6/15 21:10
 */
public class TestLamda {

    List<Employee> employee = Arrays.asList(
            new Employee("zhangsan",12,33.33),
            new Employee("lisi",13,44.33),
            new Employee("wangwu",14,55.33),
            new Employee("zhaoliu",15,66.33)
    );

    @Test
    public void test1(){
        Collections.sort(employee,(e1,e2)->{
            if(e1.getAge()==e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else {
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });

        for(Employee emp : employee){
            System.out.println(emp.toString());
        }
    }

    @Test
    public void test2(){
        String s = strHandle("  hello  ", (x) -> x.trim());
        System.out.println(s);

    }

    /**
     * lamda 表达式是 接口的实现，面向对象的思想没有改变，是优化了处理过程的环节，减少冗余代码的编写
     *
     *
     * Java8 内置丰富的函数式接口
     * @param string
     * @param myFun
     * @return
     */

    public String strHandle(String string,MyFun myFun){
        return myFun.getValue(string);
    }
}
