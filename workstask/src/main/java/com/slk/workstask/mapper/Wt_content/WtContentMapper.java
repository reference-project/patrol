package com.slk.workstask.mapper.Wt_content;

import com.slk.workstask.framework.util.MyMapper;
import com.slk.workstask.model.Wt_content.WtContent;

import java.util.List;

public interface WtContentMapper extends MyMapper<WtContent> {
     List<WtContent> selectContent();

     List<WtContent> selectContentByParentId(Integer id);

     int updateContent(WtContent wtContent);

     int insertContent(WtContent wtContent);

}