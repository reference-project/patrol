package com.slk.workstask.contoller;

import com.slk.workstask.model.Wt_release.WtRelease;
import com.slk.workstask.service.Wt_ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wtrelease")
public class WtReleaseController {
    @Autowired
    private Wt_ReleaseService service;

    @RequestMapping("/selectReleaseAll")
    public Map<String,Object> selectReleaseAll(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("flag",true);
        map.put("releaseList",service.selectReleaseAll());
        return map;
    }
    @RequestMapping("/insertAndUpdateRelease")
    public Map<String,Object> insertAndUpdateRelease(WtRelease wtRelease){
        Map<String,Object> map=new HashMap<String,Object>();
        //wtRelease.setCreatedate(new Date());
        map.put("flag",service.insertAndUpdateRelease(wtRelease));
        return map;
    }
}
