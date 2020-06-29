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

    static {
        try {
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        trackerClient = new TrackerClient();
        try {
            trackerServer = trackerClient.getConnection();
            storageClient = new StorageClient(trackerServer, storageServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String uploadFile(byte[] byteFile, String extFile) {
        StringBuffer sbPath = new StringBuffer();
        try {
            String[] strings = storageClient.upload_file(byteFile, extFile, null);
//            for (String string : strings) {
//                sbPath.append("/" + string);
//            }
            sbPath.append(StrUtil.join("/", strings));
            System.out.println(sbPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbPath.toString();
    }

    public static void downloadFile(String groupName, String fileName) {
        try {
            byte[] b = storageClient.download_file(groupName, fileName);
            IOUtils.write(b, new FileOutputStream("D:/" + UUID.randomUUID().toString() + ".conf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        uploadFile(IOUtils.toByteArray(new FileInputStream(new File("D:\\zam.txt"))), "txt");
    }

}
