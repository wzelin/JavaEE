package com.xxx.javaee.util.poi.writer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author :周忠友
 * @date   ：2017年10月22日
 * @since  : 1.0
 *
 * 输出 Excel 数据，通过 List&lt;Map&gt; 输出
 */
public class MapExcelWriter extends AbsExcelWriter<Map<String,Object>>{

    public MapExcelWriter(OutputStream outputStream){
        super(outputStream);
    }

    @Override
    public List<String> resolveBean(Map<String, Object> bean) throws IOException {
        List<String> list = new ArrayList<String>();

        for(Map.Entry<String,Object> entry : bean.entrySet()){
            list.add(entry.getValue().toString());
        }

        return list;
    }
}
