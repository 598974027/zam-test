package com.example.web_demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 功能描述: SysRegionEntity
 *
 * @author zhangam
 * @ExcelProperty(value = {"ID"}, index = 0)
 * @DateTimeFormat(value = "yyyy/MM/dd")
 * @ColumnWidth(20)
 * @time 2019/8/20 10:29
 * @see
 **/
@Data
public class SysRegionEntity {

    /**
     * id
     */
    @ExcelProperty(value = {"ID"}, index = 0)
    private Long id;

    /**
     * pid
     */
    @ExcelProperty(value = {"PID"}, index = 1)
    private Long pId;

    /**
     * region_code
     */
    @ExcelProperty(value = {"编码"}, index = 2)
    private String regionCode;

    /**
     * region_name
     */
    @ExcelProperty(value = {"名称"}, index = 3)
    private String regionName;

    /**
     * p_region_code
     */
    @ExcelProperty(value = {"父编码"}, index = 4)
    private String pRegionCode;

    /**
     * region_level
     */
    @ExcelProperty(value = {"等级"}, index = 5)
    private String regionLevel;

    /**
     * longitude
     */
    @ExcelProperty(value = {"经度"}, index = 6)
    private BigDecimal longitude;

    /**
     * latitude
     */
    @ExcelProperty(value = {"纬度"}, index = 7)
    private BigDecimal latitude;

    /**
     * tree_code
     */
    @ColumnWidth(20)
    @ExcelProperty(value = {"树编码"}, index = 8)
    private String treeCode;

    /**
     * region_path
     */
    @ExcelProperty(value = {"路径"}, index = 9)
    private String regionPath;

    /**
     * sort_num
     */
    @ExcelProperty(value = {"排序"}, index = 10)
    private Integer sortNum;
}
