package com.slk.patrol.mapper.P_describe;

import com.slk.patrol.framework.util.MyMapper;
import com.slk.patrol.model.P_describe.PDescribe;
import com.slk.patrol.model.P_describe.PDescribeCustom;

import java.util.List;

public interface PDescribeMapper extends MyMapper<PDescribe> {

    public List<PDescribeCustom> getDescribeList();

    public List<PDescribe> getDescribeByTypeID(Integer typeId);

}