package com.slk.patrol.service;

import com.slk.patrol.framework.util.FileUtil;
import com.slk.patrol.mapper.P_describe.PDescribeMapper;
import com.slk.patrol.mapper.P_patrolinfo.PPatrolinfoMapper;
import com.slk.patrol.mapper.P_patroltype.PPatroltypeMapper;
import com.slk.patrol.mapper.P_qrcode.PQrcodeMapper;
import com.slk.patrol.mapper.P_region.PRegionMapper;
import com.slk.patrol.model.P_describe.PDescribe;
import com.slk.patrol.model.P_patrolinfo.PPatrolinfo;
import com.slk.patrol.model.P_patrolinfo.PPatrolinfoCustom;
import com.slk.patrol.model.P_patroltype.PPatroltype;
import com.slk.patrol.model.P_qrcode.PQrcode;
import com.slk.patrol.model.P_region.PRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class InspectionService {

    @Autowired
    PRegionMapper regionMapper;
    @Autowired
    PPatroltypeMapper typeMapper;
    @Autowired
    PPatrolinfoMapper infoMapper;
    @Autowired
    PDescribeMapper describeMapper;
    @Autowired
    PQrcodeMapper qrcodeMapper;

    /**
     * 获取区域根据Id
     * Create by song on 2018-03-30 10:47:03
     */
    public PRegion getRegionById(Integer regionId){
        return regionMapper.selectByPrimaryKey(regionId);
    }

    /**
     * 获取类别根据Id
     * Create by song on 2018-03-30 10:47:03
     */
    public PPatroltype getPatrolTypeById(Integer patroltypeId){
        return typeMapper.selectByPrimaryKey(patroltypeId);
    }

    public PQrcode getQrcodeById(Integer qrcodeId){
        return qrcodeMapper.selectByPrimaryKey(qrcodeId);
    }


    /**
     * 根据类型获取描述集合
     * Create by song on 2018-04-11 08:59:38
     */
    public List<PDescribe> getDescribeByTypeID(Integer patroltypeId){
        return describeMapper.getDescribeByTypeID(patroltypeId);
    }

    /**
     * 提交巡检记录
     * Create by song on 2018-03-30 10:51:40
     */
    public Integer setPatrolinfo(PPatrolinfoCustom info) {
        String url="";
        if(null != info.getFiles())
            for (String file : info.getFiles()) {
                String spot = "";
                if (!"".equals(url)) spot = ",";
                url += spot+FileUtil.FileByteImgUp(file,"patrol/Patrolinfo");
            }
        info.setUrl(url);
        return (null == info.getId())?infoMapper.insertSelective(info):infoMapper.updateByPrimaryKey(info);
    }


    public List<PPatrolinfoCustom> selectInspectionsByQrId(Integer qrcode){
        return infoMapper.getPatrolinfoByQrId(qrcode);
    }

}
