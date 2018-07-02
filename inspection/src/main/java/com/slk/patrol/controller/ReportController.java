package com.slk.patrol.controller;

import com.slk.patrol.model.P_patrolinfo.PPatrolinfo;
import com.slk.patrol.model.P_patrolinfo.PPatrolinfoCustom;
import com.slk.patrol.model.P_patroltype.PPatroltype;
import com.slk.patrol.model.P_qrcode.PQrcode;
import com.slk.patrol.model.P_region.PRegion;
import com.slk.patrol.model.P_region.PRegionCustom;
import com.slk.patrol.service.InspectionService;
import com.slk.patrol.service.ReportService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报表查询模块
 * Create by song on 2018-03-30 10:59:33
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ReportService service;


    /**
     * 获取集合
     * Create by song on 2018-04-10 09:14:54
     */
    @RequestMapping("/getRegionAndType")
    public Map getRegionAndType() {
        Boolean flag = true;
        Map<String, Object> map = new HashMap<>();
        List<PRegion> regionList = service.getRegionBylevel(2);
        List<PPatroltype> typeList = service.getTypeListAll();
        if (regionList.size() == 0 || typeList.size() == 0) {
            flag = false;
            map.put("message", "获取不到信息！");
        } else {
            map.put("regionList", regionList);
            map.put("typeList", typeList);
        }
        return map;
    }


    /**
     * 查询二维码巡检记录
     * Create by song on 2018-03-30 11:07:49
     */
    @RequestMapping("/selectQrReport")
    public Map selectReport(PPatrolinfoCustom info){
        Boolean flag = true;
        Map<String,Object> map = new HashMap<>();
        List<PPatrolinfoCustom> pPatrolinfos = service.getPatrolinfoByRecord(info);
        if (0==pPatrolinfos.size()){
            flag = false;
            map.put("message","暂时没有数据可获取！");
        }else{
            map.put("pPatolinfos",pPatrolinfos);
        }
        map.put("flag",flag);
        return map;
    }

    
    /**
     * 综合查询
     * Create by song on 2018-03-30 11:08:39
     */
    @RequestMapping("/selectColligate")
    public Map selectColligate(PPatrolinfoCustom info){
        Boolean flag = true;
        Map<String,Object> map = new HashMap<>();
        List<PPatrolinfoCustom> pPatrolinfos = service.selectColligate(info);

        if(0==pPatrolinfos.size()){
            flag = false;
            map.put("message","暂时没有数据可获取！");
        }else{
            map.put("pPatrolinfos",pPatrolinfos);
        }
        map.put("flag",flag);
        return map;
    }


}
