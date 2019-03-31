package com.xxx.javaee.bean.param;

import java.util.ArrayList;
import java.util.List;

public class Pager<T> {

    private int curPage;// 当前页
    private int pageSize;// 没有显示多少条
    private int total;// 当前查询总条数

    private int start;// 当前查询的开始条数
    private int end;// 当前查询的结束条数

    // 当前页的数据
    private List<T> list = new ArrayList<>();

    /**
     * 查询之前，通过当页和没有显示多少条，计算开始和结束条数
     * @param curPage 当前页
     * @param pageSize 每页显示多少条
     */
    public Pager(int curPage, int pageSize) {
        if(pageSize == 0 || pageSize > 5000){
            pageSize = 5;
        }
        this.curPage = curPage;
        this.pageSize = pageSize;

        this.start = (this.curPage - 1)*this.pageSize;
        this.end = this.curPage * this.pageSize - 1;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Pager{" +
                "curPage=" + curPage +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", start=" + start +
                ", end=" + end +
                ", list=" + list +
                '}';
    }
}
