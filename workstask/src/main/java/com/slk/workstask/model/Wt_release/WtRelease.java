package com.slk.workstask.model.Wt_release;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class WtRelease {
    @Id
    private Integer id;
    private String phone;
    private String name;
    private String position;
    private Integer sex;
    private Integer age;
    private String task;
    private Integer state;
    private String demanddate;
    private Date createdate;
    private Integer nursingid;
    private String nursinglevel;

}
