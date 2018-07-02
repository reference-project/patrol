package com.slk.patrol.model.P_user;

import lombok.Data;

import javax.persistence.Id;


@Data
public class PUser {
    @Id
    private Integer id;

    /* username */
    private String username;

    /* passowrd */
    private String passowrd;

}