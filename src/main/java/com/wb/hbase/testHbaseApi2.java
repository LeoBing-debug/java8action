package com.wb.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;

/**
 * 使用api的过程中，学会看源码中对使用的封装，也是一种有意思的事情
 * @author wb
 * @date 2019/12/21 14:30
 */
public class testHbaseApi2 {

    public static void main(String [] args) throws Exception{


        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();

        TableName tableName = TableName.valueOf("default:t1");

        /*if(admin.tableExists(tableName)){
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        }*/

    }

}
