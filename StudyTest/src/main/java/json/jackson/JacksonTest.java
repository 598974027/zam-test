package json.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 * 1.序列化时默认序列化null值对应key
 * 2.想要不序列化，实体上加 @JsonInclude(Include.NON_NULL)
 * Include.Include.ALWAYS 默认
 * Include.NON_DEFAULT 属性为默认值不序列化
 * Include.NON_EMPTY 属性为空（“”）或者为 NULL都不序列化
 * Include.NON_NULL 属性为NULL不序列化
 * 有很多注解，列举常用注解
 * .@JsonIgnore: 用来告诉Jackson在处理时忽略该注解标注的java pojo属性
 * .@JsonIgnoreProperties: @JsonIgnoreProperties和@JsonIgnore的作用相同，都是告诉Jackson该忽略哪些属性，不同之处是@JsonIgnoreProperties是类级别的，并且可以同时指定多个属性
 * .@JsonIgnoreType: 标注在类上，当其他类引用该类时，该属性将被忽略
 * .@JsonProperty: 用来对属性的序列化/反序列化，可以用来避免遗漏属性，同时提供对属性名称重命名
 * .@JsonPropertyOrder: 被用来指明当序列化时需要对属性做排序
 * .@JsonFormat: 日期时间格式化
 *
 * <dependency>
 * <groupId>com.fasterxml.jackson.core</groupId>
 * <artifactId>jackson-databind</artifactId>
 * <version>2.9.1</version>
 * </dependency>
 *
 * @author zhangam
 * @time 2020/1/10 10:44
 * @see
 **/
public class JacksonTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        String string = "{\"id\":23,\"name\":\"lbj\",\"height\":2.06,\"like\":[\"basketball\",\"music\"],\"man\":true,\"map\":{\"map3\":\"map3\",\"map2\":\"map2\",\"map1\":\"map1\"},\"list\":[\"list1\",\"list2\",\"list3\"]}";
        Map map = new HashMap();
        map.put("map1", "map1");
        map.put("map2", "map2");
        map.put("map3", "map3");
        List list = new ArrayList();
        list.add("list1");
        list.add("list2");
        list.add("list3");

        //json字符串转json对象
        JsonNode jsonNode = mapper.readTree(string);
        System.out.println(jsonNode);

        //map转json字符串
        Map myMap = new HashMap();
        myMap.put("id", 23);
        myMap.put("name", "lbj");
        myMap.put("height", 2.06);
        myMap.put("like", new String[]{"basketball", "music"});
        myMap.put("man", true);
        myMap.put("map", map);
        myMap.put("list", list);
        System.out.println(mapper.writeValueAsString(myMap));

        //java对象转json字符串
        Person person = new Person();
        person.setId(23);
        person.setName("lbj");
        person.setHeight(2.06);
        person.setLike(new String[]{"basketball", "music"});
        person.setMan(true);
        person.setMap(map);
        person.setList(list);
        System.out.println(mapper.writeValueAsString(person));

        //json对象转字符串
        System.out.println(jsonNode.toString());

        //json对象转map
        System.out.println(mapper.readValue(jsonNode.toString(), Map.class));

        //json对象转java对象
        System.out.println(mapper.readValue(jsonNode.toString(), Person.class));

    }

}
