package json;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2020/1/10 11:22
 * @see
 **/
@Data
public class Person {

    private Integer id;

    private Boolean man;

    @JSONField(serialize = false)
    private String name;

    private Double height;

    private transient Double size;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    private String[] like;

    private Map map;

    private List list;

}
