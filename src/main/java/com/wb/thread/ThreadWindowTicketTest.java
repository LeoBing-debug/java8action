package com.wb.thread;

/**
 * @author wb
 * @date 2019/12/22 14:13
 * 存在线程安全问题，待解决
 * 两种实现方式：
 *  1、继承Thread，重写run方法，调用start时候，会启动当前线程的run方法，实现线程自定义
 *  2、实现runnable，实现run方法，有参构造函数将runnable传到Thread类中进行初始化，
 *      调用start方法时，判断 runnable是否为空，不为空，则执行实现的run方法
 *
 *  两种方式本质上都是执行自定义的run方法：
 *      开发中优先选择runnable借口
 *      1、实现方式没有类的单继承的局限性
 *      2、实现的方式更适合处理多线程数据共享
 */
public class ThreadWindowTicketTest  {
    public static void main(String[] args) {

        //runnable 三个窗口100
        windowImpl window = new windowImpl();
        Thread thread1 = new Thread(window);
        Thread thread2 = new Thread(window);
        Thread thread3 = new Thread(window);

        //extends 三个窗口100
        Thread threadExtend1 = new windowExtends();
        Thread threadExtend2 = new windowExtends();
        Thread threadExtend3 = new windowExtends();
        threadExtend1.start();
        threadExtend2.start();
        threadExtend3.start();
    }
}

//runnable方式实现抢票
class windowImpl implements Runnable{
    private int ticket = 100;
    public void run() {
        while (true){
            if(ticket>0){
                System.out.println(Thread.currentThread().getName()+"==="+ticket);
                ticket--;
            }else {
                break;
            }
        }
    }
}

//extends方式实现抢票
class windowExtends extends Thread{
    //static保证抢票是100张，线程共享方法区，需要static修饰保证100
    private static int ticket = 100;
    @Override
    public void run() {
        while (true){
            if(ticket>0){
                System.out.println(Thread.currentThread().getName()+"==="+ticket);
                ticket--;
            }else {
                break;
            }
        }
    }
}
