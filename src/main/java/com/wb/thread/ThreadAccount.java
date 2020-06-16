package com.wb.thread;

/**
 * @author wb
 * @date 2020/1/3 21:03
 */
public class ThreadAccount {
    public static void main(String [] args){
        account acc = new account(0);
        customer cus1 = new customer(acc);
        customer cus2 = new customer(acc);
        cus1.start();
        cus2.start();
    }
}

class account{
    private double balance;

    public account(double balance) {
        this.balance=balance;
    }

    public synchronized void deposit(double amt){ //不需要静态，synchronized 的 this 是 acc ，是唯一的
        if(amt>0){

            balance+=amt;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"存钱成功，余额为："+balance);
        }
    }
}


class customer extends Thread{

    private account acc;

    public customer(account acc){
        this.acc=acc;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {

            acc.deposit(1000);

        }
    }
}