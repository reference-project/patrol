package com.slk.patrol.mapper.P_region;

import com.slk.patrol.framework.util.MyMapper;
import com.slk.patrol.model.P_region.PRegion;
import com.slk.patrol.model.P_region.PRegionCustom;

import java.util.List;

public interface PRegionMapper extends MyMapper<PRegion> {

    public List<PRegionCustom> getRegionlevel();

    public List<PRegion> getRegionBylevel(Integer level);
}