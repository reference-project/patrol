package com.slk.patrol.controller;

import com.slk.patrol.model.P_patroltype.PPatroltype;
import com.slk.patrol.model.P_patroltype.PPatroltypeCustom;
import com.slk.patrol.service.AreatypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 区域类型管理
 * Create by song on 2018-03-30 10:57:55
 */
@RestController
@RequestMapping("/areatype")
public class AreatypeController {


    @Autowired
    private AreatypeService service;



    /**
     * 获取区域类别集合
     * Create by song on 2018-03-28 14:11:16
     */
    @RequestMapping("/getAreatypeList")
    public Map getAreatypeList(){
        Boolean flag = true;
        Map<String,Object> map = new HashMap<>();
        List<PPatroltype> list = service.getAreatypeList();
        if (list.size() == 0){
            flag = false;
            map.put("message","暂时没有数据可获取！");
        }else{
            map.put("patroltypeList", list);
        }
        map.put("flag",flag);
        return map;
    }


    /**
     * 添加或修改区域类别
     * Create by song on 2018-03-28 14:23:09
     */
    @RequestMapping("/setAreatype")
    public Map setAreatype(PPatroltypeCustom ptype){
        Boolean flag = true;
        String message  = "操作成功！";
        Map<String,Object> map = new HashMap<>();
        if(0==service.setAreatype(ptype)){
            flag = false;
            message = "操作失败！";
        }
        map.put("flag",flag);
        map.put("message",message);
        return map;
    }


    /**
     * 获取区域类型根据Id
     * Create by song on 2018-03-29 16:24:39
     */
    @RequestMapping("/getPatroltypeById/{id}")
    public Map getPatroltypeById(@PathVariable Integer id){
        Boolean flag = true;
        Map<String,Object> map = new HashMap<>();
        PPatroltype type = service.getPatroltypeById(id);
        if (null == type) {
            flag  = false;
            map.put("message","获取不到信息！");
        }else{
            map.put("patroltype",type);
        }
        map.put("flag",flag);
        return map;
    }


    /**
     * 删除区域类型
     * Create by song on 2018-03-28 14:31:38
     */
    @RequestMapping("/deleteAreatypeById/{id}")
    public Map deleteAreatypeById(@PathVariable Integer id){
        Boolean flag = true;
        String message = "删除成功！";
        Map<String,Object> map = new HashMap<>();
        if (0==service.deletePatroltypeById(id)) {
            flag = false;
            message = "删除失败！";
        }
        map.put("flag",flag);
        map.put("message",message);
        return map;
    }


}
