package com.ddd.demo.repository;

import com.ddd.demo.entity.Label;

import java.util.List;

public interface LabelRepository {

    Long insert(Label label);

    List<Label> find();
}
