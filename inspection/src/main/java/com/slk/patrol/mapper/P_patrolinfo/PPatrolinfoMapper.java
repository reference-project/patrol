package com.slk.patrol.mapper.P_patrolinfo;

import com.slk.patrol.model.P_patrolinfo.PPatrolinfo;
import com.slk.patrol.framework.util.MyMapper;
import com.slk.patrol.model.P_patrolinfo.PPatrolinfoCustom;

import java.util.List;

public interface PPatrolinfoMapper extends MyMapper<PPatrolinfo> {

    public List<PPatrolinfoCustom> getPPatrolinfoListByInfo(PPatrolinfoCustom info);

    public List<PPatrolinfoCustom> getPatrolinfoByRecord(PPatrolinfoCustom info);

    public List<PPatrolinfoCustom> getPatrolinfoByQrId(Integer qrId);
}