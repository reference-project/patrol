package com.slk.patrol.model.P_customer;

import lombok.Data;

import java.util.Date;

@Data
public class PCustomer {
    /* id */
    private Integer id;

    /* 用户姓名 */
    private String name;

    /* 电话 */
    private String phone;

    /* 密码 */
    private String password;

    /* Modify_Date */
    private Date modify_date;

    /* drive_X */
    private String drive_x;

    /* drive_Y */
    private String drive_y;

    /* drive_ID */
    private Integer drive_id;

}