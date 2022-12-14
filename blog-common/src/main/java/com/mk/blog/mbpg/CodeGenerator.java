package com.mk.blog.mbpg;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.*;

/**
 * @author MK
 * see https://www.cnblogs.com/miaoying/p/12625920.html & https://baomidou.com/guide/generator.html
 * @describe mybatis-plus-generator代码生成
 */
public class CodeGenerator {

    /**
     * 基本路径
     */
    private static final String SRC_MAIN_JAVA = "/src/main/java/";

    private static final String PARENT_DIR = System.getProperty("user.dir");

    private static final String WINDOWS = "Windows";

    private static final String OS = System.getProperty("os.name");

    private static final String ENTITY_PATH = PARENT_DIR + "/blog-common/src/main/java/com/mk/blog/entity/";

    private static final String MAPPER_PATH = PARENT_DIR + "/blog-common/src/main/java/com/mk/blog/dao/";

    private static final String XML_PATH = PARENT_DIR + "/blog-common/src/main/resources/mapper/";

    public static final String JDBC_MYSQL_URL = "jdbc:mysql://106.55.148.202:3306/mk_blog?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";

    public static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    public static final String JDBC_USERNAME = "root";

    public static final String JDBC_PASSWORD = "MKmysql123456.";

    /**
     * 需要进行自动填充的字段
     */
    private static final List<TableFill> TABLE_FILL_LIST = Arrays.asList(
            new TableFill("create_time", FieldFill.INSERT),
            new TableFill("is_disable", FieldFill.INSERT));

    public static void main(String[] args) {
        // 实体类生成会乱代码，格式化下实体类
        autoGenerator("auth_user", "AuthUser", "admin", "MK");
    }

    /**
     * 自动生成代码
     *
     * @param tableName  数据库表名
     * @param entityName 实体名
     * @param packName   包名
     * @param author     作者
     * @author MK
     * @date 2021/3/16 15:08
     */
    public static void autoGenerator(String tableName, String entityName, String packName, String author) {
        new AutoGenerator()
                .setGlobalConfig(getGlobalConfig(author))
                .setDataSource(getDataSourceConfig())
                .setPackageInfo(getPackageConfig(packName))
                .setStrategy(getStrategyConfig(tableName))
                .setCfg(getInjectionConfig(packName, entityName))
                .setTemplate(getTemplateConfig())
                .execute();
    }

    /**
     * 自定义属性注入配置
     *
     * @return {@link InjectionConfig }
     * @author MK
     */
    private static InjectionConfig getInjectionConfig(String packName, String entityName) {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>(1);
                map.put("dateTime", DateUtil.now());
                setMap(map);
                List<FileOutConfig> fileOutConfigList = new ArrayList<>();
                // 自定义配置会被优先输出
                fileOutConfigList.add(new FileOutConfig("/generator/mapper.xml.vm") {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        // 自定义输出文件名，如果entity设置了前后缀，此次注意xml的名称也会跟着发生变化
                        tableInfo.setEntityName(entityName);
                        tableInfo.setMapperName(entityName + "Mapper");
                        return XML_PATH + packName + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                    }
                });
                setFileOutConfigList(fileOutConfigList);
            }
        };
    }

    /**
     * 方策略配置法描述
     *
     * @param tableName 表名
     * @return {@link StrategyConfig }
     * @author MK
     */
    private static StrategyConfig getStrategyConfig(String tableName) {
        return new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableName)
                .setRestControllerStyle(true)
                .setControllerMappingHyphenStyle(true)
                .setVersionFieldName("")
                .setTableFillList(TABLE_FILL_LIST)
                .setLogicDeleteFieldName("is_delete")
                .setEntityLombokModel(true);
    }

    /**
     * 包配置
     *
     * @return {@link PackageConfig }
     * @author MK
     */
    private static PackageConfig getPackageConfig(String packName) {
        Map<String, String> pathInfo = new HashMap<>(5);
        pathInfo.put(ConstVal.ENTITY_PATH, ENTITY_PATH + packName);
        pathInfo.put(ConstVal.MAPPER_PATH, MAPPER_PATH + packName);
        return new PackageConfig()
                .setParent("")
                .setEntity("com.mk.blog.entity." + packName)
                .setMapper("com.mk.blog.dao." + packName)
                .setPathInfo(pathInfo);
    }

    /**
     * 数据源配置
     *
     * @return {@link DataSourceConfig }
     * @author MK
     */
    private static DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig()
                .setUrl(JDBC_MYSQL_URL)
                .setDriverName(JDBC_DRIVER_NAME)
                .setUsername(JDBC_USERNAME)
                .setPassword(JDBC_PASSWORD);
    }

    /**
     * 全局配置
     *
     * @return {@link GlobalConfig }
     * @author MK
     */
    private static GlobalConfig getGlobalConfig(String author) {
        String filePath = PARENT_DIR + "/" + SRC_MAIN_JAVA;
        if (OS.startsWith(WINDOWS)) {
            filePath = filePath.replaceAll("/+|\\\\+", "\\\\");
        } else {
            filePath = filePath.replaceAll("/+|\\\\+", "/");
        }
        return new GlobalConfig()
                .setOutputDir(filePath)
                .setDateType(DateType.ONLY_DATE)
                .setIdType(IdType.ASSIGN_ID)
                .setAuthor(author)
                .setFileOverride(true)
                .setBaseColumnList(true)
                .setSwagger2(true)
                .setBaseResultMap(true)
                .setOpen(false);
    }

    /**
     * 模板配置
     *
     * @return {@link TemplateConfig }
     * @author MK
     */
    private static TemplateConfig getTemplateConfig() {
        return new TemplateConfig()
                .setEntity("/generator/entity.java.vm")
                .setMapper("/generator/mapper.java.vm")
                .setXml("/generator/mapper.xml.vm");
    }
}
