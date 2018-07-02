package com.slk.patrol.model.P_user_region;

import lombok.Data;

import java.util.Date;

@Data
public class PUserRegion {
    /* id */
    private Integer id;

    /* 用户id */
    private Integer custom_id;

    /* 区域id */
    private Integer region_id;

    /* Modify_Date */
    private Date modify_date;

    /* 用户姓名 */
    private String customname;

    /* 区域名称 */
    private String regionname;

}