package com.ddd.demo.exception;

import com.shinemo.common.tools.exception.ErrorCode;

/**
 * 错误
 *
 * @author Chenzhe Mao
 * @date 2020-04-25
 */
public interface LabelErrorCodes {
	ErrorCode BASE_ERROR = new ErrorCode(500, "系统繁忙，请稍后尝试！");
	ErrorCode NAME_EXIST = new ErrorCode(500, "标签名已存在！");
}