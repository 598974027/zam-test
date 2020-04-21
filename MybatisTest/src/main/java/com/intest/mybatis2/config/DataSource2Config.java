package com.intest.mybatis2.config;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@MapperScan(value = "com.intest.mybatis2.dao.db2", sqlSessionFactoryRef = "db2SqlSessionFactory")
public class DataSource2Config {

    @Autowired
    private MybatisProperties mybatisProperties;

    /**
     * 生成数据源
     *
     * @Primary 注解声明为默认数据源
     */
    @Bean(name = "db2DataSource")
    @ConfigurationProperties(prefix = "db2.spring.datasource")
    public DataSource businessDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建JdbcTemplate
     */
    @Bean(name = "db2JdbcTemplate")
    public JdbcTemplate businessJdbcTemplate(@Qualifier("db2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 创建SqlSessionFactory
     */
    @Bean(name = "db2SqlSessionFactory")
    public SqlSessionFactory businessSqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(mybatisProperties.resolveMapperLocations());
        VendorDatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        Properties properties = new Properties();
        Map<String, String> map = new HashMap<>();
        map.put("Oracle", "oracle");
        map.put("MySQL", "mysql");
        properties.putAll(map);
        databaseIdProvider.setProperties(properties);
        sqlSessionFactoryBean.setDatabaseIdProvider(databaseIdProvider);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 创建SqlSessionTemplate
     */
    @Bean(name = "db2SqlSessionTemplate")
    public SqlSessionTemplate businessSqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 配置事务管理
     */
    @Bean(name = "db2TransactionManager")
    public DataSourceTransactionManager businessTransactionManager(@Qualifier("db2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
