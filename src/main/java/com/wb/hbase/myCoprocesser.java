package com.wb.hbase;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;

import java.io.IOException;

/**
 * @author wb
 * @date 2019/12/22 21:19
 *
 * 1、hbase协处理器是hbase自己的东西
 * 2、重写方法postPut，类似的方法还有prePut/doPut
 * 3、实现逻辑
 *      增加原表数据
 *      同时增加触发表数据
 * 4、将项目打包（依赖）后上传到hbase中，让hbase可以识别处理器，放到hbase/lib下
 * 5、添加之后，重启hbase，观察组件是否正常运行
 */
public class myCoprocesser extends BaseRegionObserver {

    //写入数据之后
    @Override
    public void postPut(ObserverContext<RegionCoprocessorEnvironment> e, Put put
            , WALEdit edit, Durability durability) throws IOException {
        Table table = e.getEnvironment().getTable(TableName.valueOf("wb:wb"));

        table.put(put);

        table.close();
    }
}
