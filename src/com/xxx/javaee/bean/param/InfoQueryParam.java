package com.xxx.javaee.bean.param;

public class InfoQueryParam {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InfoQueryParam{" +
                "name='" + name + '\'' +
                '}';
    }
}
