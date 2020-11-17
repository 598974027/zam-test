package json;

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
public class Person2 {

    private Integer id;

    private Boolean man;

    private String name;

    private Double height;

    private Date time;

    private String[] like;

    private Map map;

    private List list;

}
