package com.mk.blog.mbpg;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MateHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //新增数据时自动填充创建时间和最后修改时间
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "isDisable", Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
    }

}
