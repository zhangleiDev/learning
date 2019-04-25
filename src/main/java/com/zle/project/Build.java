package com.zle.project;

import com.zle.plugins.CommonPlugin;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;

public class Build {

    public static void main(String[] args) {
        try {
            /*配置xml配置项*/
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            Configuration config = getConfig();

            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

            myBatisGenerator.generate(null);
            warnings.stream().forEach(s -> System.out.println(s));
            System.out.println("********end**********");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Configuration getConfig(){
        Configuration config = new Configuration();
        //生成的字段位于同一实体中
        Context context = new Context(ModelType.FLAT);
        //
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

        //格式化信息
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.SerializablePlugin");
        pluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.ToStringPlugin");
        //配置自定义生成数据库实体插件
        pluginConfiguration.setConfigurationType("com.zle.plugins.LombokPlugin");
        context.addPluginConfiguration(pluginConfiguration);



        //设置是否生成注释
        CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
        //是否屏蔽注释
        //commentGeneratorConfiguration.addProperty("suppressAllComments","true");
        //是否屏蔽注释日期
        commentGeneratorConfiguration.addProperty("suppressDate","true");
        //是否添加数据库备注 没起作用
        commentGeneratorConfiguration.addProperty("addRemarkComments","true");
        //配置自定义注释生成插件
        commentGeneratorConfiguration.setConfigurationType("com.zle.plugins.CommonPlugin");
        context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);

        //设置连接数据库
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setDriverClass("com.mysql.jdbc.Driver");
        jdbcConnectionConfiguration.setConnectionURL("jdbc:mysql://localhost:3306/my");
        jdbcConnectionConfiguration.setUserId("root");
        jdbcConnectionConfiguration.setPassword("root1234");
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
        tableConfiguration.setTableName("user");
        tableConfiguration.setDomainObjectName("UserEntity");
        tableConfiguration.setMapperName("UserDao");
//      type=post且Identity=true时生成的selectKey中order=AFTER;
//      当type=pre时,identity只能为false,生成的selectKey中order=BEFORE
        GeneratedKey generatedKey=new GeneratedKey("id","MySql",true,"post");

        tableConfiguration.setGeneratedKey(generatedKey);
        context.addTableConfiguration(tableConfiguration);

        config.addContext(context);
        return config;
    }
}
