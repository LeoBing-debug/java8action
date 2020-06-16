package com.wb.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wb
 * @date 2020/1/4 13:08
 * 创建 线程的第四种方法，线程池
 * 好处：
 * 1、提高响应速度：减少创建和销毁
 * 2、降低资源消耗：
 * 3、便于线程管理
 *
 */
public class ThreadPool {

    public static void main(String [] args){
        //1、创建连接池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //设置线程属性
        ThreadPoolExecutor executor = (ThreadPoolExecutor) executorService;
        executor.setCorePoolSize(11);

        runNum runNum = new runNum();
        //2、指定线程操作
        executorService.execute(runNum);//runable
        //executorService.submit();//callable
        //3、关闭
        executorService.shutdown();
    }
}

class runNum implements Runnable{

    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i%2==0){
                System.out.println(i);
            }
        }
    }

}