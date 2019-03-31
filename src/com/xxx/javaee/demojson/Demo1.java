package com.xxx.javaee.demojson;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Demo1 {

    public static void main(String[] args) {


        JSONObject json =new JSONObject();
        json.put("key1","张三");
        json.put("key1","李四");


        Boolean isSuccess = true;
        json.put("status",isSuccess);

        Double price = 10d;
        json.put("price",price);

        System.out.println(json.size());//只能有一个key1 后面的会将前面的覆盖

        System.out.println(json.containsKey("key"));//代表的返回的布尔类型的key

        System.out.println(json.containsValue("张三"));

        System.out.println(json.remove("key1"));

        System.out.println(json.remove("key1"));


        Object object = json.get("status");
        System.out.println(object);

        System.out.println(json.get("status"));


        JSONObject object1 = new JSONObject();
        object1.put("computer_1","显示器");
        object1.put("computer_2","鼠标");
        object1.put("computer_3","键盘");
        object1.put("computer_4","主机");

        JSONObject classs = new JSONObject();
        classs.put("classs_1","桌子");
        classs.put("classs_2","椅子");

        JSONArray array = new JSONArray();
        array.add(object1);
        array.add(classs);
        System.out.println(array);





    }

}
