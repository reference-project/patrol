package com.slk.workstask.model.Wt_task;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class WtTask{
    @Id
    private Integer id;

    /* 护工id */
    private Integer wordid;

    /* 护工姓名 */
    private String workname;

    /* 护工电话 */
    private String phone;

    /* 任务 */
    private String task;

    /* 合同 */
    private String contractpic;

    /* 天数 */
    private Double days;

    /* 小时 */
    private Integer hours;

    /* 护工工资 */
    private Double workmoney;

    /* 任务价格 */
    private Double taskmoney;

    /* 标准价格 */
    private Double standardmoney;

    /* 任务开始时间 */
    private Date starttime;

    /* 任务结束时间 */
    private Date endtime;

    /* 任务实际结束时间 */
    private Date realityendtime;

    /* 0-任务发布,1-任务进行中，2-任务完成 */
    private Integer states;

    /*0-护工任务发布，1-主管发布任务*/
    private Integer type;

    /* 创建时间 */
    private Date createtime;
    /*创建人*/
    private String createuser;

    private String taskcode;
}