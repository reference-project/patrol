package com.slk.patrol.model.P_qrcode;

import lombok.Data;
import java.util.Date;
import javax.persistence.Id;

@Data
public class PQrcode{
    @Id
    private Integer id;

    /* url */
    private String url;

    /* 备注 */
    private String remark;

    /* 巡查类别id */
    private Integer patroltype_id;

    /* 所属区域id */
    private Integer region_id;

    /* 访问路径 */
    private String path;

    /* 描述 */
    private String descr;

    /* drive_X */
    private String drive_x;

    /* drive_Y */
    private String drive_y;

    /* drive_ID */
    private Integer drive_id;

    /* Modify_Date */
    private Date modify_date;

}