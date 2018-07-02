package com.slk.workstask.model.Wt_repair;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class WtRepairCustom extends WtRepair {

    //图片数组
    private MultipartFile[] imgArr;
}