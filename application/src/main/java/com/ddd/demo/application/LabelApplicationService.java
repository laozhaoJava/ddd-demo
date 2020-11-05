package com.ddd.demo.application;

import com.ddd.demo.command.CreateLabelCommand;
import com.ddd.demo.entity.Label;
import com.ddd.demo.repository.LabelRepository;
import com.shinemo.util.ConvertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LabelApplicationService {

    private LabelRepository labelRepository;


    public LabelApplicationService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public Long create(CreateLabelCommand createLabelCommand) {
        //查询所有标签
        List<Label> labels = labelRepository.find();
        Label label = new Label(labels);
        //领域执行：业务支撑
        label.create(createLabelCommand);
        //执行insert
        BeanUtils.copyProperties(createLabelCommand,label);
        return labelRepository.insert(label);
    }

}
