package com.slk.patrol.service;

import com.slk.patrol.framework.util.FileUtil;
import com.slk.patrol.mapper.P_patroltype.PPatroltypeMapper;
import com.slk.patrol.model.P_patroltype.PPatroltype;
import com.slk.patrol.model.P_patroltype.PPatroltypeCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AreatypeService {


    @Autowired
    private PPatroltypeMapper typemapper;

    /**
     * 获取所有区域类型
     * Create by song on 2018-03-29 16:21:30
     */
    public List<PPatroltype> getAreatypeList(){
        return typemapper.selectAll();
    }


    /**
     * 添加或修改区域类别
     * Create by song on 2018-03-28 14:23:09
     */
    public Integer setAreatype(PPatroltypeCustom ptype){
        Integer count = 0;
        if (null == ptype.getId()) {
            ptype.setUrl(FileUtil.FileImgUp(ptype.getMultipartFile(), "patrol/Patroltype"));
            count = typemapper.insertSelective(ptype);
        }else{
            if(null != ptype.getMultipartFile())
                ptype.setUrl(FileUtil.FileImgUp(ptype.getMultipartFile(), "patrol/Patroltype"));
            count = typemapper.updateByPrimaryKeySelective(ptype);
        }
        return count;
    }


    /**
     * 获取区域类型根据id
     * Create by song on 2018-03-29 16:22:48
     */
    public PPatroltype getPatroltypeById(Integer id){
        return typemapper.selectByPrimaryKey(id);
    }
    /**
     * 删除区域类型根据id
     * Create by song on 2018-03-29 16:36:16
     */
    public Integer deletePatroltypeById(Integer id){
        return typemapper.deleteByPrimaryKey(id);
    }

}
