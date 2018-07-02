package com.slk.workstask.mapper.Wt_position;

import com.slk.workstask.framework.util.MyMapper;
import com.slk.workstask.model.Wt_position.WtPosition;

public interface WtPositionMapper  extends MyMapper<WtPosition> {
    /**
     * 赵福岭
     *更新数据
     * @param wtPosition
     * @return
     */
    int updatePosition(WtPosition wtPosition);

    /**
     * 赵福岭
     * 插入数据
     * @param wtPosition
     * @return
     */
    int insertPosition(WtPosition wtPosition);

}
