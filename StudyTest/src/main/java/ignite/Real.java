package ignite;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/4/3 14:28
 * @see
 **/
public class Real {

    @QuerySqlField(index = true)
    int Id;

    @QuerySqlField
    String onlineserver;

    @QuerySqlField
    String onlinestatus;

    @QuerySqlField
    String ActiveTIME;

    @QuerySqlField
    String REALSTATUS;

    @QuerySqlField
    String REALCAN;

}
