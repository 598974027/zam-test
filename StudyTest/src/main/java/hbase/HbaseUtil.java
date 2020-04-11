package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HbaseUtil {

    public static Connection connection = null;

    public static void main(String[] args) {
        getConnection();
//		createTable("bq_zam",  new String[] {"cf"});
//		deleteTable("bq_zam");
        insertCell("bq_zam", "bq_zam_0001", "cf", "c", "{\"SRCD\":\"\",\"TID\":0,\"TRT\":1036825,\"TFW\":348,\"CDE\":2,\"SEQ\":20181204212026545,\"RET\":3813,\"VT\":13.98,\"Lat\":39.7120875,\"Lon\":116.3129995,\"Alt\":58.0,\"GSp\":0.0,\"GDr\":0.0,\"GMi\":46043.7,\"MD\":\"925FD6497A1F78C1C6894BFE45A687F4\",\"Data\":[1.0,1.0,2.0,0.0,0.0,0.0,0.0,2.0,0.0,0.0,1.0,0.0,13.66,8338.0,21.0,7.0,1.0,1.0,0.0,0.0,0.0,0.74,0.36,2.0,180.0,30.0,1.0,0.0,2.0,1.0,1.0,0.0,0.0,1.0,0.0,12.8,13.3,4.0,0.0,93.0,1.0,1.0,1.0,0.0,1.0,0.0,0.0,320.0,0.0,36.9,204.98,204.98,33.0,35.0,8200.0,1.0,51.0,1.0,1.0,1.0,1.0,66.0,32.0,52000.0,0.0,316.0,1.04,317.0,317.0,3823.0,3784.0,10000.0,10000.0,12.0,9.0,0.0,0.0,0.0,0.0,0.0,0.0,64.33,0.0,8210.0,1.0,52.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,111569.0,8514.0,2.0,56.0,0.0,0.0,11.0,11.0,10.0,10.0,10.0,11.0,3.811,3.807,3.796,3.784,3.811,3.805,3.799,3.798,3.795,3.804,3.814,3.799,132.0,12.0,11.0,11.0,12.0,12.0,11.0,3.802,3.798,3.798,3.784,3.792,3.793,3.799,3.789,3.801,3.804,3.802,3.801,132.0,11.0,11.0,12.0,10.0,10.0,11.0,3.795,3.808,3.804,3.795,3.786,3.784,3.802,3.804,3.795,3.804,3.795,3.802,132.0,10.0,11.0,10.0,10.0,11.0,10.0,3.799,3.81,3.81,3.817,3.816,3.799,3.814,3.811,3.802,3.811,3.802,3.805,132.0,9.0,10.0,9.0,10.0,9.0,10.0,3.811,3.817,3.817,3.823,3.81,3.811,3.807,3.808,3.823,3.817,3.822,3.823,132.0,11.0,10.0,9.0,11.0,11.0,9.0,3.789,3.816,3.805,3.813,3.807,3.804,3.79,3.81,3.805,3.811,3.814,3.814,132.0,12.0,12.0,11.0,11.0,11.0,12.0,3.807,3.801,3.79,3.816,3.805,3.813,3.811,3.813,3.805,3.804,3.814,3.814,132.0,3.22,1.82,1.82,3.22,0.0,0.0,132.0,0.0,3.0,0.0,0.0],\"DF\":null,\"TC\":70010157,\"TE\":\"2018-12-04 21:19:17.570\"}");
        insertCell("bq_zam", "bq_zam_0002", "cf", "c", "{\"SRCD\":\"\",\"TID\":0,\"TRT\":1036825,\"TFW\":348,\"CDE\":2,\"SEQ\":20181204212026545,\"RET\":3813,\"VT\":13.98,\"Lat\":0.0,\"Lon\":0,\"Alt\":58.0,\"GSp\":0.0,\"GDr\":0.0,\"GMi\":46043.7,\"MD\":\"925FD6497A1F78C1C6894BFE45A687F4\",\"Data\":[1.0,1.0,2.0,0.0,0.0,0.0,0.0,2.0,0.0,0.0,1.0,0.0,13.66,8338.0,21.0,7.0,1.0,1.0,0.0,0.0,0.0,0.74,0.36,2.0,180.0,30.0,1.0,0.0,2.0,1.0,1.0,0.0,0.0,1.0,0.0,12.8,13.3,4.0,0.0,93.0,1.0,1.0,1.0,0.0,1.0,0.0,0.0,320.0,0.0,36.9,204.98,204.98,33.0,35.0,8200.0,1.0,51.0,1.0,1.0,1.0,1.0,66.0,32.0,52000.0,0.0,316.0,1.04,317.0,317.0,3823.0,3784.0,10000.0,10000.0,12.0,9.0,0.0,0.0,0.0,0.0,0.0,0.0,64.33,0.0,8210.0,1.0,52.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,111569.0,8514.0,2.0,56.0,0.0,0.0,11.0,11.0,10.0,10.0,10.0,11.0,3.811,3.807,3.796,3.784,3.811,3.805,3.799,3.798,3.795,3.804,3.814,3.799,132.0,12.0,11.0,11.0,12.0,12.0,11.0,3.802,3.798,3.798,3.784,3.792,3.793,3.799,3.789,3.801,3.804,3.802,3.801,132.0,11.0,11.0,12.0,10.0,10.0,11.0,3.795,3.808,3.804,3.795,3.786,3.784,3.802,3.804,3.795,3.804,3.795,3.802,132.0,10.0,11.0,10.0,10.0,11.0,10.0,3.799,3.81,3.81,3.817,3.816,3.799,3.814,3.811,3.802,3.811,3.802,3.805,132.0,9.0,10.0,9.0,10.0,9.0,10.0,3.811,3.817,3.817,3.823,3.81,3.811,3.807,3.808,3.823,3.817,3.822,3.823,132.0,11.0,10.0,9.0,11.0,11.0,9.0,3.789,3.816,3.805,3.813,3.807,3.804,3.79,3.81,3.805,3.811,3.814,3.814,132.0,12.0,12.0,11.0,11.0,11.0,12.0,3.807,3.801,3.79,3.816,3.805,3.813,3.811,3.813,3.805,3.804,3.814,3.814,132.0,3.22,1.82,1.82,3.22,0.0,0.0,132.0,0.0,3.0,0.0,0.0],\"DF\":null,\"TC\":70010157,\"TE\":\"2018-12-04 21:19:17.570\"}");
        insertCell("bq_zam", "bq_zam_0003", "cf", "c", "{\"SRCD\":\"\",\"TID\":0,\"TRT\":1036825,\"TFW\":348,\"CDE\":2,\"SEQ\":20181204212026545,\"RET\":3813,\"VT\":13.98,\"Lat\":0.0,\"Lon\":0,\"Alt\":58.0,\"GSp\":0.0,\"GDr\":0.0,\"GMi\":46043.7,\"MD\":\"925FD6497A1F78C1C6894BFE45A687F4\",\"Data\":[],\"DF\":null,\"TC\":70010157,\"TE\":\"2018-12-04 21:19:17.570\"}");
//		selectCell("bq_zam", "bq_zam_0001", "cf", "c");
//		deleteCell("bq_zam", "bq_zam_0001", "cf", "c");
//		deleteRow("bq_zam", "bq_zam_0001");
        closeConnection();
    }

    public static void getConnection() {
        Configuration cfg = HBaseConfiguration.create();
//		cfg.set("hbase.rootdir", "hdfs://192.168.2.110/hbase");
        cfg.set("hbase.zookeeper.quorum", "192.168.2.110,192.168.2.111,192.168.2.112");
        cfg.set("hbase.zookeeper.property.clientPort", "2181");
        try {
            connection = ConnectionFactory.createConnection(cfg);
        } catch (IOException e) {
            System.out.println("get connection error:" + e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            if (null != connection)
                connection.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("close connection error:" + e.getMessage());
        }
    }

    public static void createTable(String str, String[] cols) throws IOException {
        TableName tableName = TableName.valueOf(str);
        if (connection.getAdmin().tableExists(tableName)) {
            System.out.println("table already exists");
        } else {
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            for (String col : cols) {
                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(col);
                hTableDescriptor.addFamily(hColumnDescriptor);
            }
            connection.getAdmin().createTable(hTableDescriptor);
            System.out.println("create table success");
        }
    }

    public static void deleteTable(String tableName) throws IOException {
        TableName tn = TableName.valueOf(tableName);
        if (connection.getAdmin().tableExists(tn)) {
            connection.getAdmin().disableTable(tn);
            connection.getAdmin().deleteTable(tn);
            System.out.println("delete table success");
        } else {
            System.out.println("table not exists");
        }
    }

    public static void insertCell(String tableName, String rowkey, String colFamily, String col, String val) {
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            Put put = new Put(Bytes.toBytes(rowkey));
            put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(col), Bytes.toBytes(val));
            table.put(put);
            table.close();
        } catch (Exception e) {
            System.out.println("insert error:" + e.getMessage());
        }
    }

    public static void insertPut(String tableName, Put put) {
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            table.put(put);
            table.close();
        } catch (Exception e) {
            System.out.println("insert error:" + e.getMessage());
        }
    }

    public static void insertList(String tableName, List<Put> list) {
        try {
            HTable table = (HTable) connection.getTable(TableName.valueOf(tableName));
            table.setAutoFlush(false);
            table.setWriteBufferSize(15 * 1024 * 1024);
            table.put(list);
            table.flushCommits();
            table.close();
        } catch (Exception e) {
            System.out.println("insert error:" + e.getMessage());
        }
    }

    public static Cell selectCell(String tableName, String rowkey, String colFamily, String col) {
        Cell re = null;
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            Get get = new Get(Bytes.toBytes(rowkey));
            get.addFamily(Bytes.toBytes(colFamily));
            get.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(col));
            Result result = table.get(get);
            table.close();
            if (!result.isEmpty()) {
                re = result.rawCells()[0];
            }
        } catch (Exception e) {
            System.out.println("select error:" + e.getMessage());
        }
        return re;
    }

    public static Cell[] selectRow(String tableName, String rowkey) {
        Cell[] re = null;
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            Get get = new Get(Bytes.toBytes(rowkey));
            Result result = table.get(get);
            table.close();
            if (result.isEmpty()) {
                re = result.rawCells();
            }
        } catch (Exception e) {
            System.out.println("select error:" + e.getMessage());
        }
        return re;
    }

    public static List<Cell[]> selectRowList(String tableName, List<String> rowkeyList) throws IOException {
        List<Cell[]> re = new ArrayList<Cell[]>();
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            List<Get> getList = new ArrayList();
            for (String rowkey : rowkeyList) {
                Get get = new Get(Bytes.toBytes(rowkey));
                getList.add(get);
            }
            Result[] results = table.get(getList);
            table.close();
            for (Result result : results) {
                if (result.isEmpty()) {
                    re.add(result.rawCells());
                }
            }
        } catch (Exception e) {
            System.out.println("select error:" + e.getMessage());
        }
        return re;
    }

    public static void deleteCell(String tableName, String rowkey, String colFamily, String col) {
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            Delete delete = new Delete(Bytes.toBytes(rowkey));
            delete.addFamily(Bytes.toBytes(colFamily));
            delete.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(col));
            table.delete(delete);
            table.close();
        } catch (Exception e) {
            System.out.println("delete error:" + e.getMessage());
        }
    }

    public static void deleteRow(String tableName, String rowkey) {
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            Delete delete = new Delete(Bytes.toBytes(rowkey));
            table.delete(delete);
            table.close();
        } catch (Exception e) {
            System.out.println("delete error:" + e.getMessage());
        }
    }

    public static void deleteRowList(String tableName, List<String> rowkeyList) throws IOException {
        try {
            Table table = connection.getTable(TableName.valueOf(tableName));
            List<Delete> deleteList = new ArrayList<Delete>();
            for (String rowkey : rowkeyList) {
                Delete delete = new Delete(Bytes.toBytes(rowkey));
                deleteList.add(delete);
            }
            table.delete(deleteList);
            table.close();
        } catch (Exception e) {
            System.out.println("delete error:" + e.getMessage());
        }
    }

}