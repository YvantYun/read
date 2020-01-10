package com.yvant.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mybatis-plus 自动填充
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        setFieldValByName("deleted", 0, metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object val = getFieldValByName("updateTime", metaObject);
        if(val == null){
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }

    }
}