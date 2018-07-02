package com.slk.patrol.model.P_region;

import lombok.Data;

import java.util.Date;

@Data
public class PRegion {
    /* id */
    private Integer id;

    /* 区域名称 */
    private String name;

    /* 上级区域 */
    private Integer parent_id;

    /* 区域级别 */
    private Integer level;

    /* Modify_Date */
    private Date modify_date;

    /* drive_X */
    private String drive_x;

    /* drive_Y */
    private String drive_y;

    /* drive_ID */
    private Integer drive_id;

}