package com.deng.aspect;

import com.deng.pojo.log.OperatorType;

import java.lang.annotation.*;

/**
 * Created by deng on 2017/3/17.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogType {
    /**
     * @return 操作名
     */
    String operationName() default "";

    /**
     * @return 操作者类型，包括会员、酒店、经理
     */
    OperatorType operatorType() default OperatorType.MEMBER;
}
