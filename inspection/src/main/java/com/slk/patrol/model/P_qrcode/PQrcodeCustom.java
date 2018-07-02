package com.slk.patrol.model.P_qrcode;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class PQrcodeCustom extends PQrcode {

    /* 父名Id */
    private String parentId;
    /* 父名 */
    private String parentName;

    /* 区域名 */
    private String regionName;

    /*类型名*/
    private String typeName;
}