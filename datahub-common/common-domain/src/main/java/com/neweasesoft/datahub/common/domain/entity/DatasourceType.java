package com.neweasesoft.datahub.common.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 数据源类型信息表
 * </p>
 *
 * @author junjie
 * @since 2022-12-26
 */
@Getter
@Setter
@TableName("datasource_type")
public class DatasourceType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 数据源类型唯一 如Mysql, Oracle, Hive
     */
    private String dataType;

    /**
     * 数据源分类栏主键id
     */
    private Integer dataClassifyId;

    /**
     * 数据源权重
     */
    private BigDecimal weight;

    /**
     * 数据源logo图片地址
     */
    private String imgUrl;

    /**
     * 数据源类型排序字段, 默认从0开始
     */
    private Integer sorted;

    /**
     * 是否可见
     */
    @TableField("`invisible`")
    private Integer invisible;

    /**
     * 是否删除,1删除，0未删除
     */
    private Integer isDeleted;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer createBy;

    private Integer updateBy;


}
