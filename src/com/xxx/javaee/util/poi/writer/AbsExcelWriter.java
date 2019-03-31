package com.xxx.javaee.util.poi.writer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :周忠友
 * @date   ：2017年10月22日
 * @since  : 1.0
 *
 * 输出Excel数据，顶层抽象类
 * Excel超大数据写入，抽象excel2007读入器，先构建.xlsx一张模板，改写模板中的sheet.xml, 使用这种方法
 * 写入.xlsx文件，不需要太大的内存
 *
 */
public abstract class AbsExcelWriter<T> implements ExcelWriter<T>{


	private OutputStream outputStream;

	public AbsExcelWriter(OutputStream outputStream){
		this.outputStream = outputStream;
	}

	public AbsExcelWriter(String outPath) throws FileNotFoundException {
		this.outputStream = new FileOutputStream(outPath);
	}

	public AbsExcelWriter(File file) throws FileNotFoundException {
		this.outputStream = new FileOutputStream(file);
	}


	@Override
	public void write(Map<String,List<T>> map) throws IOException {
		SXSSFWorkbook wb = new SXSSFWorkbook(100); // 在内存当中保持 100 行 , 超过的数据放到硬盘中
		for(String sheetName : map.keySet()) {
			Sheet sh = wb.createSheet(sheetName);
			int rownum = 0;
			List<String> headers = getHeaders();
			if(headers != null && headers.size() > 0){
				createRow(sh,rownum++,headers);
			}

			for(T t : map.get(sheetName)){
				List<String> data = resolveBean(t);
				createRow(sh,rownum++,data);
			}
		}
		wb.write(outputStream);
		outputStream.close();
		// dispose of temporary files backing this workbook on disk
		wb.dispose();
	}

	@Override
	public void write(String sheetName, List<T> list) throws IOException {
		Map<String,List<T>> map = new HashMap<>();
		map.put(sheetName,list);
		write(map);
	}

	@Override
	public void write(List<T> list) throws IOException {
		this.write("sheet",list);
	}

	@Override
	public List<String> getHeaders() {
		return new ArrayList<>();
	}

	//-------------------------------------------------

	/**
	 * 创建行
	 * @param sh sheet 对象
	 * @param rowNum 当前行数
	 * @param data 当前行的数据
	 */
	public Row createRow(Sheet sh,int rowNum, List<String> data){
		int len = data.size();
		Row row = sh.createRow(rowNum);
		for(int colNum = 0; colNum < len; colNum++){
			Cell cell = row.createCell(colNum);
			cell.setCellValue(data.get(colNum));
		}

		return row;
	}

}
