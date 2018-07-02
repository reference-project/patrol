package com.slk.workstask.mapper.Wt_task;

import com.slk.workstask.model.Wt_task.WtTask;
import com.slk.workstask.framework.util.MyMapper;
import com.slk.workstask.model.Wt_task.WtTaskCustom;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "wttaskmapper")
public interface WtTaskMapper extends MyMapper<WtTask> {

    List<WtTaskCustom> selectTaskAll(Integer type);

    WtTaskCustom selectTaskByWorkNumber(String taskcode);

}