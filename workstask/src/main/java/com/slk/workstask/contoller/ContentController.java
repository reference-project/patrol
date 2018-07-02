package com.slk.workstask.contoller;

import com.slk.workstask.model.Wt_content.WtContent;
import com.slk.workstask.model.Wt_user.WtUser;
import com.slk.workstask.service.Wt_ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private Wt_ContentService service;
   @RequestMapping("/selectContent")
    public Map selectContent(){
       Map<String,Object> map=new HashMap<String,Object>();
       List<WtContent> contentList=service.selectContent();
       boolean flag=false;
       if(contentList.size()>0){
            flag=true;
            map.put("contentList",contentList);
       }
       map.put("flag",flag);
       return map;
    }

    @RequestMapping("/insertUpdateContent")
    public Map insertUpdateContent(WtContent content, HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        WtUser u=(WtUser)session.getAttribute("user");
        content.setCreateuser(u.getUsername());
        content.setCreatetime(new Date());
        map.put("flag",service.insertUpdateContent(content));
        return map;
    }
    @RequestMapping("/deleteContent/{id}")
    public Map deleteContent(@PathVariable Integer id){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("flag",service.deleteContent(id));
        return map;
    }

}
