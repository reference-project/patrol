package com.slk.workstask.contoller;

import com.slk.workstask.model.Wt_user.WtUser;
import com.slk.workstask.model.Wt_work.WtWork;
import com.slk.workstask.service.Wt_workService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/works")
public class WorkContoller {
    @Autowired
    private Wt_workService service;

    @RequestMapping("/selectAllWorks")
    public Map selectAllWorks(){
        Map<String,Object> map=new HashMap<String,Object>();
        List<WtWork> list=service.selectAllWorks();
        map.put("worksList",list);
        return map;
    }


    @RequestMapping("/ModifyToWorks")
    public Map ModifyToWorks(WtWork work, HttpSession session){
        Map<String,Object> map= new ConcurrentHashMap<>();
        work.setCreatedate(new Date());
        WtUser u=(WtUser)session.getAttribute("user");
        work.setCreateuser(u.getUsername());
        map.put("flag",service.addOrUpWorks(work));
        return map;
    }

    @RequestMapping("/romveWokrs/{id}")
    public Map romveWokrs(@PathVariable @NotNull Integer id)
    {
        Map<String,Object> map= new ConcurrentHashMap<>();
        map.put("flag",service.deleteWork(id));
        return map;
    }

}
