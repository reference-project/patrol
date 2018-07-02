package com.slk.workstask.contoller;

import com.slk.workstask.model.Wt_repair.WtRepairCustom;
import com.slk.workstask.service.Wt_repairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    Wt_repairService service;


    /**
     *
     * 获取所有任务
     * Create by song-xl on 2018-04-30 09:04:55
     */
    @RequestMapping("selectRepairAll")
    public Map selectRepairAll(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("repairs",service.selectRepairAll());
        return map;
    }


    /**
     *
     * 添加或者修改 任务
     * Create by song-xl on 2018-04-30 09:18:48
     */
    @RequestMapping("setRepairByInfo")
    public Map setRepairByInfo(WtRepairCustom repair){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("flag",service.setRepairByInfo(repair));
        return map;
    }


    /**
     *
     * 添加或者修改 任务
     * Create by song-xl on 2018-04-30 09:18:48
     */
    @RequestMapping("setRepairStateById/{id}/{state}/{Repairmoney}")
    public Map setRepairStateById(@PathVariable Integer id, @PathVariable Integer state, @PathVariable Double Repairmoney){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("flag",service.setRepairStateById(id,state,Repairmoney));
        return map;
    }



    /**
     *
     * 删除任务根据Id
     * Create by song-xl on 2018-04-30 09:32:27
     */
    @RequestMapping("deleteRepairById/{id}")
    public Map deleteRepairById(@PathVariable Integer id){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("flag",service.deleteRepairById(id));
        return map;
    }



    /**
     *
     * 获取单个任务根据Id
     * Create by song-xl on 2018-04-30 09:32:27
     */
    @RequestMapping("selectRepairById/{id}")
    public Map selectRepairById(@PathVariable Integer id){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("repair",service.selectRepairById(id));
        return map;
    }


}
