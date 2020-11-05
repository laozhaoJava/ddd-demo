package com.ddd.demo.command;

import lombok.Data;

@Data
public class CreateLabelCommand {
    private String name;
    private Long classId;
    private Integer sort;
    private Integer useCount;
    private String creatorUserId;
    private String creatorUserName;
    private Integer status;
}
