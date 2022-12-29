package com.neweasesoft.datahub.common.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 数据源分类表
 * </p>
 *
 * @author junjie
 * @since 2022-12-26
 */
@Getter
@Setter
@TableName("datasource_classify")
public class DatasourceClassify implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型栏唯一编码
     */
    private String classifyCode;

    /**
     * 类型栏排序字段 默认从0开始
     */
    private Integer sorted;

    /**
     * 类型名称 包含全部和常用栏
     */
    private String classifyName;

    /**
     * 是否删除,1删除，0未删除
     */
    private Integer isDeleted;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer createBy;

    private Integer updateBy;


}
