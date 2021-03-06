package com.zam.mybatis2.service;

import com.zam.mybatis2.dao.db1.DbcMd5RecordMapper;
import com.zam.mybatis2.dao.db1.TerminalMapper1;
import com.zam.mybatis2.dao.db2.TerminalMapper2;
import com.zam.mybatis2.entity.DbcMd5RecordEntity;
import com.zam.mybatis2.entity.DbcUsedRecord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MyService {

    @Resource
    private DbcMd5RecordMapper dbcMd5RecordMapper;

    @Resource
    private TerminalMapper1 terminalMapper1;

    @Resource
    private TerminalMapper2 terminalMapper2;

    @Resource(name = "db1JdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Resource(name = "db1NamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedTemplate;

    /**
     * Spring的AOP即声明式事务管理默认是针对unchecked exception回滚。
     * 开启了事务，方法执行完了才提交。
     * 统一方法内，先插入，后查找，数据包含了未提交的部分。
     */
    @Transactional
    public void test() {
//        testMapper.insertUser("zam", "hero");
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select * from zam");
        int i = 1 / 0;
    }

    public int test1() {
        dbcMd5RecordMapper.selectFinishedDbcRecord();
        return dbcMd5RecordMapper.selectFinishedDbcRecord().size();
    }

    public DbcMd5RecordEntity test2(String md5) {
        return dbcMd5RecordMapper.selectByMd52(md5);
    }

    public List<DbcMd5RecordEntity> test3(String md5) {
        return dbcMd5RecordMapper.selectByMd53(md5);
    }

    public List<Map<String, Object>> test4() {
        terminalMapper2.getTerminalInfo();
        return terminalMapper1.getTerminalInfo();
    }

    public void test5() {
        DbcUsedRecord dbcUsedRecord = new DbcUsedRecord();
        dbcUsedRecord.setTerminalCode(222);
        dbcUsedRecord.setMd5Code("D4EAA0A41821E44085CE7CD77657E4D1");
        dbcUsedRecord.setStatus(1);
        dbcUsedRecord.setTravelTime(LocalDateTime.now());
        terminalMapper1.insertDbcUsedRecord(dbcUsedRecord);
    }

    public void test6() {
        String dataType = null;
        try {
            dataType = jdbcTemplate.getDataSource().getConnection().getMetaData().getDatabaseProductName();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DbcUsedRecord dbcUsedRecord = new DbcUsedRecord();
        dbcUsedRecord.setTerminalCode(222);
        dbcUsedRecord.setMd5Code("89A9DC8E21462A6215904B45FA0F5990");
        dbcUsedRecord.setStatus(1);
        dbcUsedRecord.setTravelTime(LocalDateTime.parse("2020-03-16 10:03:02", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        Map<String,Object> params = new HashMap<String,Object>();
//        String sql = "SELECT dbcid FROM incom_dbcmd5record WHERE md5code=:md5";
//        params.put("md5", dbcUsedRecord.getMd5Code());
//        List<Object> list = namedTemplate.queryForList(sql, params);
        String sql = null;
        List<Object> params = new ArrayList<Object>();
        if (dataType.equalsIgnoreCase("mysql")) {
            sql = "select recid,md5code,begintime from incom_dbcterminluserecord where begintime=(select max(begintime) from incom_dbcterminluserecord where terminalcode=? and begintime<=?)";
        } else {
            sql = "select recid,md5code,begintime from (select row_number() over(order by begintime desc) row_num,recid,md5code,begintime from incom_dbcterminluserecord where terminalcode=? and begintime<=? order by begintime desc) where row_num=1";
        }
        params.add(dbcUsedRecord.getTerminalCode());
        params.add(Timestamp.valueOf(dbcUsedRecord.getTravelTime()));
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, params.toArray());
        DbcUsedRecord maxDbcUsedRecord = null;
        if (list.size() > 0) {
            maxDbcUsedRecord = new DbcUsedRecord();
            if (dataType.equalsIgnoreCase("mysql")) {
                maxDbcUsedRecord.setStatus((Integer) list.get(0).get("recid"));
            } else {
                maxDbcUsedRecord.setStatus(((BigDecimal) list.get(0).get("recid")).intValue());
            }
            maxDbcUsedRecord.setMd5Code((String) list.get(0).get("md5code"));
            maxDbcUsedRecord.setTravelTime(((Timestamp) list.get(0).get("begintime")).toLocalDateTime());
        }
        if (dataType.equalsIgnoreCase("mysql")) {
            sql = "select recid,md5code,begintime from incom_dbcterminluserecord where begintime=(select min(begintime) from incom_dbcterminluserecord where terminalcode=? and begintime>?)";
        } else {
            sql = "select recid,md5code,begintime from (select row_number() over(order by begintime asc) row_num,recid,md5code,begintime from incom_dbcterminluserecord where terminalcode=? and begintime>? order by begintime asc) where row_num=1";
        }
        params.clear();
        params.add(dbcUsedRecord.getTerminalCode());
        params.add(Timestamp.valueOf(dbcUsedRecord.getTravelTime()));
        list = jdbcTemplate.queryForList(sql, params.toArray());
        DbcUsedRecord minDbcUsedRecord = null;
        if (list.size() > 0) {
            minDbcUsedRecord = new DbcUsedRecord();
            if (dataType.equalsIgnoreCase("mysql")) {
                minDbcUsedRecord.setStatus((Integer) list.get(0).get("recid"));
            } else {
                minDbcUsedRecord.setStatus(((BigDecimal) list.get(0).get("recid")).intValue());
            }
            minDbcUsedRecord.setMd5Code((String) list.get(0).get("md5code"));
            minDbcUsedRecord.setTravelTime(((Timestamp) list.get(0).get("begintime")).toLocalDateTime());
        }
        if ((maxDbcUsedRecord == null && minDbcUsedRecord == null) ||
                (minDbcUsedRecord == null && maxDbcUsedRecord != null && !maxDbcUsedRecord.getMd5Code().equals(dbcUsedRecord.getMd5Code())) ||
                (minDbcUsedRecord != null && maxDbcUsedRecord != null && !maxDbcUsedRecord.getMd5Code().equals(dbcUsedRecord.getMd5Code())
                        && !minDbcUsedRecord.getMd5Code().equals(dbcUsedRecord.getMd5Code()) && !maxDbcUsedRecord.getMd5Code().equals(dbcUsedRecord.getMd5Code()))
        ) {
            if (dataType.equalsIgnoreCase("mysql")) {
                sql = "insert into incom_dbcterminluserecord (dbcid,terminalcode,begintime,md5code,createdon) values (?,?,?,?,sysdate())";
            } else {
                sql = "insert into incom_dbcterminluserecord (recid,dbcid,terminalcode,begintime,md5code,createdon) values (seq_dbcterminluserecord.nextval,?,?,?,?,sysdate)";
            }
            List<Map<String, Object>> myList = jdbcTemplate.queryForList("select dbcid from incom_dbcmd5record where md5code='" + dbcUsedRecord.getMd5Code() + "'");
            params.clear();
            if (dataType.equalsIgnoreCase("mysql")) {
                params.add(myList.size() > 0 ? (Integer) myList.get(0).get("dbcid") : 0);
            } else {
                params.add(myList.size() > 0 ? ((BigDecimal) myList.get(0).get("dbcid")).intValue() : 0);
            }
            params.add(dbcUsedRecord.getTerminalCode());
            params.add(Timestamp.valueOf(dbcUsedRecord.getTravelTime()));
            params.add(dbcUsedRecord.getMd5Code());
        } else if (maxDbcUsedRecord != null && maxDbcUsedRecord.getMd5Code().equals(dbcUsedRecord.getMd5Code()) && maxDbcUsedRecord.getTravelTime().isAfter(dbcUsedRecord.getTravelTime())) {
            if (dataType.equalsIgnoreCase("mysql")) {
                sql = "update incom_dbcterminluserecord t set t.begintime=?, remark=concat('修改<=传入时间的最大时间',sysdate()) where t.recid=?";
            } else {
                sql = "update incom_dbcterminluserecord t set t.begintime=?, remark='修改<=传入时间的最大时间'||to_char(sysdate,'yyyy-MM-dd HH24:mi:ss') where t.recid=?";
            }
            params.clear();
            params.add(Timestamp.valueOf(dbcUsedRecord.getTravelTime()));
            params.add(maxDbcUsedRecord.getStatus());
        } else if (minDbcUsedRecord != null && minDbcUsedRecord.getMd5Code().equals(dbcUsedRecord.getMd5Code()) && minDbcUsedRecord.getTravelTime().isAfter(dbcUsedRecord.getTravelTime())) {
            if (dataType.equalsIgnoreCase("mysql")) {
                sql = "update incom_dbcterminluserecord t set t.begintime=?, remark=concat('修改>传入时间的最大时间',sysdate()) where t.recid=?";
            } else {
                sql = "update incom_dbcterminluserecord t set t.begintime=?, remark='修改>传入时间的最大时间'||to_char(sysdate,'yyyy-MM-dd HH24:mi:ss') where t.recid=?";
            }
            params.clear();
            params.add(Timestamp.valueOf(dbcUsedRecord.getTravelTime()));
            params.add(minDbcUsedRecord.getStatus());
        }
        if (!sql.contains("select")) {
            jdbcTemplate.update(sql, params.toArray());
        }
    }

}
