package com.slk.workstask.model.Wt_user;

import lombok.Data;

import javax.persistence.Id;

@Data
public class WtUser {
    @Id
    private Integer id;
    private String username;
    private String password;
}
