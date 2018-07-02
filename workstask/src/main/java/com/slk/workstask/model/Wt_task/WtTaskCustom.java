package com.slk.workstask.model.Wt_task;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class WtTaskCustom extends WtTask {

    /* 文件信息 */
    private MultipartFile[] multipartFile;
    private String[] baseFile;
/*
    private String starttimeStr;

    private String endtimeStr;*/

}