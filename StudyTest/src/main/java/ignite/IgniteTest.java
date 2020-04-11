package ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

import javax.cache.Cache;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/4/1 20:06
 * @see
 **/
public class IgniteTest {

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("onlineserver", "0d1f85ec-9e03-4bcd-9f17-192168121cs2");
        map.put("onlinestatus", "1");
        map.put("ActiveTIME", "2019-03-28 15:51:22");
        map.put("REALSTATUS", "{\"VT\":0.8600000000000001,\"TE\":\"2019-03-28 15:50:43.000\",\"SDC\":11,\"ISO\":0,\"GSS\":23,\"IRC\":0,\"TRT\":2862,\"ITD\":0,\"IHL\":0,\"CAN\":0,\"CDE\":0,\"TC\":70591033,\"V2\":0.0,\"ITL\":0,\"TSE\":1,\"RET\":39,\"PWR\":0,\"ICE\":1,\"SMD\":1,\"IVHF\":0,\"GLS\":32,\"UPDATED\":\"2019-03-28 15:50:43\",\"LCK\":3,\"TFW\":0,\"GPS\":0,\"DST\":0}");
        map.put("REALCAN", "0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0");
        Real t = new Real();
        t.onlineserver = "0d1f85ec-9e03-4bcd-9f17-192168121cs2";
        t.onlinestatus = "1";
        t.ActiveTIME = "2019-03-28 15:51:22";
        t.REALSTATUS = "{\"VT\":0.8600000000000001,\"TE\":\"2019-03-28 15:50:43.000\",\"SDC\":11,\"ISO\":0,\"GSS\":23,\"IRC\":0,\"TRT\":2862,\"ITD\":0,\"IHL\":0,\"CAN\":0,\"CDE\":0,\"TC\":70591033,\"V2\":0.0,\"ITL\":0,\"TSE\":1,\"RET\":39,\"PWR\":0,\"ICE\":1,\"SMD\":1,\"IVHF\":0,\"GLS\":32,\"UPDATED\":\"2019-03-28 15:50:43\",\"LCK\":3,\"TFW\":0,\"GPS\":0,\"DST\":0}";
        t.REALCAN = "0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0";
        TcpDiscoverySpi spi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        ipFinder.setAddresses(Arrays.asList("192.168.1.103:47500..47509"));
        spi.setIpFinder(ipFinder);
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setDiscoverySpi(spi);
        cfg.setClientMode(true);
        Ignite ignite = Ignition.start(cfg);
        //销毁缓存
//        ignite.destroyCache("TEST");

        CacheConfiguration<Integer, Real> ccfg = new CacheConfiguration<Integer, Real>();
        ccfg.setName("TEST");
        ccfg.setCacheMode(CacheMode.PARTITIONED);
        ccfg.setIndexedTypes(Integer.class, Real.class);
        IgniteCache<Integer, Real> cache = ignite.getOrCreateCache(ccfg);
        //清空缓存
//        cache.clear();

//        long time1 = System.currentTimeMillis();
//        for (int j = 0; j < 100000; j++) {
//            t.Id = j + 1;
//            cache.put(j, t);
//            cache.putAsync(j, t);
//        }
//        String s = "";
//        long time2 = System.currentTimeMillis();
//        System.out.println("ignite写入耗时(ms)-------------" + (time2 - time1));
//        for (int j = 0; j < 100000; j++) {
//            s = cache.get(j).REALCAN;
//        }

        //使用SqlQuery进行普通查询
//        SqlQuery<Integer, Real> sqlQuery = new SqlQuery(Real.class, " id <=50000 ");
//        List<Cache.Entry<Integer, Real>> list = cache.query(sqlQuery).getAll();
//        System.out.println(list.size());

        //使用SqlFieldsQuery进行域查询
//        SqlFieldsQuery query = new SqlFieldsQuery("select * from Real where id <=100000 ");
//        List<List<?>> result = cache.query(query).getAll();
//        System.out.println(result.size());
//        long time3 = System.currentTimeMillis();
//        System.out.println("ignite读取耗时(ms)-------------" + (time3 - time2));

//        cache.close();
//        ignite.close();
    }

}