package com.example.web_demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 功能描述: 计数器工具类
 *
 * @author zhangaomin
 * @time 2020/7/2 11:05
 **/
public class CountingUtil {
    /**
     * 单例
     */
    private static final CountingUtil instance = new CountingUtil();

    /**
     * 计数器集合
     */
    private ConcurrentHashMap<String, OneCounting> countingMap;

    /**
     * 私有构造
     */
    private CountingUtil() {
        countingMap = new ConcurrentHashMap<>();
    }

    /**
     * 返回单例
     */
    public static CountingUtil getInstance() {
        return instance;
    }

    /**
     * 锁对象
     */
    private Object lockObject = new Object();

    /**
     * 更新计数信息
     */
    public void updateCount(String name, long count) {
        OneCounting one = countingMap.get(name);
        if (one == null) {
            synchronized (lockObject) {
                one = countingMap.get(name);
                if (one == null) {
                    one = new OneCounting(name);
                    countingMap.put(name, one);
                }
            }
        }
        one.updateCount(count);
    }

    /**
     * 添加计数器
     */
    private void addOneCounting(String name, OneCounting oneCounting) {
        countingMap.put(name, oneCounting);
    }

    /**
     * 获取所有计数器
     */
    public Map<String, OneCounting> getAllCounting() {
        return countingMap;
    }

    /**
     * 获取单个计数器
     */
    public OneCounting getOneCounting(String name) {
        return countingMap.get(name);
    }

    /**
     * 获取单个计数器信息
     */
    public String getOneCountingInfo(String name) {
        OneCounting oneCounting = getOneCounting(name);
        if (oneCounting == null) {
            return "";
        } else {
            return oneCounting.getInfo();
        }
    }

    /**
     * 功能描述: 一个计数器
     **/
    public class OneCounting {
        /**
         * 计数名称
         */
        private String name;

        /**
         * 上一次数据量
         */
        private long lastCount;

        /**
         * 上一次时间戳
         */
        private long lastTickCount;

        /**
         * 计数量
         */
        private AtomicLong currentCount = new AtomicLong();

        /**
         * 构造函数
         */
        public OneCounting(String countingName) {
            name = countingName;
            lastTickCount = System.currentTimeMillis();
            CountingUtil.getInstance().addOneCounting(countingName, this);
        }

        /**
         * 设置数量
         */
        private void setCount(long value) {
            currentCount.set(value);
        }

        /**
         * 增加计数
         */
        public void updateCount(long count) {
            currentCount.addAndGet(count);
        }

        /**
         * 获取记数器数量
         */
        public long getCount() {
            return currentCount.get();
        }

        /**
         * 获取计数器速度
         */
        public String getRate() {
            long currentTick = System.currentTimeMillis();
            double timespan = (currentTick - lastTickCount) / 1000.0;
            if (timespan == 0) {
                return "0.00";
            }
            double rate = (getCount() - lastCount) / timespan;
            lastCount = getCount();
            lastTickCount = currentTick;
            return String.format("%.2f", rate);
        }

        /**
         * 获取计数信息
         */
        public String getInfo() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[名称:" + name);
            stringBuilder.append(";数量:" + getCount());
            stringBuilder.append("; 速率:" + getRate() + "/s]");
            return stringBuilder.toString();
        }

    }

}
