package com.slk.workstask.model.Wt_evaluate;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Evaluate {
    private String hospital;
    private String role;
    private Integer iScore;
    private String arrList;
    private String suggestion;
    private String existingproblems;
    private MultipartFile file;
}
