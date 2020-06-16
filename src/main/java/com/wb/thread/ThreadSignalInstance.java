package com.wb.thread;

/**
 * @author wb
 * @date 2020/1/1 19:29
 * 单例模式下的线程，懒汉式
 */
public class ThreadSignalInstance {

}

class Bank{
    private Bank(){};

    private static Bank instance=null; //共享数据

    public static Bank getInstance(){
        //效率稍差
        /*synchronized (Bank.class) {
            if (instance == null) {
                instance = new Bank();
            }
            return instance;
        }*/
        //效率更高
        if(instance==null){
            synchronized (Bank.class) {
                if (instance == null) {
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}
