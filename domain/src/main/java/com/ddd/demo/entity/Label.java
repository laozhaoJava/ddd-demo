package com.ddd.demo.entity;

import com.ddd.demo.command.CreateLabelCommand;
import com.ddd.demo.common.AggregateRoot;
import com.ddd.demo.exception.LabelErrorCodes;
import com.shinemo.common.tools.exception.ApiException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

/**
 * domain层不依赖任何层
 */
@Data
@NoArgsConstructor
public class Label implements AggregateRoot {

    private Long id;
    private String name;
    private Long classId;
    private Integer sort;
    private Integer useCount;
    private String creatorUserId;
    private String creatorUserName;
    private Integer status;


    /**
     * 已存在的标签名
     */
    private List<Label> existedLabel;

    public Label(List<Label> labels) {
        this.existedLabel = labels;
    }


    public void create(CreateLabelCommand createLabelCommand) {

        //非空字段判断
        Assert.notNull(createLabelCommand.getName(),"name is null");
        Assert.notNull(createLabelCommand.getClassId(),"classId is null");
        Assert.notNull(createLabelCommand.getCreatorUserId(),"creator_user_id is null");
        Assert.notNull(createLabelCommand.getCreatorUserName(),"create_user_id is null");
        //判断是否有名字重复的标签
        if (!CollectionUtils.isEmpty(existedLabel)) {
            List<String> names = existedLabel.stream().map(Label::getName).collect(Collectors.toList());
            if (names.contains(createLabelCommand.getName())) {
                throw new ApiException(LabelErrorCodes.NAME_EXIST);
            }
        }
    }

}
