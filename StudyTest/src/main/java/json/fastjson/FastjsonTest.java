package json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import json.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 * 1.默认不序列化null值对应key
 * 2.若想序列化，JSONObject.toJSONString(Object, SerializerFeature.WriteMapNullValue);
 * QuoteFieldNames———-输出key时是否使用双引号,默认为true
 * WriteMapNullValue——–是否输出值为null的字段,默认为false
 * WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
 * WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
 * WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null
 * WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
 *
 * <dependency>
 * <groupId>com.alibaba</groupId>
 * <artifactId>fastjson</artifactId>
 * <version>1.2.58</version>
 * </dependency>
 *
 * @author zhangam
 * @time 2020/1/10 10:44
 * @see
 **/
public class FastjsonTest {

    public static void main(String[] args) {

        String string = "{\"id\":23,\"name\":\"lbj\",\"height\":2.06,\"like\":[\"basketball\",\"music\"],\"king\":true,\"map\":{\"map3\":\"map3\",\"map2\":\"map2\",\"map1\":\"map1\"},\"list\":[\"list1\",\"list2\",\"list3\"]}";
        Map map = new HashMap();
        map.put("map1", "map1");
        map.put("map2", "map2");
        map.put("map3", "map3");
        List list = new ArrayList();
        list.add("list1");
        list.add("list2");
        list.add("list3");

        //json字符串转json对象
        JSONObject jsonObject = JSONObject.parseObject(string);
        System.out.println(jsonObject);

        //map转json字符串
        Map myMap = new HashMap();
        myMap.put("id", 23);
        myMap.put("name", "lbj");
        myMap.put("height", 2.06);
        myMap.put("like", new String[]{"basketball", "music"});
        myMap.put("king", true);
        myMap.put("map", map);
        myMap.put("list", list);
        JSONObject jsonObject2 = JSONObject.parseObject(JSON.toJSONString(myMap));
        System.out.println(jsonObject2);

        //java对象转json字符串
        Person person = new Person();
        person.setId(23);
        person.setName("lbj");
        person.setHeight(2.06);
        person.setLike(new String[]{"basketball", "music"});
        person.setKing(true);
        person.setMap(map);
        person.setList(list);
        System.out.println(JSONObject.toJSON(person));

        //json对象转字符串
        System.out.println(jsonObject.toJSONString());

        //json对象转map
        System.out.println(JSONObject.toJavaObject(jsonObject, Map.class));

        //json对象转java对象
        System.out.println(JSONObject.toJavaObject(jsonObject, Person.class));

    }

}
