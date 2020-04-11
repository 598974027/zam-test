package mongodb;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述: Mongodb工具类
 *
 * @author zhangam
 * @time 2019/6/18 9:03
 * @see
 **/
public class MongoUtil {

    /**
     * 功能描述: MongoClient
     *
     * @author zhangam
     * @date 2019/6/18 9:08
     * @since
     */
    private static MongoClient mongoClient;

    /**
     * 功能描述: 数据库名称
     *
     * @author zhangam
     * @date 2019/6/18 9:08
     * @since
     */
    private static String MongoDataBase;

    /**
     * 功能描述: 初始化
     *
     * @author zhangam
     * @date 2019/6/18 9:08
     * @since
     */
    public static void init(String ip, String port, String username, String password, String dataBase) {
        String[] ips = ip.split(",");
        String[] ports = port.split(",");
        MongoDataBase = dataBase;
        MongoClientOptions.Builder build = new MongoClientOptions.Builder();
        //线程池空闲时保持的最小连接数
        //build.minConnectionsPerHost();
        //线程池允许的最大连接数
        //build.connectionsPerHost();
        //线程池中连接的最大空闲时间,5分钟
        //build.maxConnectionIdleTime();
        //设置服务器选择超时（以毫秒为单位），它定义驱动程序在抛出异常之前等待服务器选择成功的时间
        //值为0表示如果没有可用的服务器，它将立即超时，负值意味着无限期等待
        //build.serverSelectionTimeout();
        //连接超时时间,必须大于0
        //build.connectTimeout();
        //线程等待连接变为可用的最长时间
        //build.maxWaitTime();
        //线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误
        //build.threadsAllowedToBlockForConnectionMultiplier();
        build.writeConcern(WriteConcern.W1);//
        build.readConcern(ReadConcern.LOCAL);
        MongoClientOptions options = build.build();
        List<ServerAddress> listHost = new ArrayList<>(ips.length);
        for (int i = 0; i < ips.length; i++) {
            listHost.add(new ServerAddress(ips[i].trim(), Integer.valueOf(ports[i].trim())));
        }
        if (username != null && password != null) {
            //三个参数分别为 用户名 数据库名称 密码
            MongoCredential credential = MongoCredential.createScramSha1Credential(username, MongoDataBase, password.toCharArray());
            mongoClient = new MongoClient(listHost, credential, options);
        } else {
            mongoClient = new MongoClient(listHost, options);
        }
    }

    /**
     * 功能描述: 获取客户端连接
     *
     * @author zhangam
     * @date 2019/6/18 9:32
     * @since
     */
    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    /**
     * 功能描述: 连接到数据库
     *
     * @author zhangam
     * @date 2019/6/18 9:32
     * @since
     */
    public static MongoDatabase getDatabase() {
        if (MongoDataBase != null && !"".equals(MongoDataBase)) {
            MongoDatabase database = mongoClient.getDatabase(MongoDataBase);
            return database;
        }
        return null;
    }

    /**
     * 功能描述: 连接到数据库
     *
     * @author zhangam
     * @date 2019/6/18 9:32
     * @since
     */
    public static MongoDatabase getDatabase(String dataBase) {
        if (dataBase != null && !"".equals(dataBase)) {
            MongoDatabase database = mongoClient.getDatabase(dataBase);
            return database;
        }
        return null;
    }

    /**
     * 功能描述: 关闭Mongodb
     *
     * @author zhangam
     * @date 2019/6/18 9:08
     * @since
     */
    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }

}
