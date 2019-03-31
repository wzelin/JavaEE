package com.xxx.javaee;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xxx.javaee.util.JsonUtil;

import java.util.Map;

public class JsonTest {
    public static void main(String[] args) {
        String json = "{\"name\":\"王曾勇\",\"start\":\"15\",\"end\":\"18\"}";
        StudentParam sp = JsonUtil.toBean(json,StudentParam.class);
        System.out.println(sp.getName());
        //转换成Map<String,Object>
        Map<String,Object> map = JsonUtil.toGenericBean(json,new TypeReference<Map<String,Object>>(){});
    }
    static class StudentParam{
        private String name;
        //获取参数时，应该用包装类，因为原生类没有null
        private Integer start;
        private Integer end;

        public StudentParam() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getStart() {
            return start;
        }

        public void setStart(Integer start) {
            this.start = start;
        }

        public Integer getEnd() {
            return end;
        }

        public void setEnd(Integer end) {
            this.end = end;
        }
    }
}
