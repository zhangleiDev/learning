package com.zle.plugins.mybatis;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.*;

/**
 * Map驼峰命名拦截器
 */
@Intercepts(@Signature(type = ResultSetHandler.class,
        method = "handleResultSets",args = {Statement.class}))
public class ResultSetInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        List<Object> list = (List<Object>)invocation.proceed();
        for (Object object : list) {
            if (object instanceof Map){
                processMap((Map)object);
            }
        }
        return list;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target , this);
    }

    @Override
    public void setProperties(Properties properties) {
        //此处能获取xml配置文件的属性
        System.out.println(111111);
    }
    private void processMap(Map<String, Object> map) {

     Set<String> keySet = new HashSet<String>(map . keySet());
     for (String key : keySet) {

     if( (key.charAt (0) >= 'A' && key.charAt(0) <= 'z')
        || key. indexOf ("-") >= 0) {
         Object value= map.get(key) ;
         map.remove(key);
         map.put(underlineToCamelhump(key) , value) ;}
     }
    }
    public static String underlineToCamelhump(String inputString) {
        StringBuilder sb =new StringBuilder();
        boolean nextUpperCase = false ;
        if(inputString.indexOf("_")==-1){
            return inputString;
        }
        String[] split = inputString.split("_");
        for (int i = 0; i < split.length; i++) {
            if( i==0 && split[i] == ""){
                continue;
            }
            if(sb.length()>0){
                sb.append(Character.toUpperCase(split[i].charAt(0)))
                        .append(split[i].substring(1)).toString();
            }else {
                sb.append(split[i]);
            }
        }
        return sb.toString();
    }



}
