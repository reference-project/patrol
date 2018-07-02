package com.slk.patrol.service;

import com.slk.patrol.mapper.P_patrolinfo.PPatrolinfoMapper;
import com.slk.patrol.mapper.P_patroltype.PPatroltypeMapper;
import com.slk.patrol.mapper.P_region.PRegionMapper;
import com.slk.patrol.model.P_patrolinfo.PPatrolinfo;
import com.slk.patrol.model.P_patrolinfo.PPatrolinfoCustom;
import com.slk.patrol.model.P_patroltype.PPatroltype;
import com.slk.patrol.model.P_region.PRegion;
import com.slk.patrol.model.P_region.PRegionCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@Transactional
public class ReportService {

    @Autowired
    PPatrolinfoMapper infoMapper;
    @Autowired
    PRegionMapper regionMapper;
    @Autowired
    PPatroltypeMapper typeMapper;


    /**
     * 获取所有巡检记录
     * Create by song on 2018-03-30 11:07:49
     */
    public List<PPatrolinfo> selectReport(){
        return infoMapper.selectAll();
    }


    /**
     * 综合查询
     * 条件：区域，是否合格，时间
     * Create by song on 2018-03-30 11:08:39
     */
    public List<PPatrolinfoCustom> selectColligate(PPatrolinfoCustom info){
        return infoMapper.getPPatrolinfoListByInfo(info);
    }

    public List<PPatrolinfoCustom> getPatrolinfoByRecord(PPatrolinfoCustom info){
        return infoMapper.getPatrolinfoByRecord(info);
    }

    /**
     * 获取区域连级集合
     * Create by song on 2018-04-10 09:08:38
     */
    public List<PRegion> getRegionBylevel(Integer level){
        return regionMapper.getRegionBylevel(level);
    }
    /**
     * 获取类型集合
     * Create by song on 2018-04-10 09:12:07
     */
    public List<PPatroltype> getTypeListAll(){
        return typeMapper.selectAll();
    }

}
