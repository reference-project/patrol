package com.slk.patrol.model.P_describe;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class PDescribe {
    @Id
    private Integer id;

    /* 描述名称 */
    private String name;

    /* 类型ID */
    private String patroltype_id;

    /* 更新时间 */
    private Date modify_date;

}