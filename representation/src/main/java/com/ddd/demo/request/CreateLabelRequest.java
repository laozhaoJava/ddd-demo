package com.ddd.demo.request;

import lombok.Data;


@Data
public class CreateLabelRequest {
    private String name;
    private Long classId;
    private Integer sort;
    private Integer useCount;
    private String creatorUserId;
    private String creatorUserName;
    private Integer status;
}
