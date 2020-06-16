package com.wb.thread;

/**
 * @author wb
 * @date 2020/1/3 22:01
 *
 * 分析：
 * 1、是否是多线程：是，一个生产者/一个消费者
 * 2、是否共享数据：是，店员（产品）
 * 3、处理线程安全问题：
 * 4、如果解决线程安全问题：三种方法
 * 5、是否涉及到线程通信：是
 */
public class ThreadProductConsumer {
    public static void main(String [] args){
        Clerk clerk = new Clerk();

        Producer producer = new Producer(clerk);
        Consumer consumer = new Consumer(clerk);

        producer.start();
        consumer.start();
    }
}
class Clerk{

    private int productNum =0;

    //synchronized 同步监视器都是Clerk这个this对象，所以可以做到线程安全
    public synchronized void producerProduct() {
        if(productNum<20){
            productNum++;
            System.out.println(Thread.currentThread().getName()+"开始生产"+productNum);

            notify();

        }else {
            //等待
            try {
                wait();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public synchronized void consumerProduct() {
        if(productNum>0){
            System.out.println(Thread.currentThread().getName()+"开始消费"+productNum);
            productNum--;

            notify();

        }else {
            //等待
            try {
                wait();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}

class Producer extends Thread{
    private Clerk clerk;

    public  Producer(Clerk clerk){
        this.clerk=clerk;
    }

    @Override
    public void run() {
        System.out.println("生产者"+Thread.currentThread().getName());
        while(true){

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.producerProduct();
        }
    }
}

class Consumer extends Thread{
    private Clerk clerk;

    public  Consumer(Clerk clerk){
        this.clerk=clerk;
    }

    @Override
    public void run() {
        System.out.println("消费者"+Thread.currentThread().getName());
        while(true){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.consumerProduct();
        }
    }
}