package com.slk.patrol.model.P_patroltype;

import lombok.Data;
import java.util.Date;
import javax.persistence.Id;

@Data
public class PPatroltype{
    @Id
    private Integer id;

    /* 名称 */
    private String name;

    /* 描述 */
    private String descr;

    /* 图片url */
    private String url;

    /* Modify_Date */
    private Date modify_date;

}