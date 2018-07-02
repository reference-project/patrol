package com.slk.workstask.model.Wt_evaluate;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class Wt_score {
    @Id
    private Integer id;
    private Integer typeid;
    private String typename;
    private String rolename;
    private Integer totalscoreid;
    private Integer score;
    private String scoreinfo;
    private Date createtime;
}
