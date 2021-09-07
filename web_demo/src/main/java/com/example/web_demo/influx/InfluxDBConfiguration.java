//package com.example.web_demo.influx;
//
//import org.influxdb.dto.QueryResult;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 功能描述: InfluxDBConfiguration
// *
// * @author zhangaomin
// * @time 2021/8/30 9:41
// **/
//@Configuration
//public class InfluxDBConfiguration {
//    private final Logger logger = LoggerFactory.getLogger(InfluxDBConfiguration.class);
//
//    @Autowired
//    private InfluxDBProperties influxDBProperties;
//
//    @Bean
//    public InfluxDBUtil getInfluxDBConnect() {
//        InfluxDBUtil influxDB = new InfluxDBUtil(influxDBProperties.getUserName(), influxDBProperties.getPassword(),
//                influxDBProperties.getUrl(), influxDBProperties.getDatabase(), influxDBProperties.getRetentionPolicy(),
//                influxDBProperties.getRetentionPolicyTime());
//        influxDB.influxDbBuild();
//        influxDB.createRetentionPolicy();
//        logger.info("init influxdb::[{}]", influxDBProperties);
//        QueryResult queryResult = influxDB.query("select * from zam");
//        return influxDB;
//    }
//}
//
