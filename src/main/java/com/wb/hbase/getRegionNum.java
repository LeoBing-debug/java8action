package com.wb.hbase;

import org.apache.hadoop.hbase.util.Bytes;

/**
 * @author wb
 * @date 2019/12/23 22:56
 */
public class getRegionNum {

    public static void main(String[] args) {

        byte[][] bs = regionKey(6);
        for (int i = 0; i < 5 ; i++) {
            System.out.println(Bytes.toString(bs[i]));
        }

    }

    //分区键的数据类型就是数组，所以需要这么写
    public static byte[][] regionKey(int regionCount){
        //这个声明就是 string [] = new String[2]
        byte[][] bs = new byte[regionCount-1][];

        for (int i = 0; i <regionCount-1 ; i++) {
            bs[i]= Bytes.toBytes(i+"|");
        }
        return bs;
    }

    public static String regionNum(String rowkey,int regionCount){
        int regionNum;

        int hash = rowkey.hashCode();

        //(regionCount)&(regionCount-1)验证是否是2的倍数
        if(regionCount >0 && ((regionCount)&(regionCount-1))==0){
            regionNum=hash & (regionCount-1);
        }else {
            regionNum=hash % (regionCount-1);
        }

        return regionNum+"_"+rowkey;
    }




}
