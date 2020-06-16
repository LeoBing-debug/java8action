package com.wb.thread;

/**
 * @author wb
 * @date 2019/12/22 1:15o
 * 1、start():启动当前线程，调用当前线程的run（）
 * 2、run（）：重写方法 crtl+
 * 3、currentThread：返回当前线程
 * 4、getName（）：获取当前线程的名字
 * 5、setName
 * 6、yield：释放当前cpu的执行权
 * 7、join：线程B调在线程A中调用join方法，线程B执行完成前，线程A处于阻塞状态
 * 8、stop：不推荐，强制结束
 * 9、sleep（long ）：
 * 10、isAlive（）
 *
 * 线程优先级：1/5/10
 * getPriority
 * setPriority
 *
 * 线程分类：守护线程（setDaemon方法设置），用户线程
 */
public class ThreadExtends {

    public static void main(String[] args) {
        myThread m1 = new myThread();
        m1.start();
        myThread m2 = new myThread();
        m2.start();
        //通过run方法启动的是函数方法调用，不是多线程
        //m1.run();
/*
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName()+"---main---"+i);
        }

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i <3 ; i++) {
                    System.out.println("---noname---"+Thread.currentThread().getName());
                }
            }
        }.start();*/


    }
}

class myThread extends Thread{

    private static int ticket=100;
    //静态是继承方式线程安全的必备方式

    @Override
    public void run() {
        while(true) {
            // synchronized (object) {
            // synchronized (this) {//this不唯一
            synchronized (myThread.class) {//反射类只会加载一次，唯一
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