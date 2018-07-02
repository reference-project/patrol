package com.slk.patrol.model.P_patrolinfo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class PPatrolinfoCustom extends PPatrolinfo {
    /* 开始时间 */
    private String beginDate;
    /* 结束时间 */
    private String endDate;
    /* 文件信息 */
    private String[] files;
    /* 区域 */
    private String regionName;
    /* 类型 */
    private String typeName;

    private Integer normal2;
}