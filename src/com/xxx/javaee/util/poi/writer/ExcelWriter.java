package com.xxx.javaee.util.poi.writer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author :周忠友
 * @date   ：2017年10月22日
 * @since  : 1.0
 *
 * 输出Excel数据
 */
public interface ExcelWriter<T> {
    /**
     *
     * 按列的顺序将当前行中每一列的值加入返回列表
     * 注意：和 getHeaders 中的顺序对应
     * @param bean 每行的对象
     * @throws Exception
     */
    List<String> resolveBean(T bean)throws IOException;

    /**
     * 按列的顺序将每列的表头加入返回列表
     * 注意：和 resolveBean 中的顺序对应
     */
    List<String> getHeaders();

    /**
     * 将数据输出到多个 sheet
     * @param map map 的 key 为 sheet 名称列表，value 为每个sheet的数据（该数据是一个对象列表）
     * @throws IOException
     */
    void write(Map<String, List<T>> map)throws IOException;

    /**
     * 输出数据到 Excel
     * @param sheetName Excel sheet 名称
     * @param list 要输出的数据对象列表
     * @throws IOException
     */
    void write(String sheetName, List<T> list)throws IOException;

    /**
     * 输出数据到 Excel
     * @param list 要输出的数据对象列表，默认输出在sheet名称为sheet的Excel中
     * @throws IOException
     */
    void write(List<T> list)throws IOException;
}
