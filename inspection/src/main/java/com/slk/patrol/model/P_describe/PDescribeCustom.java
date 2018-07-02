package com.slk.patrol.model.P_describe;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class PDescribeCustom extends PDescribe {
    /* 类型名称 */
    private String typeName;


}