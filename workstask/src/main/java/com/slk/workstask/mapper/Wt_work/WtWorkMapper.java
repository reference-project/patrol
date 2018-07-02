package com.slk.workstask.mapper.Wt_work;

import com.slk.workstask.framework.util.MyMapper;
import com.slk.workstask.model.Wt_work.WtWork;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "WtWorkMapper")
public interface WtWorkMapper extends MyMapper<WtWork> {
    List<WtWork> selectAllWorks();
}