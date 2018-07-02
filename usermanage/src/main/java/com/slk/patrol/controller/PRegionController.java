package com.slk.patrol.controller;

import com.slk.patrol.model.P_region.PRegion;
import com.slk.patrol.service.P_regionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("region")
public class PRegionController {
    @Autowired
    P_regionService service;

    /**
     * 父类区域列表（页面单选框）
     * @return 区域列表
     */
    @RequestMapping("/selectRegionParent")
    public ConcurrentHashMap selectRegionParent(){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        List<PRegion> list=service.selectAllRegion(0);
        boolean flag=false;
        String message="未查询到信息";
        if(list.size()!=0){
            flag=true;
            message="查询到信息";
        }
        map.put("regionList",list);
        map.put("message",message);
        map.put("flag",flag);
        return map;
    }

    //查询所有  下级区域
    @RequestMapping("/selectAllRegion")
    public ConcurrentHashMap selectAllRegion(){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        List<PRegion> list=service.selectAllRegion();
        boolean flag=false;
        String message="未查询到信息";
        if(list.size()!=0){
            flag=true;
            message="查询到信息";
        }
        map.put("flag",flag);
        map.put("message",message);
        map.put("regionList",list);
        return map;
    }

    /**
     * 通过parent_id查询所属区域列表
     * @return 区域列表
     */
    @RequestMapping("/selectRegionByParentId")
    public ConcurrentHashMap selectRegionByParentId(Integer id){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        List<PRegion> list=service.selectAllRegion(id);
        String message="未查询到信息";
        boolean flag=false;
        if(list.size()!=0){
            flag=true;
            message="查询到信息";
        }
        map.put("flag",flag);
        map.put("message",message);
        map.put("regionList",list);
        return map;
    }

    /**
     * 新增区域
     * @param region
     * @return 是否添加成功
     */
    @RequestMapping("/addRegion")
    public ConcurrentHashMap addRegion(PRegion region){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        //默认区域等级为2
        region.setLevel(2);
        boolean flag=service.addRegion(region);
        String message="添加失败";
        if(flag){
           message="添加成功";
        }
        map.put("message",message);
        map.put("flag",flag);
        return map;
    }

    /**
     * 通过修改区域
     * @return
     */
    @RequestMapping("/updateRegion")
    public ConcurrentHashMap updateRegion(PRegion region){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        boolean flag=service.updateRegion(region);
        String message="修改失败";
        if(flag){
            message="修改成功";
        }
        map.put("message",message);
        map.put("flag",flag);
        return map;

    }

    /**
     * 删除区域
     * @param  id
     * @return 是否成功
     */
    @RequestMapping("/deleteRegion")
    public ConcurrentHashMap deleteRegion(Integer id){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        boolean flag=service.deleteRegion(id);
        String message="删除失败";
        if(flag){
            message="删除成功";
        }
        map.put("message",message);
        map.put("flag",flag);
        return map;
    }

    /**
     * 通过id获取区域信息
     * @param id
     * @return
     */
    @RequestMapping("/getRegionById")
    public ConcurrentHashMap getRegionById(Integer id){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
        PRegion region=service.getRegionById(id);
        boolean flag=false;
        String message="查找区域失败";
        if(region!=null){
            flag=true;
            message="查找成功";
        }
        map.put("flag",flag);
        map.put("message",message);
        map.put("region",service.getRegionById(id));
        return map;
    }
}
