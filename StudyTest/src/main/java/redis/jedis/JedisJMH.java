package redis.jedis;

import com.alibaba.fastjson.JSONObject;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/4/25 9:59
 * @see
 **/
@BenchmarkMode({Mode.AverageTime, Mode.Throughput})//用于指定性能数据的格式。主要用的有吞吐量或者平均时间
@OutputTimeUnit(TimeUnit.MILLISECONDS)//用于指定输出的时间单位
@Warmup(iterations = 1)//用于对预热迭代进行配置，包括iterations配置预热次数，time配置预热时间， timeUnit配置时间单位，以及batchSize执行次数
@Measurement(iterations = 5, timeUnit = TimeUnit.MILLISECONDS)//用于对测试迭代进行配置，各参数含义跟Warmup一样
@Threads(100)//用于配置测试时的线程数
@Fork(1)//许开发人员指定所要 Fork 出的 Java 虚拟机的数目
@State(Scope.Thread)//其中因为Jedis是线程不安全的，所以每个线程使用的都是一个单独的Jedis对象
//用于标识程序的状态，其中：Scope.Thread：默认的State，每个测试线程分配一个实例；Scope.Benchmark：所有测试线程共享一个实例，用于测试有状态实例在多线程共享下的性能；Scope.Group：每个线程组共享一个实例；
public class JedisJMH {

    private static final int LOOP = 1;
    private static JedisPool jedisPool = null;
    private Jedis jedis = null;
    private static String str = "{\"gearDriveForce\":0,\"batteryConsistencyDifferenceAlarm\":0,\"soc\":98,\"socJumpAlarm\":0,\"caterpillaringFunction\":0,\"satNum\":11,\"socLowAlarm\":0,\"chargingGunConnectionState\":0,\"minTemperatureSubSystemNum\":1,\"chargedElectronicLockStatus\":0,\"maxVoltageBatteryNum\":68,\"terminalTime\":\"2019-01-04 13:04:48\",\"singleBatteryOverVoltageAlarm\":0,\"otherFaultCount\":0,\"vehicleStorageDeviceOvervoltageAlarm\":0,\"brakeSystemAlarm\":0,\"serverTime\":\"2019-01-04 13:05:41.894\",\"vin\":\"LS5A3AJC4JB011211\",\"rechargeableStorageDevicesFaultCount\":0,\"remainedPowerMile\":247,\"driveMotorTemperatureAlarm\":0,\"dcdcStatusAlarm\":0,\"gearBrakeForce\":1,\"lat\":24.704769999999996,\"driveMotorFaultCodes\":\"123456\",\"deviceType\":\"rmu\",\"vehicleSpeed\":0,\"lng\":118.42937999999998,\"chargingTimeExtensionReason\":0,\"gpsTime\":\"2019-01-04 13:04:48\",\"nevChargeSystemVoltageDtoList\":[{\"currentBatteryStartNum\":1,\"batteryVoltage\":[4.176,4.158,4.172,4.18,4.173,4.179,4.175,4.177,4.176,4.176,4.174,4.174,4.18,4.179,4.176,4.178,4.178,4.181,4.179,4.177,4.177,4.179,4.177,4.177,4.175,4.179,4.181,4.178,4.178,4.179,4.178,4.181,4.175,4.176,4.183,4.179,4.178,4.178,4.148,4.175,4.178,4.18,4.179,4.177,4.178,4.177,4.176,4.142,4.175,4.174,4.176,4.178,4.172,4.17,4.174,4.176,4.176,4.177,4.174,4.179,4.179,4.18,4.18,4.179,4.177,4.177,4.18,4.184,4.18,4.154,4.176,4.18,4.178,4.181,4.181,4.179,4.174,4.177,4.177,4.18,4.178,4.18,4.179,4.179],\"chargeSystemVoltage\":350.80002,\"currentBatteryCount\":84,\"batteryCount\":84,\"childSystemNum\":1,\"chargeSystemCurrent\":0.10003662}]}";
    private static Map<String, String> map;

    static {
//        JedisPoolConfig config = new JedisPoolConfig();
//        jedisPool = new JedisPool(config, "10.7.52.33", 6379, 2000);
        JSONObject jo = JSONObject.parseObject(str);
        Iterator<String> it = jo.keySet().iterator();
        map = new HashMap<String, String>();
        while (it.hasNext()) {
            String key = it.next();
            map.put(key, jo.getString(key));
        }
    }

    @Setup
    public void setup() {
//        redis.jedis = jedisPool.getResource();
        jedis = new Jedis("10.7.52.33", 6379);
        jedis.select(1);
    }

    @TearDown
    public void tearDown() {
    }

    @Benchmark
    public void write() {
        for (int i = 0; i < LOOP; ++i) {
//            redis.jedis.set("redis.jedis", str);
            jedis.hmset("jedis2", map);
        }
    }

    @Benchmark
    public void read() {
        for (int i = 0; i < LOOP; ++i) {
//            redis.jedis.get("redis.jedis");
            jedis.hgetAll("jedis2");
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(JedisJMH.class.getSimpleName())
                .output("redis.jedis.log").build();
        new Runner(options).run();
    }

}