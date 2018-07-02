package com.slk.workstask.contoller;

import com.slk.workstask.model.Wt_position.WtPosition;
import com.slk.workstask.model.Wt_user.WtUser;
import com.slk.workstask.service.Wt_PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private Wt_PositionService service;

    @RequestMapping("/selectPositionAll")
    public Map<String,Object> selectPositionAll(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("flag",true);
        map.put("positionList",service.selectPostionAll());
        return map;
    }

    @RequestMapping("/modifyPosition")
    public Map<String,Object> modifyPosition(WtPosition wtPosition, HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        wtPosition.setCreatedate(new Date());
        WtUser u=(WtUser)session.getAttribute("user");
        wtPosition.setCreateuser(u.getUsername());
        map.put("flag",service.insertAndUpdatePostion(wtPosition));
        return map;
    }

    @RequestMapping("/deletePosition")
    public Map<String,Object> deletePosition(WtPosition wtPosition){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("flag",service.deletePostion(wtPosition));
        return map;
    }
}
