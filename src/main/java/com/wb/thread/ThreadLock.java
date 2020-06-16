package com.wb.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wb
 * @date 2020/1/3 20:31
 *
 *  synchronized 和 Lock 的异同？
 *  相同：两者都可以解决线程安全问题
 *  不同：synchronized 机制在执行完同步代码后，自动释放同步监视器
 *        Lock 需要手动启动 lock 手动释放 unlock
 *  优先使用顺序：Lock>同步代码块>同步方法
 *
 *  解决线程安全问题有几种方法？ 以上
 */
public class ThreadLock  {

   public static void main(String [] args){
       thread t = new thread();
       Thread thread = new Thread(t);
       thread.start();
   }
}

class thread implements Runnable{
    private int ticket = 100;
    private ReentrantLock lock = new ReentrantLock();

    public void run() {
        while (true){
            try {
                lock.lock();//jdk5.0

                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + ticket);
                    ticket--;
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
