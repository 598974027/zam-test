package redis.lettuce;

import com.alibaba.fastjson.JSONObject;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 功能描述: 单机客户端
 *
 * @author zhangam
 * @time 2018-9-25下午5:03:58
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LettuceTest {

    /**
     * 功能描述:
     *
     * @param args
     * @author zhangam
     * @time 2018-9-25下午5:03:59
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void main(String[] args) throws Exception {
        int dn = Integer.parseInt(args[0]);
        int type = Integer.parseInt(args[1]);

//        String str = "{\"gearDriveForce\":0,\"batteryConsistencyDifferenceAlarm\":0,\"soc\":98,\"socJumpAlarm\":0,\"caterpillaringFunction\":0,\"satNum\":11,\"socLowAlarm\":0,\"chargingGunConnectionState\":0,\"minTemperatureSubSystemNum\":1,\"chargedElectronicLockStatus\":0,\"maxVoltageBatteryNum\":68,\"terminalTime\":\"2019-01-04 13:04:48\",\"singleBatteryOverVoltageAlarm\":0,\"otherFaultCount\":0,\"vehicleStorageDeviceOvervoltageAlarm\":0,\"brakeSystemAlarm\":0,\"serverTime\":\"2019-01-04 13:05:41.894\",\"vin\":\"LS5A3AJC4JB011211\",\"rechargeableStorageDevicesFaultCount\":0,\"remainedPowerMile\":247,\"driveMotorTemperatureAlarm\":0,\"dcdcStatusAlarm\":0,\"gearBrakeForce\":1,\"lat\":24.704769999999996,\"driveMotorFaultCodes\":\"123456\",\"deviceType\":\"rmu\",\"vehicleSpeed\":0,\"lng\":118.42937999999998,\"chargingTimeExtensionReason\":0,\"gpsTime\":\"2019-01-04 13:04:48\",\"nevChargeSystemVoltageDtoList\":[{\"currentBatteryStartNum\":1,\"batteryVoltage\":[4.176,4.158,4.172,4.18,4.173,4.179,4.175,4.177,4.176,4.176,4.174,4.174,4.18,4.179,4.176,4.178,4.178,4.181,4.179,4.177,4.177,4.179,4.177,4.177,4.175,4.179,4.181,4.178,4.178,4.179,4.178,4.181,4.175,4.176,4.183,4.179,4.178,4.178,4.148,4.175,4.178,4.18,4.179,4.177,4.178,4.177,4.176,4.142,4.175,4.174,4.176,4.178,4.172,4.17,4.174,4.176,4.176,4.177,4.174,4.179,4.179,4.18,4.18,4.179,4.177,4.177,4.18,4.184,4.18,4.154,4.176,4.18,4.178,4.181,4.181,4.179,4.174,4.177,4.177,4.18,4.178,4.18,4.179,4.179],\"chargeSystemVoltage\":350.80002,\"currentBatteryCount\":84,\"batteryCount\":84,\"childSystemNum\":1,\"chargeSystemCurrent\":0.10003662}],\"engineFaultCount\":0,\"carId\":\"6666\",\"currentElectricity\":9.8,\"singleBatteryUnderVoltageAlarm\":0,\"maxVoltageBatterySubSystemNum\":1,\"minTemperatureProbe\":3,\"driveMotorNum\":1,\"totalVoltage\":350.8,\"maxAlarmLevel\":0,\"temperatureDifferenceAlarm\":0,\"status\":\"1\",\"averageEnergyConsumption\":12.4,\"geerPosition\":0,\"minVoltageBattery\":4.142000198364258,\"geerStatus\":0,\"driveMotorData\":[{\"controllerInputVoltage\":347.0,\"controllerTemperature\":28,\"revolutionSpeed\":0,\"num\":1,\"controllerDcBusCurrent\":0.0,\"temperature\":36,\"torque\":0.0,\"state\":4}],\"minVoltageBatteryNum\":48,\"engineFaultCodes\":\"8888\",\"minTemperatureValue\":31,\"chargeStatus\":3,\"alti\":43.80005,\"ignitionTime\":\"2019-01-04 13:43:52\",\"totalOdometer\":4337.0,\"speed\":0.0,\"socHighAlarm\":0,\"vehicleStorageDeviceUndervoltageAlarm\":0,\"batteryAlarm\":0,\"totalCurrent\":0.10000000000002274,\"isHistoryPoi\":0,\"rechargeableStorageDeviceMismatchAlarm\":0,\"maxVoltageBattery\":4.184000015258789,\"dcdcTemperatureAlarm\":0,\"vehiclePureDeviceTypeOvercharge\":0,\"lastUpdatedTime\":\"2019-01-04 13:43:56.328\",\"driveMotorControllerTemperatureAlarm\":0,\"nevChargeSystemTemperatureDtoList\":[{\"probeTemperatures\":[32,33,31,32,32,32,31,32,32,33,31,31,31,32,31,33,31,32,32,32,31,33,32,32,31,32,32,33],\"chargeTemperatureProbeNum\":28,\"childSystemNum\":1}],\"igniteCumulativeMileage\":\"4333.2\",\"dcStatus\":1,\"repay\":false,\"maxTemperatureSubSystemNum\":1,\"carStatus\":2,\"heading\":1.73,\"minVoltageBatterySubSystemNum\":1,\"driveMotorFaultCount\":0,\"tuid\":\"50003001180504140000000060453322\",\"energyRecoveryStatus\":0,\"fireStatus\":\"1\",\"maxTemperatureProbe\":2,\"rechargeableStorageDevicesFaultCodes\":\"7777\",\"carMode\":1,\"highVoltageInterlockStateAlarm\":0,\"insulationAlarm\":0,\"maxTemperatureValue\":33,\"mileageInformation\":247,\"otherFaultCodes\":\"qqqq\",\"remainPower\":9.8,\"insulateResistance\":7628,\"batteryLowTemperatureHeater\":0}";
        String str = "{\"gearDriveForce\":0,\"batteryConsistencyDifferenceAlarm\":0,\"soc\":98,\"socJumpAlarm\":0,\"caterpillaringFunction\":0,\"satNum\":11,\"socLowAlarm\":0,\"chargingGunConnectionState\":0,\"minTemperatureSubSystemNum\":1,\"chargedElectronicLockStatus\":0,\"maxVoltageBatteryNum\":68,\"terminalTime\":\"2019-01-04 13:04:48\",\"singleBatteryOverVoltageAlarm\":0,\"otherFaultCount\":0,\"vehicleStorageDeviceOvervoltageAlarm\":0,\"brakeSystemAlarm\":0,\"serverTime\":\"2019-01-04 13:05:41.894\",\"vin\":\"LS5A3AJC4JB011211\",\"rechargeableStorageDevicesFaultCount\":0,\"remainedPowerMile\":247,\"driveMotorTemperatureAlarm\":0,\"dcdcStatusAlarm\":0,\"gearBrakeForce\":1,\"lat\":24.704769999999996,\"driveMotorFaultCodes\":\"123456\",\"deviceType\":\"rmu\",\"vehicleSpeed\":0,\"lng\":118.42937999999998,\"chargingTimeExtensionReason\":0,\"gpsTime\":\"2019-01-04 13:04:48\",\"nevChargeSystemVoltageDtoList\":[{\"currentBatteryStartNum\":1,\"batteryVoltage\":[4.176,4.158,4.172,4.18,4.173,4.179,4.175,4.177,4.176,4.176,4.174,4.174,4.18,4.179,4.176,4.178,4.178,4.181,4.179,4.177,4.177,4.179,4.177,4.177,4.175,4.179,4.181,4.178,4.178,4.179,4.178,4.181,4.175,4.176,4.183,4.179,4.178,4.178,4.148,4.175,4.178,4.18,4.179,4.177,4.178,4.177,4.176,4.142,4.175,4.174,4.176,4.178,4.172,4.17,4.174,4.176,4.176,4.177,4.174,4.179,4.179,4.18,4.18,4.179,4.177,4.177,4.18,4.184,4.18,4.154,4.176,4.18,4.178,4.181,4.181,4.179,4.174,4.177,4.177,4.18,4.178,4.18,4.179,4.179],\"chargeSystemVoltage\":350.80002,\"currentBatteryCount\":84,\"batteryCount\":84,\"childSystemNum\":1,\"chargeSystemCurrent\":0.10003662}]}";
        JSONObject jo = JSONObject.parseObject(str);
        Iterator<String> it = jo.keySet().iterator();
        Map<String, String> map = new HashMap<String, String>();
        while (it.hasNext()) {
            String key = it.next();
            map.put(key, jo.getString(key));
        }
        RedisURI redisURI = RedisURI.create("192.168.2.111", 6379);
        RedisClient redisClient = RedisClient.create(redisURI);
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        //响应式connection.reactive()
        //同步方式connection.sync()
        //异步方式connect.async()
//        RedisReactiveCommands<String, String> redis.lettuce = connection.reactive();
//        RedisCommands<String, String> redis.lettuce = connection.sync();
        RedisAsyncCommands<String, String> lettuce = connection.async();
        lettuce.select(5);
        switch (type) {
            case 1:
                lettuce.flushdb();
                long time1 = System.currentTimeMillis();
                for (int j = 0; j < dn; j++) {
                    RedisFuture future = lettuce.set(Thread.currentThread().getName() + "string" + j, str);
                    future.get();
                }
                long time2 = System.currentTimeMillis();
                System.out.println("线程"
                        + Thread.currentThread().getName()
                        + "--redis.lettuce--存字符串--耗时(ms)-------------"
                        + (time2 - time1));
                break;
            case 2:
                long time3 = System.currentTimeMillis();
                for (int j = 0; j < dn; j++) {
                    RedisFuture future = lettuce.get(Thread.currentThread().getName() + "string" + j);
                    future.get();
                }
                long time4 = System.currentTimeMillis();
                System.out.println("线程"
                        + Thread.currentThread().getName()
                        + "--redis.lettuce--取字符串--耗时(ms)-------------"
                        + (time4 - time3));
                break;
            case 3:
                lettuce.flushdb();
                long time5 = System.currentTimeMillis();
                for (int j = 0; j < dn; j++) {
                    RedisFuture future = lettuce.hmset(Thread.currentThread().getName() + "hash" + j, map);
                    future.get();
                }
                long time6 = System.currentTimeMillis();
                System.out.println("线程"
                        + Thread.currentThread().getName()
                        + "--redis.lettuce--存哈希--耗时(ms)-------------"
                        + (time6 - time5));
                break;
            case 4:
                long time7 = System.currentTimeMillis();
                for (int j = 0; j < dn; j++) {
                    RedisFuture future = lettuce.hgetall(Thread.currentThread().getName() + "hash" + j);
                    future.get();
                }
                long time8 = System.currentTimeMillis();
                System.out.println("线程"
                        + Thread.currentThread().getName()
                        + "--redis.lettuce--取哈希--耗时(ms)-------------"
                        + (time8 - time7));
                break;
        }
        connection.close();
        redisClient.shutdown();
    }

}
