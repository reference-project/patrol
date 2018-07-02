package com.slk.workstask.contoller;


import com.slk.workstask.model.Wt_content.WtContent;
import com.slk.workstask.model.Wt_evaluate.Evaluate;
import com.slk.workstask.service.Wt_ContentService;
import com.slk.workstask.service.Wt_evaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/phone/Content")
public class PhoneContentController {
    @Autowired
    private Wt_evaluateService service;
    @Autowired
    private Wt_ContentService contentService;

    @RequestMapping("submitContent")
    public Map submitContent(Evaluate e){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("flag",service.submitContent(e));
        return map;
    }

    /**
     * 手机端满意度调查
     */
    @RequestMapping("/selectContent")
    public Map selectContent(){
        Map<String,Object> map=new HashMap<String,Object>();
        boolean flag=false;
        List<WtContent> contentList=contentService.selectContent();
        if(contentList.size()>0){
            flag=true;
            map.put("contentList",contentList);
        }
        map.put("flag",flag);
        return map;
    }
}
