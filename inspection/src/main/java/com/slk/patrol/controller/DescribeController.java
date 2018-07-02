package com.slk.patrol.controller;

import com.slk.patrol.model.P_describe.PDescribe;
import com.slk.patrol.model.P_describe.PDescribeCustom;
import com.slk.patrol.model.P_patroltype.PPatroltype;
import com.slk.patrol.service.DescribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/describe")
public class DescribeController {


    @Autowired
    private DescribeService service;



    /**
     * 获取描述集合
     * Create by song on 2018-03-28 14:11:16
     */
    @RequestMapping("/getDescribeList")
    public Map getDescribeList(){
        Boolean flag = true;
        Map<String,Object> map = new HashMap<>();
        List<PDescribeCustom> list = service.getDescribeList();
        if (list.size() == 0){
            flag = false;
            map.put("message","获取不到信息！");
        }else{
            map.put("describeList", list);
        }
        map.put("flag",flag);
        return map;
    }


    /**
     * 获取类型集合
     * Create by song on 2018-04-10 16:18:06
     */
    @RequestMapping("/getPatroltypeList")
    public Map getPatroltypeList(){
        Boolean flag = true;
        Map<String,Object> map = new HashMap<>();
        List<PPatroltype> list = service.getPatroltypeAll();
        if (list.size() == 0){
            flag = false;
            map.put("message","获取不到信息！");
        }else{
            map.put("typeList", list);
        }
        map.put("flag",flag);

        return map;
    }




    /**
     * 添加或修改描述
     * Create by song on 2018-03-28 14:23:09
     */
    @RequestMapping("/setDescribe")
    public Map setDescribe(PDescribe describe){
        Boolean flag = true;
        String message  = "操作成功！";
        Map<String,Object> map = new HashMap<>();
        if(0==service.setDescribe(describe)){
            flag = false;
            message = "操作失败！";
        }
        map.put("flag",flag);
        map.put("message",message);
        return map;
    }


    /**
     * 获取描述根据Id
     * Create by song on 2018-03-29 16:24:39
     */
    @RequestMapping("/getDescribeById/{id}")
    public Map getdescribeById(@PathVariable Integer id){
        Boolean flag = true;
        Map<String,Object> map = new HashMap<>();
        PDescribe type = service.getDescribeById(id);
        if (null == type) {
            flag  = false;
            map.put("message","获取不到信息！");
        }else{
            map.put("describe",type);
        }
        map.put("flag",flag);
        return map;
    }


    /**
     * 删除描述
     * Create by song on 2018-03-28 14:31:38
     */
    @RequestMapping("/deleteDescribeById/{id}")
    public Map deleteDescribeById(@PathVariable Integer id){
        Boolean flag = true;
        String message = "删除成功！";
        Map<String,Object> map = new HashMap<>();
        if (0==service.deleteDescribeById(id)) {
            flag = false;
            message = "删除失败！";
        }
        map.put("flag",flag);
        map.put("message",message);
        return map;
    }




}
