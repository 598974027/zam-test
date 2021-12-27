package com.example.web_demo.service;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Service
public abstract class AbstractDataExport<T extends BaseRowModel> {

    /**
     * 数据导出
     *
     * @param response response
     * @param param    获取数据参数
     */
    public void dataExport(HttpServletResponse response, Map<String, Object> param) {
        List<T> data = getData(param);
        String fileName = getFileName();
        if (StringUtils.isEmpty(fileName)) {
            fileName = "DataExport";
        }
        String sheetName = getSheetName();
        if (StringUtils.isEmpty(sheetName)) {
            sheetName = "sheet";
        }
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
            ServletOutputStream out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
            Class<T> tClass =
                    (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            Sheet sheet1 = new Sheet(1, 0, tClass, sheetName, null);
            writer.write(data, sheet1);
            writer.finish();
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * 根据条件获取数据
     *
     * @param param 条件
     * @return 数据
     */
    protected abstract List<T> getData(Map<String, Object> param);

    /**
     * 获取文件名
     *
     * @return 文件名
     */
    protected abstract String getFileName();


    /**
     * 获取页签名
     *
     * @return 文件名
     */
    protected abstract String getSheetName();
}