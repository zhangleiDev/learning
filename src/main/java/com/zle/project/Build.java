package com.zle.project;


import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;

public class Build {

    private static final String DBURL="jdbc:mysql://localhost:3306/my";
    private static final String DBNAME="root";
    private static final String DBPWD="root1234";

    public static void main(String[] args) {
        try {

            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            //表名
            String tableName="user";
            /*配置项*/
            Configuration config = getGeneratedKeyConfig(tableName);
//            Configuration config = getConfig(tableName);

            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

            myBatisGenerator.generate(null);
            warnings.stream().forEach(s -> System.out.println(s));
            System.out.println("********end**********");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Configuration getConfig(String tableName){
        return getConfig(tableName,false,"getter,setter");
    }

    private static Configuration getGeneratedKeyConfig(String tableName){
        return getConfig(tableName,true,"getter,setter");
    }
    private static Configuration getConfig(String tableName,boolean generatedKey,String lombokType){
        Configuration config = new Configuration();
        //生成的字段位于同一实体中
        Context context = new Context(ModelType.FLAT);
        //生成简单sql还是复杂sql
        context.setTargetRuntime("MyBatis3");
        //配置id 随意设置
        context.setId("mysql");
        //自动识别数据库关键字，默认false，如果设置为true，
        //根据SqlReservedWords中定义的关键字列表；一般保留默认值，遇到数据库关键字（Java关键字），
        //使用columnOverride覆盖,字段包含数据库关键字时候需要配置
        //context.addProperty("autoDelimitKeywords","true");
        //生成的Java文件的编码
        context.addProperty("javaFileEncoding","utf-8");

        //格式化java代码
        context.addProperty("javaFormatter","org.mybatis.generator.api.dom.DefaultJavaFormatter");
        //格式化xml代码
        context.addProperty("xmlFormatter","org.mybatis.generator.api.dom.DefaultXmlFormatter");

        //配置生成bean的插件,配置多个最后一个生效
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.SerializablePlugin");
        pluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.ToStringPlugin");
        //配置自定义生成数据库实体插件
        pluginConfiguration.setConfigurationType("com.zle.plugins.LombokPlugin");
        //自定义的扩展属性,配置lombok注解类型
        context.addProperty("lombokType","getter,setter");
        context.addPluginConfiguration(pluginConfiguration);



        //设置是否生成注释
        CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
        //是否屏蔽注释
        //commentGeneratorConfiguration.addProperty("suppressAllComments","true");
        //是否屏蔽注释日期
        commentGeneratorConfiguration.addProperty("suppressDate","true");
        //是否添加数据库备注
        commentGeneratorConfiguration.addProperty("addRemarkComments","true");

        //配置自定义注释生成插件
        commentGeneratorConfiguration.setConfigurationType("com.zle.plugins.CommonPlugin");
        context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);

        //设置连接数据库
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setDriverClass("com.mysql.jdbc.Driver");
        jdbcConnectionConfiguration.setConnectionURL(DBURL);
        jdbcConnectionConfiguration.setUserId(DBNAME);
        jdbcConnectionConfiguration.setPassword(DBPWD);
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);


        JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
        //是否使用bigDecimal， false可自动转化以下类型（Long, Integer, Short, etc.）
        javaTypeResolverConfiguration.addProperty("forceBigDecimals","false");
        context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);

        //生成实体类的地址
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetPackage("com.zle.entity.db");
        javaModelGeneratorConfiguration.setTargetProject("src/main/java");
        javaModelGeneratorConfiguration.addProperty("enableSubPackages","false");
        javaModelGeneratorConfiguration.addProperty("trimStrings","true");
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        //生成mapper.xml
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject("src/main/resources");
        sqlMapGeneratorConfiguration.setTargetPackage("mapper");
        sqlMapGeneratorConfiguration.addProperty("enableSubPackages","true");
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        //生成mapxml对应client，也就是接口dao
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetPackage("com.zle.dao");
        javaClientGeneratorConfiguration.setTargetProject("src/main/java");
        //配置为注解和xml混合
        javaClientGeneratorConfiguration.setConfigurationType("MIXEDMAPPER");
        javaClientGeneratorConfiguration.addProperty("enableSubPackages","false");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);


        TableConfiguration tableConfiguration = new TableConfiguration(context);
        if(tableName == null || tableName.equals("")){
            System.out.println("表名称不能为空");
            return null;
        }
        tableName=tableName.toLowerCase();
        tableConfiguration.setTableName(tableName);
        String tb=underlineToHump(tableName)+"Entity";
        tableConfiguration.setDomainObjectName(underlineToHump(tableName)+"Entity");
        tableConfiguration.setMapperName(underlineToHump(tableName)+"Dao");

        if (generatedKey){
            // type=post且Identity=true时生成的selectKey中order=AFTER;
            // 当type=pre时,identity只能为false,生成的selectKey中order=BEFORE
            GeneratedKey genKey=new GeneratedKey("id","MySql",true,"post");
            tableConfiguration.setGeneratedKey(genKey);
        }

        context.addTableConfiguration(tableConfiguration);

        config.addContext(context);
        return config;
    }

    /**
     * 驼峰命名转换,首字母大写
     * @param para
     * @return
     */
    public static String underlineToHump(String para){
        StringBuilder result=new StringBuilder();
        String a[]=para.split("_");
        for(String s:a){
            result.append(s.substring(0, 1).toUpperCase());
            result.append(s.substring(1).toLowerCase());
        }
        return result.toString();
    }
}
