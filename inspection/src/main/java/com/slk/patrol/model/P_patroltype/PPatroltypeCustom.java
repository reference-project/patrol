package com.slk.patrol.model.P_patroltype;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import java.util.Date;

@Data
public class PPatroltypeCustom extends PPatroltype {
    /* 文件信息 */
    private MultipartFile multipartFile;
}