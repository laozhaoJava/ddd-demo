package com.shinemo.demo.mapper;

import com.ddd.demo.query.LabelQuery;
import com.shinemo.common.db.dao.BaseMapper;
import com.shinemo.demo.model.LabelDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author htdong
 * @date 2020年6月15日 上午11:43:59
 */
public interface LabelMapper extends BaseMapper<LabelQuery, LabelDO> {

}