package com.example.web_demo.fastdfs;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * 功能描述: FastDFSClient
 *
 * @author zhangaomin
 * @time 2020/6/29 17:11
 **/
public class FastDFSClient {

    private static final Logger logger = LoggerFactory.getLogger(FastDFSClient.class);
    private static TrackerClient trackerClient = null;
    private static TrackerServer trackerServer = null;
    private static StorageServer storageServer = null;
    private static StorageClient storageClient = null;
    private static StorageClient1 storageClient1 = null;

    static {
        try {
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
            trackerClient = new TrackerClient();
//            ClientGlobal.setG_connect_timeout(100);
//            ClientGlobal.setG_network_timeout(3000);
//            ClientGlobal.setG_anti_steal_token(false);
//            ClientGlobal.setG_charset("UTF-8");
//            ClientGlobal.setG_secret_key("FastDFS1234567890");
//            ClientGlobal.setG_tracker_http_port(1234);
//            InetSocketAddress[] tracker_servers = new InetSocketAddress[1];
//            tracker_servers[0] = new InetSocketAddress("139.159.185.20", 22122);
//            TrackerGroup trackerGroup = new TrackerGroup(tracker_servers);
//            ClientGlobal.setG_tracker_group(trackerGroup);
//            trackerClient = new TrackerClient(trackerGroup);
            trackerServer = trackerClient.getConnection();
            storageServer = new StorageServer("139.159.185.20", 23000, 0);
            storageClient1 = new StorageClient1(trackerServer, storageServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void uploadFile(byte[] byteFile, String extFile) {
        NameValuePair[] meta_list = new NameValuePair[4];
        meta_list[0] = new NameValuePair("fileName", "test");
        meta_list[1] = new NameValuePair("fileLength", "");
        meta_list[2] = new NameValuePair("fileExt", extFile);
        meta_list[3] = new NameValuePair("fileAuthor", "zam");
        try {
            String fileid = storageClient1.upload_file1("group1", byteFile, extFile, meta_list);
            System.out.println(fileid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void downloadFile(String group_name, String remote_filename) {
        try {
            byte[] result = storageClient1.download_file(group_name, remote_filename);
            if (result != null && result.length > 0) {
                IOUtils.write(result, new FileOutputStream("D:/" + UUID.randomUUID().toString() + ".txt"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile(String group_name, String remote_filename) {
        try {
            int result = storageClient1.delete_file(group_name, remote_filename);
            if (result == 0) {
                System.out.println("文件删除成功");
            } else if (result == 2) {
                System.out.println("文件不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        uploadFile(IOUtils.toByteArray(new FileInputStream("D:/zam.txt")), "txt");
//        uploadFile(IOUtils.toByteArray(new FileInputStream("D:/xxx.png")), "png");
        downloadFile("group1", "00/00/wKgA-F77AtyAII6RAAAANxZALME319.txt");
        deleteFile("group1", "00/00/wKgA-F77A8aAYcFvAAKKkA2lCao166.png");
    }

}
