package com.wb.thread;

/**
 * @author wb
 * @date 2019/12/22 13:32
 *
 * 1、买票过程中出现重票/错票
 * 2、问题原因：当某个线程操作的时候，操作未完成，其他线程参与进来，线程安全问题
 * 3、解决问题：
 *      1、同步代码块：
 *          synchronized（同步监视器）{
 *              //需要被同步的代码
 *          }
 *          操作共享数据的代码，即为需要被同步的代码
 *          共享数据：多个线程共同操作的变量，ticket
 *          同步监视器：任何一个对象，都一个充当锁。
 *              要求：多个线程必须共用同一把锁。
 *
 *          补充:
 *              在实现runnable接口创建多线程的方式中，可以考虑使用this充当锁
 *              在继承thread类创建多线程的方式中，慎用this充当监视器，可以考虑使用CLASSNAME.class充当锁
 *
 *      2、同步方法：
 *             在实现runnable接口中，同步方法不需要显示声明
 *             非静态类的默认同步监视器是this
 *             静态类的同步方法是当前类本身：当前类本身
 *
 * 5、同步的方式，解决了线程安全问题---优点
 *      操作同步代码的时候，只有一个线程参与，其他线程等待，相当于一个单线程的过程，效率低---局限性
 *
 *
 *
 *  实现锁的方式：
 *  runnable：               thread：
 *      1、自定义Object          1、静态Object
 *      2、this当前类            2、反射类
 *      3、同步方法，默认this     3、静态同步方法，默认当前CLASSNAME.class，即反射
 */
public class ThreadRunnable {

    public static void main(String [] args){
        myRun1 run = new myRun1();
        Thread thread1 = new Thread(run);
        //start方法调用当前线程的run方法
        thread1.setName("线程1");
        thread1.start();

        Thread thread2 = new Thread(run);
        thread2.setName("线程2");
        thread2.start();
    }
}

 class myRun implements Runnable{
    private int ticket=100;
    //Object object = new Object();

    public void run() {

        while(true) {
           // synchronized (object) {
              synchronized (this) {//this代码当前对象，唯一
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
            }
        }
    }
}

class myRun1 implements Runnable{
    private int ticket=100;

    public void run() {

        while(true) {
            show();
        }
    }

    private synchronized void show(){ //同步方法
            if (ticket > 0) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                System.out.println(Thread.currentThread().getName() + ":" + ticket);
                ticket--;

            }
    }
}