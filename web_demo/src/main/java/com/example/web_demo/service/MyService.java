package com.example.web_demo.service;

import com.example.web_demo.dao.TestMapper;
import com.example.web_demo.entity.SysRegionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {

    @Autowired
    private TestMapper testMapper;

    public List<SysRegionEntity> test() {
        //设置id
        List<SysRegionEntity> list = testMapper.selectAllRecordFir();
        long index = 1220662;
        for (SysRegionEntity entity : list) {
            if (entity.getId() == null) {
                SysRegionEntity sysRegionEntity = new SysRegionEntity();
                sysRegionEntity.setRegionCode(entity.getRegionCode().trim());
                sysRegionEntity.setId(index++);
                testMapper.updateRecord(sysRegionEntity);
            }
            index = setChildrenId(entity, index);
            index++;
        }
        //设置pid
//        List<SysRegionEntity> list2 = testMapper.selectAllRecord();
//        for (SysRegionEntity entity : list2) {
//            if (entity.getId() == 1) {
//                entity.setPId(0L);
//                testMapper.updateRecord(entity);
//            } else {
//                SysRegionEntity sysRegionEntity = new SysRegionEntity();
//                sysRegionEntity.setRegionCode(entity.getRegionCode());
//                sysRegionEntity.setPId(getPId(entity.getPRegionCode(), list2));
//                testMapper.updateRecord(sysRegionEntity);
//            }
//        }
        //设置tree_code
//        List<SysRegionEntity> list3 = testMapper.selectAllRecord();
//        for (SysRegionEntity entity : list3) {
//            if (entity.getId() == 1) {
//                entity.setPId(null);
//                entity.setTreeCode("/1/");
//                testMapper.updateRecord(entity);
//            } else {
//                SysRegionEntity sysRegionEntity = new SysRegionEntity();
//                sysRegionEntity.setRegionCode(entity.getRegionCode());
//                sysRegionEntity.setTreeCode(getTreeCode(entity.getPRegionCode(), entity.getId()));
//                testMapper.updateRecord(sysRegionEntity);
//            }
//        }
        //设置region_path
//        for (SysRegionEntity entity : list3) {
//            if (entity.getId() == 1) {
//                entity.setPId(null);
//                entity.setTreeCode(null);
//                entity.setRegionPath("");
//                testMapper.updateRecord(entity);
//            } else {
//                SysRegionEntity sysRegionEntity = new SysRegionEntity();
//                sysRegionEntity.setRegionCode(entity.getRegionCode());
//                sysRegionEntity.setRegionPath(getRegionPath(entity.getPRegionCode()));
//                testMapper.updateRecord(sysRegionEntity);
//            }
//        }
        return list;
    }

    private String getRegionPath(String pRegionCode) {
        String result = "";
        SysRegionEntity sysRegionEntity = testMapper.getRecordByCode(pRegionCode);
        if (sysRegionEntity != null) {
            return sysRegionEntity.getRegionPath() + "/" + sysRegionEntity.getRegionName();
        }
        return result;
    }

    private String getTreeCode(String pRegionCode, long id) {
        String result = "";
        SysRegionEntity sysRegionEntity = testMapper.getRecordByCode(pRegionCode);
        if (sysRegionEntity != null) {
            return sysRegionEntity.getTreeCode() + id + "/";
        }
        return result;
    }

    private Long getPId(String pRegionCode, List<SysRegionEntity> list) {
        long result = 0;
        for (SysRegionEntity entity : list) {
            if (pRegionCode.equals(entity.getRegionCode())) {
                return entity.getId();
            }
        }
        return result;
    }

    private long setChildrenId(SysRegionEntity regionEntity, long index) {
        List<SysRegionEntity> list = testMapper.selectAllRecordByCode(regionEntity.getRegionCode().trim());
        for (SysRegionEntity entity : list) {
            if (entity.getPRegionCode() != null && entity.getPRegionCode().length() > 0) {
                if (entity.getId() == null) {
                    SysRegionEntity sysRegionEntity = new SysRegionEntity();
                    sysRegionEntity.setRegionCode(entity.getRegionCode().trim());
                    sysRegionEntity.setId(index++);
                    testMapper.updateRecord(sysRegionEntity);
                }
                index = setChildrenId(entity, index);
                index++;
            }
        }
        return index;
    }
}
