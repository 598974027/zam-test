package json.gson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import json.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 * 1.默认不序列化null值对应key
 * 2.若是想序列化，Gson gson = new GsonBuilder().serializeNulls().create();
 *
 * <dependency>
 * <groupId>com.google.code.gson</groupId>
 * <artifactId>gson</artifactId>
 * <version>2.2.4</version>
 * </dependency>
 * <p>
 * 5个注解
 * .@SerializedName:用来自定义序列化和反序列化过程中字段的名字
 * .@Expose:可以对序列化和反序列化单独控制
 * .@Since:进行版本控制，这个使用的很少
 * .@Until:进行版本控制，这个使用的很少
 * .@TypeAdapter:用于接管某种类型的序列化和反序列化过程
 *
 * @author zhangam
 * @time 2020/1/10 10:44
 * @see
 **/
public class GsonTest {

    private static final Gson gson = new Gson();

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
        JsonObject jsonObject = new JsonParser().parse(string).getAsJsonObject();
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
        System.out.println(gson.toJson(myMap));

        //java对象转json字符串
        Person person = new Person();
        person.setId(23);
        person.setName("lbj");
        person.setHeight(2.06);
        person.setLike(new String[]{"basketball", "music"});
        person.setKing(true);
        person.setMap(map);
        person.setList(list);
        JsonObject jsonObject2 = gson.toJsonTree(person).getAsJsonObject();
        System.out.println(jsonObject2.toString());

        //json对象转字符串
        System.out.println(jsonObject.toString());

        //json对象转map
        Map<String, Object> map2 = gson.fromJson(jsonObject, new TypeToken<Map<String, Object>>() {
        }.getType());
        System.out.println(map2);

        //json对象转java对象
        Person person2 = gson.fromJson(jsonObject, Person.class);
        System.out.println(person2);

    }

}
