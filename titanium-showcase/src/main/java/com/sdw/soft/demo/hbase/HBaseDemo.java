/**
 * @author shangyd
 * @Date 2015年5月16日 上午9:24:12
 * Copyright (c) 2015
 **/
package com.sdw.soft.demo.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HBaseDemo {

	private static final Logger logger = LoggerFactory.getLogger(HBaseDemo.class);
	
	private static Configuration configuration;

	static {
		configuration = HBaseConfiguration.create();
		configuration.set("hbase.zookeeper.property.clientport", "2181");
		configuration.set("hbase.zookeeper.quorum", "namenode,datanode1,datanode2");
		configuration.set("hbase.master", "namenode:60000");
		configuration.set("hbase.rpc.timeout", "120");
	}
	
	public static void main(String[] args){
		String tableName = "post";
		String row = UUID.randomUUID().toString();
//		createTable(tableName);
//		insertData(tableName, row, "content", "userid", "tom@163.com");
//		insertData(tableName, row, "content", "body", "hello habse!");
//		insertData(tableName, row, "article", "title", "test hbase demo");
//		deleteData(tableName, "270e24d5-7973-4e7b-9750-674e85f99945");
//		queryAll(tableName);
		queryByRowkey(tableName, "c3384561-7706-42de-81e8-b0661d45037c");
	}
	public static void createTable(String tableName){
		logger.info("create hbase table start ... ...");
		try {
			HBaseAdmin hbaseAdmin = new HBaseAdmin(configuration);
			if(hbaseAdmin.tableExists(tableName)){
				hbaseAdmin.disableTable(tableName);
				hbaseAdmin.deleteTable(tableName);
				logger.info("hbase table ["+tableName+"] exists ,delete ... ...");
			}
			HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
			tableDescriptor.addFamily(new HColumnDescriptor("content"));
			tableDescriptor.addFamily(new HColumnDescriptor("article"));
			hbaseAdmin.createTable(tableDescriptor);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		logger.info("create hbase table end ... ...");
	}
	/**
	 * 插入数据
	 * @param tableName 表名
	 * @param row 主键
	 * @param columnFamily 列族
	 * @param column 列
	 * @param value 值
	 */
	public static void insertData(String tableName,String row,String columnFamily,String column,String value){
		logger.info("hbase table ["+tableName+"] start to insert data ... ...");
		try {
			HTable table = new HTable(configuration, tableName);
			Put put = new Put(Bytes.toBytes(row));
			put.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(value));
			table.put(put);
		} catch (IOException e) {
			e.printStackTrace();
			logger.info(e.getMessage(),e);
		}
		logger.info("hbase table ["+tableName+"] insert data end ... ...");		
	}
	/**
	 * 删除数据
	 * @param tableName
	 * @param row
	 */
	public static void deleteData(String tableName,String... row){
		logger.info("hbase table [" +tableName+ "] start to delete data ... ...");
		try {
			HTable table = new HTable(configuration, tableName);
			List<Delete> list = new ArrayList<Delete>();
			for(String rowkey : row){
				Delete del = new Delete(Bytes.toBytes(rowkey));
				list.add(del);
			}
			table.delete(list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage(),e);
		}
		logger.info("hbase table ["+tableName+"] delete data end ... ...");
		
	}
	
	/**
	 * 删除表
	 * @param tableName
	 */
	public static void dropTable(String tableName){
		logger.info("hbase delete table starat ... ...");
		try {
			HBaseAdmin hbaseAdmin = new HBaseAdmin(configuration);
			hbaseAdmin.disableTable(tableName);
			hbaseAdmin.deleteTable(tableName);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage(),e);
		}
		logger.info("hbase delete table end ... ...");
	}
	/**
	 * 查询指定表的全部数据
	 * @param tableName
	 */
	public static void queryAll(String tableName){
		try {
			HTable table = new HTable(configuration, tableName);
			ResultScanner scanner = table.getScanner(new Scan());
			for(Result rs : scanner){
				logger.info("获取rowkey:" + new String(rs.getRow()));
				for(KeyValue kv : rs.raw()){
					logger.info("列:" + new String(kv.getFamily()) + " =========> " + new String(kv.getValue()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
	}
	
	/**
	 * 根据rowkey查询数据
	 * @param tableName
	 * @param rowkey
	 */
	public static void queryByRowkey(String tableName,String rowkey){
		logger.info("hbase query data by rowkey start ... ...");
		try {
			HTable table = new HTable(configuration, tableName);
			Get get = new Get(Bytes.toBytes(rowkey));
			Result result = table.get(get);
			logger.info("获得rowkey:" + new String(result.getRow()));
			for(KeyValue kv : result.raw()){
				logger.info("列:" + new String(kv.getFamily()) + " =========> " + new String(kv.getValue()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("hbase query data by rowkey end ... ...");
	}
	
}
