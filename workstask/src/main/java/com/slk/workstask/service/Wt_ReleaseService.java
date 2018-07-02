package com.slk.workstask.service;

import com.slk.workstask.mapper.Wt_release.WtReleaseMapper;
import com.slk.workstask.model.Wt_release.WtRelease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Wt_ReleaseService {
    @Autowired
    private WtReleaseMapper mapper;

    public boolean insertAndUpdateRelease(WtRelease wtRelease){
        int count=0;
        if(wtRelease.getId()!=null){
            count=mapper.updateRelease(wtRelease);
        }else{
            count=mapper.insertRelease(wtRelease);
        }
        return count>0;
    }

    public List<WtRelease> selectReleaseByState(){
        return mapper.selectReleaseByState();
    }
    public List<WtRelease> selectReleaseAll(){
        return mapper.selectReleaseAll();
    }
}
