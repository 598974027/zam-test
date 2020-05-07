package json.json_lib;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 * 序列化时默认序列化null值对应key
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
        //字符串->Json对象->Java对象
        String string = "{\"id\":1,\"man\":true,\"name\":\"intest\",\"height\":2.06,\"like\":[\"basketball\",\"music\"],\"map\":{\"map1\":\"map1\",\"map2\":\"map2\",\"map3\":null},\"list\":[\"list1\",\"list2\",\"list3\"]}";
//        JSONObject jsonObject = JSONObject.fromObject(string);
//        Person person = (Person) JSONObject.toBean(jsonObject, Person.class);
//        System.out.println(person);

        //Java对象->Json对象->字符串
//        Person person = new Person();
//        person.setMan(true);
//        person.setId(23);
//        person.setName("lbj");
//        person.setHeight(2.06);
//        person.setLike(new String[]{"basketball", "music"});
//        Map map = new HashMap();
//        map.put("map1", null);
//        map.put("map2", "map2");
//        map.put("map3", "map3");
//        person.setMap(map);
//        List list = new ArrayList();
//        list.add("list1");
//        list.add("list2");
//        list.add("list3");
//        person.setList(list);
//        JSONObject jsonObject = JSONObject.fromObject(person);
//        System.out.println(jsonObject.toString());

        //Map->Json对象
        Map myMap = new HashMap();
        myMap.put("id", 1);
        myMap.put("man", true);
        myMap.put("name", "intest");
        myMap.put("height", 2.06);
        myMap.put("like", new String[]{"basketball", "music"});
        Map map = new HashMap();
        map.put("map1", null);
        map.put("map2", "map2");
        map.put("map3", "map3");
        myMap.put("map", map);
        List list = new ArrayList();
        list.add("list1");
        list.add("list2");
        list.add("list3");
        myMap.put("list", list);
        JSONObject jsonObject = JSONObject.fromObject(myMap);
        System.out.println(jsonObject);

        //Json对象->Map
        Map mapData1 = (Map) JSONObject.toBean(jsonObject, Map.class);
        Map mapData2 = jsonObject;
        System.out.println(mapData1);
        System.out.println(mapData2);
    }

}
