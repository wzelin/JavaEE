package com.xxx.javaee.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author 周忠友
 * @version 1.0
 * @date 2017/9/25
 * @see
 */
public abstract class JsonUtil {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
//        当反序列化json时，未知属性会引起的反序列化被打断，这里我们禁用未知属性打断反序列化功能，
//        因为，例如json里有10个属性，而我们的bean中只定义了2个属性，其它8个属性将被忽略
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //-- 配置Jackson返回日期类型格式（默认是日期的毫秒数）
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(format);
    }


    /**
     * 转换为json字符串
     * @param obj
     * @return
     */
    public static final String toJson(Object obj){
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 将json对象，转换为Java对象
     * @param json json对象字符串
     * @param clazz 要转化为的java对象
     * @param <T>
     * @return
     */
    public static final <T> T toBean(String json,Class<T> clazz){
        try {
            return mapper.readValue(json,clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  转换为带泛型的类（如Person&lt;T&gt;、List或Map等泛型）
     *
     * <p>将json数组或对象转换为对应的集合或Map或者带泛型的类</p>
     *
     *  <p>示例：</p>
     *  <p>例1：</p>
     *  <p>json = "{\"name\":\"对象转换\",\"age\":11}";</p>
     *  <p>toCollection(json,new TypeReference&lt;Map&lt;String,Object&gt;&gt;(){});返回Map<String,Object>类型</p>
     *
     *  <p>列2：</p>
     *  <p>json = "[{\"name\":\"对象转换\",\"age\":11},{\"name\":\"对象转换\",\"age\":11}]"</p>
     *  <p>toCollection(json,new TypeReference&lt;ArrayList&lt;User&gt;&gt;(){});返回List<User>类型</p>
     *
     * @param json
     * @param valueTypeRef
     * @param <T>
     * @return
     */
    public static final <T> T toGenericBean(String json,TypeReference valueTypeRef){
        try {
            return mapper.readValue(json,valueTypeRef);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
