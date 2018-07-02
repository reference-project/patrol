package com.slk.workstask.service;

import com.slk.workstask.mapper.Wt_position.WtPositionMapper;
import com.slk.workstask.model.Wt_position.WtPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Wt_PositionService {
    @Autowired
    private WtPositionMapper mapper;

    /**
     * 赵福岭
     * 新增、修改位置
     * @param wtPosition
     * @return
     */
    public boolean insertAndUpdatePostion(WtPosition wtPosition){
        int count=0;
        if(wtPosition.getId()!=null){
            count=mapper.updatePosition(wtPosition);
        }else{
            count=mapper.insertPosition(wtPosition);
        }
        return count>0;
    }

    /**
     * 赵福岭
     * 删除位置信息
     * @param wtPosition
     * @return
     */
    public boolean deletePostion(WtPosition wtPosition){
        return mapper.delete(wtPosition)>0;
    }

    /**
     * 赵福岭
     * 查询全部位置信息
     * @return WtPosition
     */
    public List<WtPosition> selectPostionAll(){
        return mapper.selectAll();
    }
}
