package com.shinemo.demo.repository.impl;

import com.ddd.demo.entity.Label;
import com.ddd.demo.repository.LabelRepository;
import com.shinemo.demo.mapper.LabelMapper;
import com.shinemo.demo.model.LabelDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class LabelRepositoryImpl implements LabelRepository {

    @Resource
    private LabelMapper labelMapper;

    @Override
    public Long insert(Label label) {

        LabelDO labelDO = new LabelDO();
        BeanUtils.copyProperties(label,labelDO);
        labelMapper.insert(labelDO);
        return labelDO.getId();
    }

    @Override
    public List<Label> find() {
        List<Label> labels = new ArrayList<>();
        Label label = new Label();
        label.setName("标签1");
        Label label2 = new Label();
        label2.setName("标签2");
        labels.add(label2);
        labels.add(label);
        return labels;
    }
}
