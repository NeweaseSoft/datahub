package com.neweasesoft.datahub;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.querys.PostgreSqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.baomidou.mybatisplus.generator.keywords.PostgreSqlKeyWordsHandler;

import java.util.Collections;

public class CodeGen {

    //数据库信息(POSTGRESQL)
//    private static final String DRIVER_NAME = "org.postgresql.Driver";
    private static final String URL = "jdbc:mysql://101.34.147.124:38246/datahub";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "aYRSVx8W95";
    private static final DataSourceConfig.Builder DATA_CONFIG_BUILDER=
            new DataSourceConfig.Builder(URL,USER_NAME,PASSWORD)
                    .dbQuery(new MySqlQuery())
//                    .schema("datahub")
                    .typeConvert(new MySqlTypeConvert())
                    .keyWordsHandler(new MySqlKeyWordsHandler());

    public static void main(String[] args) {

        //parentPath: 项目路径--到/src/main 此处为默认值
        final String parentPath = System.getProperty("user.dir")+"/src/main";

        final String projectPath = parentPath+"/java";
        FastAutoGenerator.create(DATA_CONFIG_BUILDER)
                //全局设置
                .globalConfig(builder -> {
                    // 设置作者
                    builder.author("junjie")
                            // 覆盖已生成文件
                            .fileOverride()
                            //执行完毕后禁止打开目录
                            .disableOpenDir()
                            // 指定输出目录
                            .outputDir(projectPath);
                })
                //包设置
                .packageConfig(builder -> {
                    // 设置父包名
                    builder.parent("com.neweasesoft.datahub")
                            // 设置父包模块名
//                            .moduleName("system")
                            // 设置mapperXml生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, parentPath+"/resources/mapper"));
                })
                //策略设置
                .strategyConfig(builder -> 
                        //数据库表
                        builder.addInclude("datasource_classify","datasource_type", "datasource_version")
                        //通用查询结果列和通用查询映射结果
                        .mapperBuilder().enableBaseColumnList().enableBaseResultMap()
                        //去掉service接口开头的字母I
                        .serviceBuilder().formatServiceFileName("%sService")
                        //以RESTful风格生成controller
                        .controllerBuilder().enableRestStyle()
                        //数据库字段转实体属性风格--驼峰
                        .entityBuilder().columnNaming(NamingStrategy.underline_to_camel)
                        // 设置过滤表前缀
//                            .addTablePrefix("t_", "c_")
                        //启用lombok注解
                        .enableLombok())
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
