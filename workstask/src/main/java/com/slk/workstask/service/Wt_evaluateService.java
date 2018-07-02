package com.slk.workstask.service;

import com.alibaba.druid.support.json.JSONParser;
import com.slk.workstask.framework.util.FileUtil;
import com.slk.workstask.mapper.Wt_score.WtScoreMapper;
import com.slk.workstask.mapper.Wt_totalscore.WtTotalscoreMapper;
import com.slk.workstask.model.Wt_evaluate.Evaluate;
import com.slk.workstask.model.Wt_evaluate.Wt_score;
import com.slk.workstask.model.Wt_evaluate.Wt_totalscore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@Transactional
public class Wt_evaluateService {
    @Autowired
    private WtScoreMapper sorceMapper;
    /*@Autowired
    private WtTotalscoreMapper totalScoreMapper;*/
    public boolean submitContent(Evaluate evaluate){
        boolean flag=false;
        int count=0;
        /*Wt_totalscore totalscore=new Wt_totalscore();
        totalscore.setHospital(evaluate.getHospital());
        totalscore.setIscore(evaluate.getIScore());
        totalscore.setExistingproblems(evaluate.getExistingproblems());
        totalscore.setRolename(evaluate.getRole());
        totalscore.setSuggestion(evaluate.getSuggestion());
        totalscore.setCreatetime(new Date());
        if(evaluate.getFile()!=null&&evaluate.getFile().getSize()>0){
            MultipartFile file = evaluate.getFile();
            totalscore.setImgurl(FileUtil.FileImgUp(file, "workstask/evaluate"));
        }
        count=totalScoreMapper.insertTotalSocre(totalscore);*/
        JSONParser parser = new JSONParser(evaluate.getArrList());
        List<Object> objectList=parser.parseArray();
        List<Wt_score> scoreList=new ArrayList<>();
        for(int i=0,x=objectList.size();i<x;i++){
            Map<String,Object> map=(HashMap<String,Object>) objectList.get(i);
            Wt_score wtScore=new Wt_score();
            wtScore.setScore(null!=map.get("score")?Integer.parseInt(map.get("score").toString()):0);
            wtScore.setScoreinfo(null!=map.get("name")?map.get("name").toString():"");
            wtScore.setTypeid(null!=map.get("parentId")?Integer.parseInt(map.get("parentId").toString()):null);
            wtScore.setTypename(null!=map.get("parentName")?map.get("parentName").toString():"");
            wtScore.setCreatetime(new Date());
            wtScore.setRolename(evaluate.getRole());
            //wtScore.setTotalscoreid(totalscore.getId());
            scoreList.add(wtScore);
        }
        if(scoreList.size()>0){
            count=sorceMapper.insertList(scoreList);
        }
        return count>0;
    }
}
