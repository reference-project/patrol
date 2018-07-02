package com.slk.workstask.contoller;


import com.slk.workstask.model.Wt_task.WtTask;
import com.slk.workstask.model.Wt_task.WtTaskCustom;
import com.slk.workstask.model.Wt_user.WtUser;
import com.slk.workstask.service.Wt_taskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/task")
public class TaskContoller {


    @Autowired
    Wt_taskService service;

    
    /**
     *
     * 获取所有任务
     * Create by song-xl on 2018-04-30 09:04:55
     */
    @RequestMapping("selectTaskAll")
    public Map selectTaskAll(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("tasks",service.selectTaskAll(0));
        map.put("works",service.selectWorkAll());
        return map;
    }

    
    /**
     *
     * 添加或者修改 任务
     * Create by song-xl on 2018-04-30 09:18:48
     */
    @RequestMapping("setTaskByInfo")
    public Map setTaskByInfo(WtTaskCustom task,String imgurls,HttpSession session){
        Map<String,Object> map = new HashMap<String, Object>();
        WtUser user=(WtUser) session.getAttribute("user");
        task.setCreateuser(user.getUsername());
        map.put("flag",service.setTaskByInfo(task,imgurls));
        return map;
    }


    /**
     *
     * 添加或者修改 任务
     * Create by song-xl on 2018-04-30 09:18:48
     */
    @RequestMapping("setTaskStateById/{id}/{state}/{taskmoney}")
    public Map setTaskStateById(@PathVariable Integer id,@PathVariable Integer state,@PathVariable Double taskmoney,HttpSession session){
        Map<String,Object> map = new HashMap<String, Object>();
        WtTask task = service.selectTaskById(id);
        WtUser user= (WtUser)session.getAttribute("user");
        task.setCreateuser(user.getUsername());
        map.put("flag",service.setTaskStateById(task,state,taskmoney));
        return map;
    }



    /**
     *
     * 删除任务根据Id
     * Create by song-xl on 2018-04-30 09:32:27
     */
    @RequestMapping("deleteTaskById/{id}")
    public Map deleteTaskById(@PathVariable Integer id){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("flag",service.deleteTaskById(id));
        return map;
    }



    /**
     *
     * 获取单个任务根据Id
     * Create by song-xl on 2018-04-30 09:32:27
     */
    @RequestMapping("selectTaskById/{id}")
    public Map selectTaskById(@PathVariable Integer id){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("flag",true);
        map.put("task",service.selectTaskById(id));
        return map;
    }



}
