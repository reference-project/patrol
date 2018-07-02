package com.slk.patrol.mapper.P_region;

import com.slk.patrol.framework.util.MyMapper;
import com.slk.patrol.model.P_region.PRegion;

import java.util.List;

public interface PRegionMapper extends MyMapper<PRegion> {
    /**
     * 通过父类id查询下级区域
     * @param parent_id
     * @return
     */
    public List<PRegion> selectAllRegionByParentId(Integer parent_id);
    /**
     * 查询所有下级区域
     * @return
     */
    public List<PRegion> selectAllRegion();

    public int addRegion(PRegion region);

    public int deleteRegion(Integer id);

    public int updateRegion(PRegion region);

    public PRegion getRegionById(Integer id);


}