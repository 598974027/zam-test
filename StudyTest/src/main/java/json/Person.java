package json;

import lombok.Data;

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

    private int id;

    private boolean man;

    private String name;

    private double height;

    private String[] like;

    private Map map;

    private List list;

}
