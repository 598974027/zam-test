package json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 * 1.序列化时默认不序列化null值对应key
 * 2.若是想序列化，可设置Gson gson = new GsonBuilder().serializeNulls().create() ；
 * .@SerializedName:用来自定义序列化和反序列化过程中字段的名字
 * .@Expose:可以对序列化和反序列化单独控制
 * .@Since:进行版本控制，这个使用的很少
 * .@Until:进行版本控制，这个使用的很少
 * .@TypeAdapter:用于接管某种类型的序列化和反序列化过程
 *
 * <dependency>
 * <groupId>com.google.code.gson</groupId>
 * <artifactId>gson</artifactId>
 * <version>2.2.4</version>
 * </dependency>
 *
 * @author zhangam
 * @time 2020/1/10 10:44
 * @see
 **/
public class GsonTest {

    public static void main(String[] args) {
        //字符串->Json对象->Java对象
//        String string = "{\"id\":1,\"man\":true,\"name\":\"intest\",\"height\":2.06,\"like\":[\"basketball\",\"music\"],\"map\":{\"map1\":\"map1\",\"map2\":\"map2\",\"map3\":null},\"list\":[\"list1\",\"list2\",\"list3\"]}";
//        Gson gson = new GsonBuilder().create();
//        Person person = gson.fromJson(string, Person.class);
//        System.out.println(person);

        //Java对象->字符串
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
//        Gson gson = new GsonBuilder().create();
//        System.out.println(gson.toJson(person));
//        System.out.println(gson.toJsonTree(person).getAsJsonObject().toString());

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
        Gson gson = new GsonBuilder().create();
        JsonObject jsonObject = gson.toJsonTree(myMap).getAsJsonObject();
        System.out.println(jsonObject);

        //Json对象->Map
        Gson gson2 = new GsonBuilder().create();
        Map<String, Object> mapData = gson2.fromJson(jsonObject, new TypeToken<Map<String, Object>>() {
        }.getType());
        System.out.println(mapData);
    }

}
