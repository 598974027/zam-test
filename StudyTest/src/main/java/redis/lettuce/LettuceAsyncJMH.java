package redis.lettuce;

import com.alibaba.fastjson.JSONObject;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/4/25 10:30
 * @see
 **/
@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 1)
@Measurement(iterations = 5, timeUnit = TimeUnit.MILLISECONDS)
@Threads(100)
@Fork(1)
@State(Scope.Benchmark)
public class LettuceAsyncJMH {

    private static final int LOOP = 1;
    private RedisAsyncCommands commands = null;
    private static String str = "{\"gearDriveForce\":0,\"batteryConsistencyDifferenceAlarm\":0,\"soc\":98,\"socJumpAlarm\":0,\"caterpillaringFunction\":0,\"satNum\":11,\"socLowAlarm\":0,\"chargingGunConnectionState\":0,\"minTemperatureSubSystemNum\":1,\"chargedElectronicLockStatus\":0,\"maxVoltageBatteryNum\":68,\"terminalTime\":\"2019-01-04 13:04:48\",\"singleBatteryOverVoltageAlarm\":0,\"otherFaultCount\":0,\"vehicleStorageDeviceOvervoltageAlarm\":0,\"brakeSystemAlarm\":0,\"serverTime\":\"2019-01-04 13:05:41.894\",\"vin\":\"LS5A3AJC4JB011211\",\"rechargeableStorageDevicesFaultCount\":0,\"remainedPowerMile\":247,\"driveMotorTemperatureAlarm\":0,\"dcdcStatusAlarm\":0,\"gearBrakeForce\":1,\"lat\":24.704769999999996,\"driveMotorFaultCodes\":\"123456\",\"deviceType\":\"rmu\",\"vehicleSpeed\":0,\"lng\":118.42937999999998,\"chargingTimeExtensionReason\":0,\"gpsTime\":\"2019-01-04 13:04:48\",\"nevChargeSystemVoltageDtoList\":[{\"currentBatteryStartNum\":1,\"batteryVoltage\":[4.176,4.158,4.172,4.18,4.173,4.179,4.175,4.177,4.176,4.176,4.174,4.174,4.18,4.179,4.176,4.178,4.178,4.181,4.179,4.177,4.177,4.179,4.177,4.177,4.175,4.179,4.181,4.178,4.178,4.179,4.178,4.181,4.175,4.176,4.183,4.179,4.178,4.178,4.148,4.175,4.178,4.18,4.179,4.177,4.178,4.177,4.176,4.142,4.175,4.174,4.176,4.178,4.172,4.17,4.174,4.176,4.176,4.177,4.174,4.179,4.179,4.18,4.18,4.179,4.177,4.177,4.18,4.184,4.18,4.154,4.176,4.18,4.178,4.181,4.181,4.179,4.174,4.177,4.177,4.18,4.178,4.18,4.179,4.179],\"chargeSystemVoltage\":350.80002,\"currentBatteryCount\":84,\"batteryCount\":84,\"childSystemNum\":1,\"chargeSystemCurrent\":0.10003662}]}";
    private static Map<String, String> map;

    static {
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
        RedisURI redisURI = RedisURI.create("10.7.52.33", 6379);
        RedisClient redisClient = RedisClient.create(redisURI);
        StatefulRedisConnection connection = redisClient.connect();
        commands = connection.async();
        commands.select(1);
    }

    @TearDown
    public void tearDown() {
    }

    @Benchmark
    public void write() throws ExecutionException, InterruptedException {
        for (int i = 0; i < LOOP; ++i) {
//            RedisFuture future = commands.set("redis.lettuce", str);
            RedisFuture future = commands.hmset("lettuce2", map);
            future.get();
        }
    }

    @Benchmark
    public void read() throws ExecutionException, InterruptedException {
        for (int i = 0; i < LOOP; ++i) {
//            RedisFuture future = commands.get("redis.lettuce");
            RedisFuture future = commands.hgetall("lettuce2");
            future.get();
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(LettuceAsyncJMH.class.getSimpleName())
                .output("lettuceAsync.log").build();
        new Runner(options).run();
    }

}