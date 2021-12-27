package com.example.web_demo.office;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangaomin
 * @classname TestCsv
 * @description TODO
 * @date 2021/12/24 10:25
 */
public class TestCsv {

    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        List<List<String>> values = new ArrayList<>();
        try {
            fileInputStream = new FileInputStream("C:\\Users\\ZAM\\Desktop\\export.csv");
//            inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            CSVParser parser = CSVFormat.DEFAULT.parse(bufferedReader);
            int rowIndex = 0;
            for (CSVRecord record : parser.getRecords()) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                List<String> value = new ArrayList<>(4 + 1);
                for (int i = 0; i < 4; i++) {
                    value.add(record.get(i));
                }
                values.add(value);
                rowIndex++;
            }
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch (Exception e) {
            System.out.println("解析CSV内容失败" + e.getMessage());
        }
        System.out.println("解析CSV内容成功");

        //加id
        int index = 1220662;
        for (List<String> value : values) {
            String regionParCode = value.get(1);
            if ("0".equals(regionParCode.trim())) {
                value.add(String.valueOf(1));
            } else {
                value.add(String.valueOf(index++));
            }
        }
        //加pid
        for (List<String> value : values) {
            String regionParCode = value.get(1);
            value.add(getPid(regionParCode, values));
        }
        //加tree_code
        for (List<String> value : values) {
            String regionParCode = value.get(1);
            String id = value.get(4);
            value.add(getTreeCode(regionParCode, id, values));
        }
        //加region_path
        for (List<String> value : values) {
            String regionParCode = value.get(1);
            value.add(getRegionPath(regionParCode, values));
        }
        try {
            File file = File.createTempFile("zam", ".csv", new File("C:\\Users\\ZAM\\Desktop"));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            CSVPrinter printer = new CSVPrinter(bufferedWriter, CSVFormat.DEFAULT.withRecordSeparator("\n"));
            printer.printRecord(new String[]{"1", "2", "3", "4", "ID", "PID", "TREE-CODE", "REGION-PATH"});
            for (List<String> value : values) {
                printer.printRecord(value);
            }
            printer.close();
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("写入CSV内容失败" + e.getMessage());
        }
        System.out.println("写入CSV内容成功");
    }

    private static String getPid(String regionParCode, List<List<String>> values) {
        String result = "";
        if ("A0".equals(regionParCode.trim())) {
            return "0";
        }
        for (List<String> value : values) {
            String regionCode = value.get(0);
            if (regionCode.equals(regionParCode.trim())) {
                result = value.get(4);
                return result;
            }
        }
        return result;
    }

    private static String getTreeCode(String regionParCode, String id, List<List<String>> values) {
        String result = "";
        if ("A0".equals(regionParCode.trim())) {
            return "/1/";
        }
        for (List<String> value : values) {
            String regionCode = value.get(0);
            if (regionCode.equals(regionParCode.trim())) {
                result = value.get(6) + id + "/";
                return result;
            }
        }
        return result;
    }

    private static String getRegionPath(String regionParCode, List<List<String>> values) {
        String result = "";
        if ("A0".equals(regionParCode.trim())) {
            return "";
        }
        for (List<String> value : values) {
            String regionCode = value.get(0);
            if (regionCode.equals(regionParCode.trim())) {
                if ("A0".equals(value.get(1))) {
                    result = value.get(2);
                } else {
                    result = value.get(7) + "/" + value.get(2);
                }
                return result;
            }
        }
        return result;
    }
}
