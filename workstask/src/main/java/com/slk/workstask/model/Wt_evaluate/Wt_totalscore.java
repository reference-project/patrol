package com.slk.workstask.model.Wt_evaluate;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class Wt_totalscore {
    @Id
    private Integer id;
    private Integer hospitalid;
    private String hospital;
    private String rolename;
    //总分
    private Integer iscore;
    //建议
    private String suggestion;
    //不足
    private String existingproblems;
    private String imgurl;
    private Date createtime;
}
