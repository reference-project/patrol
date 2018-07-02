package com.slk.workstask.model.Wt_work;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class WtWork{
    @Id
    private Integer id;

    /*  */
    private String phone;

    /*  */
    private String workname;

    /* 男女 */
    private String sex;

    /* 身份证 */
    private String idcode;

    private Integer age;

    private Integer employment;//从业年限

    private String speciality;//擅长专业

    private Integer train;//是否培训

    private String remarks;//备注

    private String worknumber;//工号

    private Date createdate;

    private String createuser;

}