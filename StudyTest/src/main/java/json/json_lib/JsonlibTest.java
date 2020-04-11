package json.json_lib;

import com.alibaba.fastjson.JSON;
import json.Person;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 * 默认序列化null值对应key
 *
 * <dependency>
 * <groupId>net.sf.json-lib</groupId>
 * <artifactId>json-lib</artifactId>
 * <version>2.4</version>
 * <classifier>jdk15</classifier>
 * </dependency>
 *
 * @author zhangam
 * @time 2020/1/10 10:44
 * @see
 **/
public class JsonlibTest {

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
        JSONObject jsonObject = JSONObject.fromObject(string);
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
        JSONObject jsonObject2 = JSONObject.fromObject(myMap);
        System.out.println(jsonObject2.toString());

        //java对象转json字符串
        Person person = new Person();
//        person.setId(23);
//        person.setName("lbj");
        person.setHeight(2.06);
        person.setLike(new String[]{"basketball", "music"});
        person.setKing(true);
//        person.setMap(map);
        person.setList(list);
        JSONObject jsonObject3 = JSONObject.fromObject(person);
        System.out.println(jsonObject3.toString());

        //json对象转字符串
        System.out.println(jsonObject.toString());

        //json对象转map
        System.out.println(JSON.parseObject(jsonObject2.toString(), Map.class));

        //json对象转java对象
        System.out.println(JSONObject.toBean(jsonObject3, Person.class));

    }

}
