//package redis.jedis;
//
//import com.alibaba.fastjson.JSONObject;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * 功能描述: 单机客户端
// *
// * @author zhangam
// * @time 2018-9-25下午5:03:58
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//public class JedisTest {
//
//    //服务器IP地址
//    private static String ADDR = "192.168.2.111";
//    //端口
//    private static int PORT = 6379;
//    //密码
//    private static String AUTH = "";
//
//    public static JedisPool jedisPool = null;
//
//    static {
//        JedisPoolConfig config = new JedisPoolConfig();
///*        //连接实例的最大连接数
//        config.setMaxTotal(1024);
//        // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值是8
//        config.setMaxIdle(100);
//        //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时
//        config.setMaxWaitMillis(1000);
//        //borrow是否要进行验证操作，如果赋值为true，则得到的jedis实例肯定是可用的
//        config.setTestOnBorrow(true);
//        //return是否要进行验证操作，如果赋值为true，则返回jedis连接池的jedis实例肯定是可用的
//        config.setTestOnReturn(true);
//        //连接池耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时，会抛出超时异常，默认为true
//        config.setBlockWhenExhausted(true);*/
//        jedisPool = new JedisPool(config, ADDR, PORT, 2000);
//    }
//
//    /**
//     * 功能描述:
//     *
//     * @param args
//     * @author zhangam
//     * @time 2018-9-25下午5:03:59
//     */
//    public static void main(String[] args) {
///*        Map map = new HashMap();
//        map.put("onlineserver", "0d1f85ec-9e03-4bcd-9f17-192168121cs2");
//        map.put("onlinestatus", "1");
//        map.put("ActiveTIME", "2019-03-28 15:51:22");
//        map.put("REALSTATUS", "{\"VT\":0.8600000000000001,\"TE\":\"2019-03-28 15:50:43.000\",\"SDC\":11,\"ISO\":0,\"GSS\":23,\"IRC\":0,\"TRT\":2862,\"ITD\":0,\"IHL\":0,\"CAN\":0,\"CDE\":0,\"TC\":70591033,\"V2\":0.0,\"ITL\":0,\"TSE\":1,\"RET\":39,\"PWR\":0,\"ICE\":1,\"SMD\":1,\"IVHF\":0,\"GLS\":32,\"UPDATED\":\"2019-03-28 15:50:43\",\"LCK\":3,\"TFW\":0,\"GPS\":0,\"DST\":0}");
//        map.put("REALCAN", "0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0");
//
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        for (int i = 0; i < 5; i++) {
//            cachedThreadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    Jedis redis.jedis = jedisPool.getResource();
//                    redis.jedis.select(1);
////        redis.jedis.flushDB();
////                    long time1 = System.currentTimeMillis();
////                    for (int i = 0; i < 100000; i++) {
////                        redis.jedis.hmset("redis" + i, map);
////                    }
//                    long time2 = System.currentTimeMillis();
////                    System.out.println("redis写入耗时(ms)-------------" + (time2 - time1));
//                    for (int i = 0; i < 100000; i++) {
//                        redis.jedis.hgetAll("redis" + i).get("REALCAN");
//                    }
//                    long time3 = System.currentTimeMillis();
//                    System.out.println("redis读取耗时(ms)-------------" + (time3 - time2));
//                    //        jedisPool.returnResource(redis.jedis);
////        jedisPool.destroy();
//                }
//
//            });
//        }*/
//
//        int tn = Integer.parseInt(args[0]);
//        int dn = Integer.parseInt(args[1]);
//        int type = Integer.parseInt(args[2]);
//
//        String str = "{\"gearDriveForce\":0,\"batteryConsistencyDifferenceAlarm\":0,\"soc\":98,\"socJumpAlarm\":0,\"caterpillaringFunction\":0,\"satNum\":11,\"socLowAlarm\":0,\"chargingGunConnectionState\":0,\"minTemperatureSubSystemNum\":1,\"chargedElectronicLockStatus\":0,\"maxVoltageBatteryNum\":68,\"terminalTime\":\"2019-01-04 13:04:48\",\"singleBatteryOverVoltageAlarm\":0,\"otherFaultCount\":0,\"vehicleStorageDeviceOvervoltageAlarm\":0,\"brakeSystemAlarm\":0,\"serverTime\":\"2019-01-04 13:05:41.894\",\"vin\":\"LS5A3AJC4JB011211\",\"rechargeableStorageDevicesFaultCount\":0,\"remainedPowerMile\":247,\"driveMotorTemperatureAlarm\":0,\"dcdcStatusAlarm\":0,\"gearBrakeForce\":1,\"lat\":24.704769999999996,\"driveMotorFaultCodes\":\"123456\",\"deviceType\":\"rmu\",\"vehicleSpeed\":0,\"lng\":118.42937999999998,\"chargingTimeExtensionReason\":0,\"gpsTime\":\"2019-01-04 13:04:48\",\"nevChargeSystemVoltageDtoList\":[{\"currentBatteryStartNum\":1,\"batteryVoltage\":[4.176,4.158,4.172,4.18,4.173,4.179,4.175,4.177,4.176,4.176,4.174,4.174,4.18,4.179,4.176,4.178,4.178,4.181,4.179,4.177,4.177,4.179,4.177,4.177,4.175,4.179,4.181,4.178,4.178,4.179,4.178,4.181,4.175,4.176,4.183,4.179,4.178,4.178,4.148,4.175,4.178,4.18,4.179,4.177,4.178,4.177,4.176,4.142,4.175,4.174,4.176,4.178,4.172,4.17,4.174,4.176,4.176,4.177,4.174,4.179,4.179,4.18,4.18,4.179,4.177,4.177,4.18,4.184,4.18,4.154,4.176,4.18,4.178,4.181,4.181,4.179,4.174,4.177,4.177,4.18,4.178,4.18,4.179,4.179],\"chargeSystemVoltage\":350.80002,\"currentBatteryCount\":84,\"batteryCount\":84,\"childSystemNum\":1,\"chargeSystemCurrent\":0.10003662}]}";
//        JSONObject jo = JSONObject.parseObject(str);
//        Iterator<String> it = jo.keySet().iterator();
//        Map<String, String> map = new HashMap<String, String>();
//        while (it.hasNext()) {
//            String key = it.next();
//            map.put(key, jo.getString(key));
//        }
//
//        Jedis jedis = jedisPool.getResource();
//        jedis.select(5);
//        if(type == 1 || type == 3) {
//            jedis.flushDB();
//        }
//        jedisPool.returnResource(jedis);
//
//        // 可缓存线程池
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        for (int i = 0; i < tn; i++) {
//            cachedThreadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    Jedis jedis = jedisPool.getResource();
//                    jedis.select(5);
//                    switch (type) {
//                        case 1:
//                            long time1 = System.currentTimeMillis();
//                            for (int j = 0; j < dn; j++) {
//                                jedis.set(Thread.currentThread().getName() + "string" + j, str);
//                            }
//                            long time2 = System.currentTimeMillis();
//                            System.out.println("线程"
//                                    + Thread.currentThread().getName()
//                                    + "--Jedis--存字符串--耗时(ms)-------------"
//                                    + (time2 - time1));
//                            break;
//                        case 2:
//                            long time3 = System.currentTimeMillis();
//                            for (int j = 0; j < dn; j++) {
//                                String strstr = jedis.get(Thread.currentThread().getName() + "string" + j);
////                                System.out.println(strstr);
//                            }
//                            long time4 = System.currentTimeMillis();
//                            System.out.println("线程"
//                                    + Thread.currentThread().getName()
//                                    + "--Jedis--取字符串--耗时(ms)-------------"
//                                    + (time4 - time3));
//                            break;
//                        case 3:
//                            long time5 = System.currentTimeMillis();
//                            for (int j = 0; j < dn; j++) {
//                                jedis.hmset(Thread.currentThread().getName() + "hash" + j, map);
//                            }
//                            long time6 = System.currentTimeMillis();
//                            System.out.println("线程"
//                                    + Thread.currentThread().getName()
//                                    + "--Jedis--存哈希--耗时(ms)-------------"
//                                    + (time6 - time5));
//                            break;
//                        case 4:
//                            long time7 = System.currentTimeMillis();
//                            for (int j = 0; j < dn; j++) {
//                                Map<String, String> mapmap = jedis.hgetAll(Thread.currentThread().getName() + "hash" + j);
////                                System.out.println(mapmap.get("nevChargeSystemTemperatureDtoList"));
//                            }
//                            long time8 = System.currentTimeMillis();
//                            System.out.println("线程"
//                                    + Thread.currentThread().getName()
//                                    + "--Jedis--取哈希--耗时(ms)-------------"
//                                    + (time8 - time7));
//                            break;
//                    }
//                    jedisPool.returnResource(jedis);
//                    jedisPool.destroy();
//                }
//            });
//        }
//        cachedThreadPool.shutdown();
//
///*        Jedis redis.jedis = new Jedis("192.168.2.30", 6379); //直连 多线程下不安全
//        redis.jedis.close(); //关闭连接
//        redis.jedis = jedisPool.getResource(); //连接池连 多线程下安全
//        redis.jedis.select(10); //选择数据区域
//        redis.jedis.flushDB(); //清除区域的数据
//        jedisPool.returnResource(redis.jedis);//放回连接池
//        jedisPool.destroy(); //关闭连接池
//
//        // 字符串
//        redis.jedis.set("string", "zamzamzam");
//        System.out.println(redis.jedis.get("string"));
//
//        // 哈希
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("1", "A");
//        map.put("2", "B");
//        redis.jedis.hmset("hash", map);
//        Map<String, String> userMap = redis.jedis.hgetAll("hash");
//        for (Map.Entry<String, String> item : userMap.entrySet()) {
//            System.out.println(item.getKey() + " : " + item.getValue());
//        }
//
//        // List
//		redis.jedis.lpush("list", "A"); //往左插入
//		redis.jedis.lpush("list", "B");
//		redis.jedis.lpush("list", "C");
//		System.out.println(redis.jedis.lpop("list"));
//		System.out.println(redis.jedis.lrange("list", 0, -1));
//		redis.jedis.rpush("list", "spring"); //往右插入
//		redis.jedis.rpush("list", "struts");
//		redis.jedis.rpush("list", "hibernate");
//		System.out.println(redis.jedis.rpop("list"));
//		System.out.println(redis.jedis.lrange("list", 0, -1));
//
//        // Set
//        redis.jedis.sadd("set", "L", "B", "J");
//        redis.jedis.sadd("set", "zam");
//        Set<String> sets = redis.jedis.smembers("set");
//        Iterator<String> iterator = sets.iterator();
//        while (iterator.hasNext()) {
//            String it = iterator.next();
//            System.out.println(it);
//        }
//        redis.jedis.srem("set", "L");
//        sets = redis.jedis.smembers("set");
//        iterator = sets.iterator();
//        while (iterator.hasNext()) {
//            String it = iterator.next();
//            System.out.println(it);
//        }*/
//
//
////		/*
////		 * 事物:redis的事务很简单，他主要目的是保障，一个client发起的事务中的命令可以连续的执行，而中间不会插入其他client的命令。
////		 */
////		Transaction tx = redis.jedis.multi();
////		// 一堆操作
////		tx.exec();
//
//        /*
//         * 管道:有时，我们需要采用异步方式，一次发送多个指令，不同步等待其返回结果。这样可以取得非常好的执行效率。这就是管道.
//         */
////		Pipeline pipeline = redis.jedis.pipelined();
////		// 一堆操作
////		pipeline.syncAndReturnAll();
//
//        /*
//         * 分布式直连同步调用
//         */
////		List<JedisShardInfo> shards1 = Arrays.asList(new JedisShardInfo(
////				"localhost", 6379), new JedisShardInfo("localhost", 6380));
////		ShardedJedis sharding1 = new ShardedJedis(shards1);
////		// 一堆操作
////		sharding1.disconnect();
//
//        /*
//         * 分布式直连异步调用
//         */
////		List<JedisShardInfo> shards2 = Arrays.asList(new JedisShardInfo(
////				"localhost", 6379), new JedisShardInfo("localhost", 6380));
////		ShardedJedis sharding2 = new ShardedJedis(shards2);
////		ShardedJedisPipeline pp1 = sharding2.pipelined();
////		// 一堆操作
////		pp1.syncAndReturnAll();
////		sharding2.disconnect();
//
//        /*
//         * 分布式连接池同步调用
//         */
////		List<JedisShardInfo> shards3 = Arrays.asList(new JedisShardInfo(
////				"localhost", 6379), new JedisShardInfo("localhost", 6380));
////		ShardedJedisPool pool1 = new ShardedJedisPool(new JedisPoolConfig(),
////				shards3);
////		ShardedJedis one1 = pool1.getResource();
////		// 一堆操作
////		pool1.returnResource(one1);
////		pool1.destroy();
//
//        /*
//         * 分布式连接池异步调用
//         */
////		List<JedisShardInfo> shards4 = Arrays.asList(new JedisShardInfo(
////				"localhost", 6379), new JedisShardInfo("localhost", 6380));
////		ShardedJedisPool pool2 = new ShardedJedisPool(new JedisPoolConfig(),
////				shards4);
////		ShardedJedis one2 = pool2.getResource();
////		ShardedJedisPipeline pp2 = one2.pipelined();
////		// 一堆操作
////		pp2.syncAndReturnAll();
////		pool2.returnResource(one2);
////		pool2.destroy();
//
//    }
//
//}
