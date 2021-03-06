package com.example.web_demo.fastdfs;

import org.apache.commons.io.IOUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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
//            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
//            ClientGlobal.init(filePath);
            trackerClient = new TrackerClient();
            ClientGlobal.setG_connect_timeout(30000);
            ClientGlobal.setG_network_timeout(30000);
            ClientGlobal.setG_anti_steal_token(false);
            ClientGlobal.setG_charset("UTF-8");
            ClientGlobal.setG_secret_key("FastDFS1234567890");
            ClientGlobal.setG_tracker_http_port(80);
            InetSocketAddress[] tracker_servers = new InetSocketAddress[1];
            tracker_servers[0] = new InetSocketAddress("121.196.179.74", 22122);
            TrackerGroup trackerGroup = new TrackerGroup(tracker_servers);
            ClientGlobal.setG_tracker_group(trackerGroup);
            trackerClient = new TrackerClient(trackerGroup);
            trackerServer = trackerClient.getConnection();
            storageServer = new StorageServer("121.196.179.74", 23000, 0);
            storageClient1 = new StorageClient1(trackerServer, storageServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void uploadFile(String groupName, String uploadFilename) {
        NameValuePair[] metaList = new NameValuePair[4];
        metaList[0] = new NameValuePair("fileName", "name");
        metaList[1] = new NameValuePair("fileLength", "");
        metaList[2] = new NameValuePair("fileExt", uploadFilename.split("\\.")[1]);
        metaList[3] = new NameValuePair("fileAuthor", "zam");
        try {
            String field = storageClient1.upload_file1(groupName, IOUtils.toByteArray(new FileInputStream(uploadFilename)), uploadFilename.split("\\.")[1], metaList);
            System.out.println(field);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void downloadFile(String groupName, String remoteFilename, String newFilename) {
        try {
            byte[] result = storageClient1.download_file(groupName, remoteFilename);
            if (result != null && result.length > 0) {
                if (newFilename == null) {
                    IOUtils.write(result, new FileOutputStream("D:/" + UUID.randomUUID().toString() + "." + remoteFilename.split("\\.")[1]));
                } else {
                    IOUtils.write(result, new FileOutputStream("D:/" + newFilename + "." + remoteFilename.split("\\.")[1]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile(String groupName, String remoteFilename) {
        try {
            int result = storageClient1.delete_file(groupName, remoteFilename);
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
        uploadFile("group1", "D:/fastdfs/system_patch_2.bin");
//        downloadFile("group1", "00/00/wKgA-F-3F7WAc5-NABKyAARnR5Q452.exe", "zam");
    }

}
