package com.slk.workstask.model.Wt_position;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class WtPosition {
    @Id
    private Integer id;
    private String position;
    private Date createdate;
    private String createuser;
}
