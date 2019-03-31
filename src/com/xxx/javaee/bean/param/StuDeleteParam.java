package com.xxx.javaee.bean.param;

public class StuDeleteParam {
    private Integer id;

    public StuDeleteParam() {
    }

    public StuDeleteParam(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StuDeleteParam{" +
                "id=" + id +
                '}';
    }
}
