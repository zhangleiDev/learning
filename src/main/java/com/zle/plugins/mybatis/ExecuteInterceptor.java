package com.zle.plugins.mybatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.Statement;
import java.util.Properties;

@Intercepts(@Signature(type = Executor.class,
        method = "update",args = {MappedStatement.class,Object.class}))
public class ExecuteInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return null;
    }

    @Override
    public Object plugin(Object target) {
        return null;
    }

    /**
     * 获取xml配置文件中配置的属性值
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        properties.stringPropertyNames().stream().forEach(a-> System.out.println(properties.get(a)));

    }
}
