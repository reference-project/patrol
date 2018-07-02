package com.slk.workstask.contoller;


import com.slk.workstask.model.Wt_release.WtRelease;
import com.slk.workstask.model.Wt_task.WtTaskCustom;
import com.slk.workstask.service.Wt_PositionService;
import com.slk.workstask.service.Wt_ReleaseService;
import com.slk.workstask.service.Wt_taskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/release")
public class ReleaseController {

    @Autowired
    private Wt_ReleaseService service;

    @Autowired
    private Wt_taskService taskService;

    @Autowired
    private Wt_PositionService positionService;

    @RequestMapping("/selectPositionAll")
    public Map<String,Object> selectPositionAll(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("flag",true);
        map.put("positionList",positionService.selectPostionAll());
        return map;
    }

    /**
     *
     * 获取所有任务
     * 赵福岺
     */
    @RequestMapping("/selectTaskAll")
    public Map selectTaskAll(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("releaseList",service.selectReleaseByState());
        return map;
    }


    /**
     *
     * 上传合同
     * 赵福岺
     */
    @RequestMapping("/updateTaskPic")
    public Map updateTaskPic(WtTaskCustom task){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("flag",taskService.setTaskByInfo(task));
        return map;
    }

    /**
     *
     * 查询任务编号是否存在
     * 赵福岺
     */
    @RequestMapping("/selectTaskByTaskcode")
    public Map selectTaskByTaskcode(String taskcode){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("flag",taskService.selectTaskByTaskcode(taskcode));
        return map;
    }


    /**
     *
     * 添加或者修改 任务
     * Create by song-xl on 2018-04-30 09:18:48
     */
    @RequestMapping("/setTaskByInfo")
    public Map setTaskByInfo(WtRelease wtRelease){
        Map<String,Object> map = new HashMap<String, Object>();
        wtRelease.setCreatedate(new Date());
        wtRelease.setState(0);
        map.put("flag",service.insertAndUpdateRelease(wtRelease));
        return map;
    }


    /**
     * 短信发送
     * @param
     * @return
     */
    @RequestMapping("/addTaskByInfo")
    public void addTaskByInfo(String phone,String task){
        if(phone!=null&&task!=null&&!"".equals(phone)&&!"".equals(task)){
            WtRelease wtRelease=new WtRelease();
            wtRelease.setPhone(phone);
            wtRelease.setTask(task);
            wtRelease.setCreatedate(new Date());
            service.insertAndUpdateRelease(wtRelease);
        }
    }

}
