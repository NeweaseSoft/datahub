package com.neweasesoft.datahub.datasource.web.service.impl;

import com.neweasesoft.datahub.common.domain.entity.DatasourceVersion;
import com.neweasesoft.datahub.datasource.web.mapper.DatasourceVersionMapper;
import com.neweasesoft.datahub.datasource.web.service.DatasourceVersionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据源版本表 服务实现类
 * </p>
 *
 * @author junjie
 * @since 2022-12-26
 */
@Service
public class DatasourceVersionServiceImpl extends ServiceImpl<DatasourceVersionMapper, DatasourceVersion> implements DatasourceVersionService {

}
