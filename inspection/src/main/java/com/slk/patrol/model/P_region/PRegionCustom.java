package com.slk.patrol.model.P_region;

import com.slk.patrol.model.P_patrolinfo.PPatrolinfo;
import com.slk.patrol.model.P_patroltype.PPatroltype;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PRegionCustom extends PRegion {

    /* 区域 */
    private List<PRegion> pregion;
    /* 区域类型 */
    private List<PPatroltype> PPatroltype;

}