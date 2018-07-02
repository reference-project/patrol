package com.slk.workstask.mapper.Wt_release;

import com.slk.workstask.framework.util.MyMapper;
import com.slk.workstask.model.Wt_release.WtRelease;
import java.util.List;

public interface WtReleaseMapper extends MyMapper<WtRelease> {
    /**
     * 查询启用状态的数据
     * @return
     */
    List<WtRelease> selectReleaseByState();

    /**
     * 查询全部数据
     * @return
     */
    List<WtRelease> selectReleaseAll();

    /**
     * 更新数据
     * @param wtRelease
     * @return
     */
    int updateRelease(WtRelease wtRelease);

    /**
     *新增数据
     * @param wtRelease
     * @return
     */
    int insertRelease(WtRelease wtRelease);
}
