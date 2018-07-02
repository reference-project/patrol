package com.slk.workstask.model.Wt_repair;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class WtRepair {
    @Id
    private Integer id;

    /* 报修内容 */
    private String repaircontent;

    /* 报修简述 */
    private String repairsketch;

    /* 维修类型 */
    private String repairtype;

    /* 维修设备 */
    private String repairdevice;

    /* 任务状态 */
    private Integer taskstate;

    /* 维修前图片 */
    private String beforeimg;

    /* 维修后图片 */
    private String afterimg;

    /* 报修人 */
    private String repairname;

    /* 接报人 */
    private String reportername;

    /* 接报人职务 */
    private String reportjob;

    /* 分配时间 */
    private Date allottime;

    /* 到达时间 */
    private Date arrivetime;

    /* 维修时长 */
    private Double hours;

    /* 完成时间 */
    private Date completetime;

    /* 报修时间（创建时间） */
    private Date createtime;

}