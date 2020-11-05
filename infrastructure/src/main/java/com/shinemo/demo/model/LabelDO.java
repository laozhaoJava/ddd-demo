package com.shinemo.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class LabelDO {

    private Long id;
    private String name;
    private Long classId;
    private Integer sort;
    private Integer useCount;
    private String creatorUserId;
    private String creatorUserName;
    private Integer status;
    private Date gmtCreate;
    private Date gmtModified;
}
