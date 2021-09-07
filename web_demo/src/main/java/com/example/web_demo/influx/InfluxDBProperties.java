//package com.example.web_demo.influx;
//
//import org.hibernate.validator.constraints.URL;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//
//import javax.validation.constraints.NotBlank;
//
///**
// * 功能描述: InfluxDBProperties
// *
// * @author zhangaomin
// * @time 2021/8/30 9:40
// **/
//@Configuration
//@ConfigurationProperties(prefix = "spring.influx")
//public class InfluxDBProperties {
//    @URL
//    private String url;
//    @NotBlank
//    private String userName;
//    @NotBlank
//    private String password;
//    @NotBlank
//    private String database;
//    @NotBlank
//    private String retentionPolicy;
//    @NotBlank
//    private String retentionPolicyTime;
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getDatabase() {
//        return database;
//    }
//
//    public void setDatabase(String database) {
//        this.database = database;
//    }
//
//    public String getRetentionPolicy() {
//        return retentionPolicy;
//    }
//
//    public void setRetentionPolicy(String retentionPolicy) {
//        this.retentionPolicy = retentionPolicy;
//    }
//
//    public String getRetentionPolicyTime() {
//        return retentionPolicyTime;
//    }
//
//    public void setRetentionPolicyTime(String retentionPolicyTime) {
//        this.retentionPolicyTime = retentionPolicyTime;
//    }
//}
