package com.zle.tools;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

public class SendDingMsg {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendDingMsg.class);
    private static final int SUCCESS_CODE = 200;

    public static void main(String[] args) {
        SendDingMsg.sendMsg("警告：来自httpClient");
    }
    public static void sendMsg(String msg){
        /***
         * {   "msgtype": "text",
         *     "text": {
         *          "content": "通知：66%"
         *     }
         * }
         */
        try {

            String s="{   \"msgtype\": \"text\", \n" +
                    "    \"text\": {\n" +
                    "         \"content\": \""+msg+"\"\n" +
                    "    }\n" +
                    "}";
            sendPost("https://oapi.dingtalk.com/robot/send?access_token=aec15efbd4c86284de27ff6dfb2631702de4e23889ab0ba14aff59382b158969",s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 发送POST请求
     * @param url
     * @param jsonMsg
     * @return JSON或者字符串
     * @throws Exception
     */
    public static Object sendPost(String url, String jsonMsg) throws Exception{
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try{
            /**
             *  创建一个httpclient对象
             */
            client = HttpClients.createDefault();
            /**
             * 创建一个post对象
             */
            HttpPost post = new HttpPost(url);

            /**
             * 设置请求的内容
             */
            post.setEntity(new StringEntity(jsonMsg, Charset.forName("UTF-8")));

            /**
             * 设置请求的报文头部的编码
             */
            post.setHeader(new BasicHeader("Content-Type", "application/json; charset=utf-8"));
            /**
             * 设置请求的报文头部的编码
             */
            post.setHeader(new BasicHeader("Accept", "application/json;charset=utf-8"));
            /**
             * 执行post请求
             */
            response = client.execute(post);
            /**
             * 获取响应码
             */
            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode){
                /**
                 * 通过EntityUitls获取返回内容
                 */
                String result = EntityUtils.toString(response.getEntity(),"UTF-8");
                /**
                 * 转换成json,根据合法性返回json或者字符串
                 */
                try{
                    jsonObject = JSONObject.parseObject(result);
                    return jsonObject;
                }catch (Exception e){
                    return result;
                }
            }else{
                LOGGER.error("HttpClientService-line: {}, errorMsg：{}", 146, "POST请求失败！");
            }
        }catch (Exception e){
            LOGGER.error("HttpClientService-line: {}, Exception：{}", 149, e);
        }finally {
            response.close();
            client.close();
        }
        return null;
    }

}
