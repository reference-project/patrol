package com.slk.workstask.model.Wt_content;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class WtContent {
    @Id
    private Integer id;
    private String parentId;
    private String name;
    private int level;
    private Integer sort;
    private Integer score;
    private Date createtime;
    private String createuser;
    private int isFlag;
}