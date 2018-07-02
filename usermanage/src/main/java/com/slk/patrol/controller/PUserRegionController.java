package com.slk.patrol.controller;

import com.slk.patrol.model.P_user_region.PUserRegion;
import com.slk.patrol.service.P_userRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("userRegion")
public class PUserRegionController {

    @Autowired
    private P_userRegionService service;


    /**
     * 查询用户管辖的区域
     * @param customerId
     * @return
     */
    @RequestMapping("/selectRegionByCustomerId")
    public ConcurrentHashMap selectRegionByCustomerById(Integer customerId){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        String message="未查到信息";
        boolean flag=false;
        List<PUserRegion> list=service.selectRegionByCustomerById(customerId);
        if (list.size() != 0) {
            flag=true;
            message="已找到数据";
        }
        map.put("flag",flag);
        map.put("message",message);
        map.put("userRegionList",list);
        return map;
    }

    /**
     * 查询区域下是否有人管辖
     * @param id
     * @return
     */
    @RequestMapping("/selectRegionCount")
    public ConcurrentHashMap selectRegionCount(Integer id){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        boolean flag=service.selectRegionCount(id);
        String message="该区域下有人管辖";
        if(flag){
            message="该区域下无人管辖";
        }
        map.put("flag",flag);
        map.put("message",message);
        return map;
    }

    //暂时不使用
    /**
     * 修改用户管辖区域
     * @param userId
     * @param regionIds
     * @return
     *
     */
   /* @RequestMapping("/updateUserRegion")
    public ConcurrentHashMap updateUserRegion(Integer userId,String regionIds){
        boolean flag=false;
        map.clear();
        if(regionIds!=null&&!"".equals(regionIds)){
            flag=service.updateUserRegion(userId,regionIds.split(","));
        }
        map.put("flag",flag);
        return map;
    }*/
}
