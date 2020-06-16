package com.wb.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;


/**
 * 使用api的过程中，学会看源码中对使用的封装，也是一种有意思的事情
 * @author wb
 * @date 2019/12/21 14:30
 */
public class testHbaseApi1 {

    public static void main(String [] args) throws Exception{

        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();

        //admin是用来管理表的
        try {
            admin.getNamespaceDescriptor("wb");
        }catch (NamespaceNotFoundException e){
            NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create("wb").build();
            admin.createNamespace(namespaceDescriptor);
        }

        TableName tableName = TableName.valueOf("wb:wb");
        boolean flag = admin.tableExists(tableName);

        if(flag){
            //表是用来操作数据的
            Table table = connection.getTable(tableName);
            String rowkey="1001";
            Get get = new Get(Bytes.toBytes(rowkey));
            Result result = table.get(get);
            boolean empty = result.isEmpty();

            //数据为空添加数据
            if(empty){
                Put put = new Put(Bytes.toBytes(rowkey));

                String family = "info";
                String column = "name";
                String val = "zhangsan";

                put.addColumn(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes(val));
                table.put(put);
            }else {
                //数据不为空，查询数据
                for (Cell cell :result.rawCells()){
                   System.out.println("family:"+Bytes.toString(CellUtil.cloneFamily(cell)));
                   System.out.println("rowkey:"+Bytes.toString(CellUtil.cloneRow(cell)));
                   System.out.println("column:"+Bytes.toString(CellUtil.cloneQualifier(cell)));
                   System.out.println("value:"+Bytes.toString(CellUtil.cloneValue(cell)));
                }

            }
        }else {
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor("info");
            hTableDescriptor.addFamily(hColumnDescriptor);

            admin.createTable(hTableDescriptor);
        }

    }

}
