package com.slk.patrol.controller;

import com.slk.patrol.model.P_describe.PDescribe;
import com.slk.patrol.model.P_patrolinfo.PPatrolinfo;
import com.slk.patrol.model.P_patrolinfo.PPatrolinfoCustom;
import com.slk.patrol.model.P_patroltype.PPatroltype;
import com.slk.patrol.model.P_qrcode.PQrcode;
import com.slk.patrol.model.P_region.PRegion;
import com.slk.patrol.model.P_region.PRegionCustom;
import com.slk.patrol.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 扫码，提交巡检记录-H5
 * Create by song on 2018-03-30 10:58:20
 */
@RestController
@RequestMapping("/detection")
public class InspectionController {

    @Autowired
    private InspectionService service;

    
    /**
     * 扫码，返回巡检参数：区域，类型
     * Create by song on 2018-03-30 10:50:17
     */
    @RequestMapping(value = "/getScancode/{regionId}/{patoltypeId}/{qrcodeId}")
    public Map getScancode( @PathVariable Integer regionId ,@PathVariable Integer patoltypeId ,@PathVariable Integer qrcodeId){
        Boolean flag = true;
        Map<String,Object> map = new HashMap<>();
        PRegion region = service.getRegionById(regionId);
        PPatroltype type = service.getPatrolTypeById(patoltypeId);
        PQrcode qrcode = service.getQrcodeById(qrcodeId);
        List<PDescribe> typeList = service.getDescribeByTypeID(patoltypeId);
        if(null == region || null == type){
            flag = false;
            map.put("message","获取不到信息！");
        }else{
            map.put("regionName",region.getName());
            map.put("typeName",type.getName());
            map.put("remark",qrcode.getRemark());
            map.put("descr",qrcode.getDescr());
            map.put("typeList",typeList);
        }
        map.put("flag",flag);
        return  map;
    }

    
    /**
     * 提交巡检记录
     * Create by song on 2018-03-30 10:51:40
     */
    @RequestMapping("/setScancode")
    public Map setScancode(PPatrolinfoCustom info){
        Boolean flag  = true;
        String message = "提交成功！";
        Map<String,Object> map = new HashMap<>();
        if(0 == service.setPatrolinfo(info)){
            flag = false;
            message = "提交失败！";
        }
        map.put("flag",flag);
        map.put("message",message);
        return map;
    }



    @RequestMapping("/selectInspectionsByQrId/{qrcodeId}")
    public Map selectInspectionsByQrId(@PathVariable Integer qrcodeId){
        Boolean flag  = true;
        String message = "成功获取巡检记录";
        Map<String,Object> map = new HashMap<>();
        List<PPatrolinfoCustom> infos = service.selectInspectionsByQrId(qrcodeId);
        if(infos.size()==0){
            flag = false;
            message = "暂时没有巡检记录";
        }else {
            map.put("infos",infos);
        }
        map.put("flag",flag);
        map.put("message",message);
        return map;
    }





}
