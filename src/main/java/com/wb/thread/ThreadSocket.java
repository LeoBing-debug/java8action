package com.wb.thread;

/**
 * @author wb
 * @date 2020/1/3 21:29
 *
 * 线程通信涉及到的三个方法:
 * wait()：线程进入阻塞状态，释放线程监视器
 * notify()：唤醒被wait的线程，如果多个线程，那就唤醒优先级高的线程
 * notifyAll()：唤醒所有线程
 *
 * 三个方法定义在Object类中
 *
 *  问题：wait 和 sleep 的异同
 *  相同：调用线程都会进入阻塞状态
 *  不同：
 *  1、两个方法声明类不同，wait在Object类中，sleep在Thread类中
 *  2、调用不同，sleep可以在任何需求场景下使用，wait必须在同步代码块或者同步方法中调用
 *  3、同步监视器：在同步代码/方法中，sleep不会释放锁，wait会释放锁。
 *
 */
public class ThreadSocket {
    public static void main(String [] args){
        Number number = new Number();
        Thread thread1 = new Thread(number);
        Thread thread2 = new Thread(number);
        thread1.start();
        thread2.start();
    }
}

/**
 * 线程交替打印
 */
class Number implements Runnable{
    private int number=1;

    public void run() {
        while (true){
            synchronized (this) {

                //唤醒睡眠的线程
                notify();

                if (number <= 100) {
                    System.out.println(Thread.currentThread().getName() + "线程，数据：" + number);
                    number++;

                    try {
                        //线程进入阻塞状态
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    break;
                }
            }
        }
    }
}
