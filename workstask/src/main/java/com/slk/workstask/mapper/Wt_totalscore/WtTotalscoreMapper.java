package com.slk.workstask.mapper.Wt_totalscore;

import com.slk.workstask.model.Wt_evaluate.Wt_totalscore;
import tk.mybatis.mapper.common.Mapper;

public interface WtTotalscoreMapper extends Mapper<Wt_totalscore>{

     int insertTotalSocre(Wt_totalscore totalscore);
}
