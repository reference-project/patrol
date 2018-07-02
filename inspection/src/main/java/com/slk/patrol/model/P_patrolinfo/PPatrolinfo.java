package com.slk.patrol.model.P_patrolinfo;

import lombok.Data;
import java.util.Date;
import javax.persistence.Id;

@Data
public class PPatrolinfo{
    @Id
    private Integer id;

    /* 二维码id */
    private Integer qrcode_id;

    /* 是否合格 */
    private Integer normal;

    /* 图片url */
    private String url;

    /* 描述 */
    private String descr;

    /* drive_X */
    private String drive_x;

    /* drive_Y */
    private String drive_y;

    /* drive_ID */
    private Integer drive_id;

    /* 用户id */
    private Integer customer_id;

    /* 用户姓名 */
    private String customername;

    /* 用户电话 */
    private String customerphone;

    /* Modify_Date */
    private Date modify_date;

    /* 巡查类别id */
    private Integer patroltype_id;

    /* 所属区域id */
    private Integer region_id;

}