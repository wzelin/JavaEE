package com.xxx.javaee.demojson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Demo2 {
    public static void main(String[] args) {

        JSONObject jsonObject1 = new JSONObject();

        jsonObject1.put("id","2280");
        jsonObject1.put("name","成都市");
        jsonObject1.put("fullName","四川省成都市");


        JSONObject jsonObject2 = new JSONObject();

        jsonObject2.put("id","2321");
        jsonObject2.put("name","德阳市");
        jsonObject2.put("fullName","四川省德阳市");


        JSONObject jsonObject3 = new JSONObject();

        jsonObject3.put("id","2222");
        jsonObject3.put("name","绵阳市");
        jsonObject3.put("fullName","四川省绵阳市");


        JSONArray body = new JSONArray();
        body.add(jsonObject1);
        body.add(jsonObject2);
        body.add(jsonObject3);

        JSONObject object = new JSONObject();
        object.put("body",body.toString());

        System.out.println(object);

    }
}
