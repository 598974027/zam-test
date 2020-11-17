package com.example.web_demo.controller;

import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 功能描述:
 * org.apache.commons.io.FileUtils
 * org.apache.commons.io.IOUtils
 *
 * @author zhangaomin
 * @time 2020/11/17 10:20
 **/
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 上传
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {
        String realSavePath = "D:\\IdeaProjects\\zam-test\\web_demo\\zam\\";
        String tempSavePath = "D:\\IdeaProjects\\zam-test\\web_demo\\zam\\temp\\";
        try {
            checkFileDirExists(realSavePath);
            checkFileDirExists(tempSavePath);
            String fileName = file.getOriginalFilename();
            File realFile = new File(realSavePath + fileName);
            File tempFile = new File(tempSavePath + fileName);
            if (checkFileExists(realFile.getPath())) {
                return "文件已经存在，请不要重复上传";
            } else {
                InputStream in = file.getInputStream();
                long needSkipBytes = 0;
                if (tempFile.exists()) {
                    //续传
                    needSkipBytes = tempFile.length();
                } else {
                    //第一次传
                    tempFile.createNewFile();
                }
                in.skip(needSkipBytes);
                RandomAccessFile tempRandAccessFile = new RandomAccessFile(tempFile, "rw");
                tempRandAccessFile.seek(needSkipBytes);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) > 0) {
                    tempRandAccessFile.write(buffer);
                }
                in.close();
                tempRandAccessFile.close();
                realFile.createNewFile();
                if (fileCopy(tempFile, realFile)) {
                    tempFile.delete();
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }


    /**
     * 下载-断点续传
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    @ApiImplicitParam(paramType = "header", name = "Range", value = "下载范围", dataType = "String", required = true)
    public void download(HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            File proposeFile = new File("D:\\IdeaProjects\\zam-test\\web_demo\\zam\\SocketTool.exe");
            response.setContentType("application/x-download");
            // Content-Disposition: attachment; filename=WebGoat-OWASP_Developer-5.2.zip
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("SocketTool.exe", "UTF-8"));
            // Accept-Ranges: bytes
            response.setHeader("Accept-Ranges", "bytes");
            // pos开始读取位置; last最后读取位置; sum记录总共已经读取了多少字节
            long pos = 0, last = proposeFile.length() - 1, sum = 0;
            if (request.getHeader("Range") != null) {
                // 断点续传
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                try {
                    // 情景一：RANGE: bytes=100-
                    // 情景二：RANGE: bytes=100-200000
                    String numRang = request.getHeader("Range").replaceAll("bytes=", "");
                    String[] strRange = numRang.split("-");
                    if (strRange.length == 2) {
                        pos = Long.parseLong(strRange[0].trim());
                        last = Long.parseLong(strRange[1].trim());
                    } else {
                        pos = Long.parseLong(numRang.replaceAll("-", "").trim());
                    }
                } catch (NumberFormatException e) {
                    pos = 0;
                }
            }
            // 总共需要读取的字节
            long rangLength = last - pos + 1;
            // Content-Length: 1024
            response.addHeader("Content-Length", String.valueOf(rangLength));
            // Content-Range: bytes 100-200000/1225216
            String contentRange = new StringBuffer("bytes ").append(pos).append("-").append(last).append("/").append(proposeFile.length()).toString();
            response.setHeader("Content-Range", contentRange);

            outputStream = new BufferedOutputStream(response.getOutputStream());
            inputStream = new BufferedInputStream(new FileInputStream(proposeFile));
            // 跳过已经下载的部分，进行后续下载
            inputStream.skip(pos);
            byte[] buffer = new byte[1024];
            int length = 0;
            while (sum < rangLength) {
                length = inputStream.read(buffer, 0, ((rangLength - sum) <= buffer.length ? ((int) (rangLength - sum)) : buffer.length));
                sum = sum + length;
                outputStream.write(buffer, 0, length);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean checkFileExists(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        return true;
    }

    public static void checkFileDirExists(String path) {
        File file = new File(path);
        if (!file.exists()) {
            //不存在创建文件夹，区别于mkdir。mkdir如果上级目录不存在，则报错
            file.mkdirs();
        } else {
            //存在则删除文件夹下的所有文件
//            doClean(file);
        }
    }

    public static void doClean(File fileDir) {
        if (fileDir.isDirectory()) {
            File[] fs = fileDir.listFiles();
            if (fs.length != 0) {
                for (int i = 0; i < fs.length; i++) {
                    if (fs[i].isDirectory()) {
                        doClean(fs[i]);
                    } else {
                        //delete只能删除文件和空文件夹
                        fs[i].delete();
                    }
                }
            }
        }
        fileDir.delete();
    }

    private static boolean fileCopy(File sourceFile, File targetFile) {
        boolean success = true;
        try {
            FileInputStream in = new FileInputStream(sourceFile);
            FileOutputStream out = new FileOutputStream(targetFile);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            success = false;
        } catch (IOException e) {
            success = false;
        }
        return success;
    }

}
