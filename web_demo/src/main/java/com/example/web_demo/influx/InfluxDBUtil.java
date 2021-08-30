package com.example.web_demo.influx;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述: InfluxDBConnect
 *
 * @author zhangaomin
 * @time 2021/8/30 9:44
 **/
public class InfluxDBUtil {
    private final Logger logger = LoggerFactory.getLogger(InfluxDBUtil.class);

    private String userName;
    private String password;
    private String url;
    public String database;

    /**
     * 数据保存策略
     */
    private String retentionPolicy;
    /**
     * 数据保存策略中数据保存时间
     */
    private String retentionPolicyTime;

    /**
     * InfluxDB实例
     */
    private InfluxDB influxDB;

    public InfluxDBUtil(String userName, String password, String url, String database, String retentionPolicy, String retentionPolicyTime) {
        this.userName = userName;
        this.password = password;
        this.url = url;
        this.database = database;
        //autogen默认的数据保存策略
        this.retentionPolicy = retentionPolicy == null || "".equals(retentionPolicy) ? "autogen" : retentionPolicy;
        this.retentionPolicyTime = retentionPolicyTime == null || "".equals(retentionPolicy) ? "30d" : retentionPolicyTime;
        this.influxDB = influxDbBuild();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getRetentionPolicy() {
        return retentionPolicy;
    }

    public void setRetentionPolicy(String retentionPolicy) {
        this.retentionPolicy = retentionPolicy;
    }

    public String getRetentionPolicyTime() {
        return retentionPolicyTime;
    }

    public void setRetentionPolicyTime(String retentionPolicyTime) {
        this.retentionPolicyTime = retentionPolicyTime;
    }

    public InfluxDB getInfluxDB() {
        return influxDB;
    }

    public void setInfluxDB(InfluxDB influxDB) {
        this.influxDB = influxDB;
    }

    /**
     * 连接时序数据库获得InfluxDB
     **/
    public InfluxDB influxDbBuild() {
        if (influxDB == null) {
            influxDB = InfluxDBFactory.connect(url);
//            influxDB = InfluxDBFactory.connect(url, userName, password);
            influxDB.query(new Query("CREATE DATABASE " + database));
            influxDB.setDatabase(database);
        }
        return influxDB;
    }

    /**
     * 设置数据保存策略
     */
    public void createRetentionPolicy() {
        String command = String.format("CREATE RETENTION POLICY \"%s\" ON \"%s\" DURATION %s REPLICATION %s DEFAULT",
                retentionPolicy, database, retentionPolicyTime, 1);
        this.query(command);
    }

    /**
     * 查询
     */
    public QueryResult query(String command) {
        return influxDB.query(new Query(command, database));
    }

    /**
     * 插入
     */
    public void insert(String measurement, Map<String, String> tags, Map<String, Object> fields) {
        Point.Builder builder = Point.measurement(measurement);
        //纳秒时会出现异常信息：partial write: points beyond retention policy dropped=1
        //builder.time(System.nanoTime(), TimeUnit.NANOSECONDS);
        builder.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        builder.tag(tags);
        builder.fields(fields);
        logger.info("influxDB insert data:[{}]", builder.build().toString());
        influxDB.write(database, "", builder.build());
    }
}
