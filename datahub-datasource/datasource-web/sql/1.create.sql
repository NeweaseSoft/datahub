create database if not exists datahub;
use datahub;

create table if not exists datasource_classify
(
    id int(11) unsigned auto_increment comment '自增id' primary key,
    classify_code varchar(64) not null comment '类型栏唯一编码',
    sorted int(5) default 0 not null comment '类型栏排序字段 默认从0开始',
    classify_name varchar(64) not null comment '类型名称 包含全部和常用栏',
    is_deleted tinyint default 0 not null comment '是否删除,1删除，0未删除',
    create_time datetime default CURRENT_TIMESTAMP null,
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    create_by int default 0 null,
    update_by int default 0 null,
    constraint classify_code
        unique (classify_code)
) comment '数据源分类表';


create table if not exists datasource_type
(
    id int(11) unsigned auto_increment comment '自增id' primary key,
    data_type varchar(64) not null comment '数据源类型唯一 如Mysql, Oracle, Hive',
    data_classify_id int not null comment '数据源分类栏主键id',
    weight decimal(20,1) default 0.0 not null comment '数据源权重',
    img_url varchar(256) null comment '数据源logo图片地址',
    sorted int(5) default 0 not null comment '数据源类型排序字段, 默认从0开始',
    invisible tinyint default 0 not null comment '是否可见',
    is_deleted tinyint default 0 not null comment '是否删除,1删除，0未删除',
    create_time datetime default CURRENT_TIMESTAMP null,
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    create_by int default 0 null,
    update_by int default 0 null,
    constraint data_type
        unique (data_type)
) comment '数据源类型信息表';

create table if not exists datasource_version
(
    id int(11) unsigned auto_increment comment '自增id' primary key,
    data_type varchar(64) not null comment '数据源类型唯一 如Mysql, Oracle, Hive',
    data_version varchar(64) not null comment '数据源版本 如1.x, 0.9',
    sorted int(5) default 0 not null comment '版本排序字段,高版本排序,默认从0开始',
    is_deleted tinyint default 0 not null comment '是否删除,1删除，0未删除',
    create_time datetime default CURRENT_TIMESTAMP null,
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    create_by int default 0 null,
    update_by int default 0 null,
    constraint data_type
        unique (data_type, data_version)
) comment '数据源版本表';


create table datasource_form_field
(
    id int(11) unsigned auto_increment comment '自增id'
        primary key,
    name varchar(64) not null comment '表单属性名称，同一模版表单中不重复',
    label varchar(64) not null comment '属性前label名称',
    widget varchar(64) not null comment '属性格式 如Input, Radio等',
    required tinyint default 0 not null comment '是否必填 0-非必填 1-必填',
    invisible tinyint default 0 not null comment '是否为隐藏 0-否 1-隐藏',
    default_value text null comment '表单属性中默认值, 默认为空',
    place_hold text null comment '输入框placeHold, 默认为空',
    request_api varchar(256) null comment '请求数据Api接口地址，一般用于关联下拉框类型，如果不需要请求则为空',
    is_link tinyint default 0 not null comment '是否为数据源需要展示的连接信息字段。0-否; 1-是',
    valid_info text null comment '校验返回信息文案',
    tooltip text null comment '输入框后问号的提示信息',
    style text null comment '前端表单样式参数',
    regex text null comment '正则校验表达式',
    type_version varchar(64) not null comment '对应数据源版本信息',
    is_deleted tinyint default 0 not null comment '是否删除,1删除，0未删除',
    gmt_create datetime default CURRENT_TIMESTAMP null,
    gmt_modified datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    create_user_id int default 0 null,
    modify_user_id int default 0 null,
    options varchar(256) default '' null comment 'select组件下拉内容',
    constraint name
        unique (name, type_version)
)
    comment '数据源表单属性表';

create table datasource_info
(
    id int(11) unsigned auto_increment comment '自增id'
        primary key,
    data_type varchar(64) not null comment '数据源类型唯一 如Mysql, Oracle, Hive',
    data_version varchar(64) null comment '数据源版本 如1.x, 0.9, 创建下的实例可能会没有版本号',
    data_name varchar(128) not null comment '数据源名称',
    data_desc text null comment '数据源描述',
    link_json text null comment '数据源连接信息, 不同数据源展示连接信息不同, 保存为json',
    data_json text null comment '数据源填写的表单信息, 保存为json, key键要与表单的name相同',
    status tinyint not null comment '连接状态 0-连接失败, 1-正常',
    is_meta tinyint default 0 not null comment '是否有meta标志 0-否 1-是',
    tenant_id int not null comment '租户主键id **可能不是id 其他唯一凭证',
    data_type_code tinyint default 0 not null comment '数据源类型编码',
    schema_name varchar(64) default '' null comment '数据源schemaName',
    is_deleted tinyint default 0 not null comment '是否删除,1删除，0未删除',
    gmt_create datetime default CURRENT_TIMESTAMP null,
    gmt_modified datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    create_user_id int default 0 null,
    modify_user_id int default 0 null
)
    comment '数据源详细信息表';

create index MODIFY_TIME
    on datasource_info (gmt_modified);