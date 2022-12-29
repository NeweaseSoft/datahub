package com.neweasesoft.datahub.datasource.web.mapper;

import com.neweasesoft.datahub.common.domain.entity.DatasourceType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 数据源类型信息表 Mapper 接口
 * </p>
 *
 * @author junjie
 * @since 2022-12-26
 */
@Mapper
public interface DatasourceTypeMapper extends BaseMapper<DatasourceType> {

}
