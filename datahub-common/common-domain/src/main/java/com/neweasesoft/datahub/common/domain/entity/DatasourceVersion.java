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
 * 数据源版本表
 * </p>
 *
 * @author junjie
 * @since 2022-12-26
 */
@Getter
@Setter
@TableName("datasource_version")
public class DatasourceVersion implements Serializable {

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
     * 数据源版本 如1.x, 0.9
     */
    private String dataVersion;

    /**
     * 版本排序字段,高版本排序,默认从0开始
     */
    private Integer sorted;

    /**
     * 是否删除,1删除，0未删除
     */
    private Integer isDeleted;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer createBy;

    private Integer updateBy;


}
