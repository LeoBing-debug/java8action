package com.wb.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author wb
 * @date 2020/1/4 12:02
 *
 * 实现线程的第三种方式：实现callable接口
 *
 * 如何理解实现callable接口的方式，创建多线程比实现runnable接口更强大？
 * 1、call方法可以有返回值
 * 2、call方法可以抛出异常
 * 3、callable支持泛型
 *
 */
public class ThreadCallable{

    public static void main(String [] args){
        //3、创建对象
        ThreadCallableTest thread = new ThreadCallableTest();
        //4、将callable接口实现类对象放到futuretask构造器中，创建futuretask对象
        FutureTask futureTask = new FutureTask(thread);
        //5、将futuretask对象放到thread构造器中，启动
        new Thread(futureTask).start();
        try {
            //6、从futuretaks结构中获取call返回值
            Object o = futureTask.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}

/**
 * 1、实现callable接口
 */
class ThreadCallableTest implements Callable{

    //2、重写call方法，返回值
    public Object call() throws Exception {

        int sum=0;
        for (int i = 1; i <= 100 ; i++) {
            if(i%2==0){
                System.out.println(sum+=i);
            }
        }
        return sum;
    }
}