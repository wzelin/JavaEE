package com.xxx.javaee.util.poi.reader;

import java.util.List;

/**
 * @author :周忠友
 * @date   ：2017年8月23日 上午11:07:35
 * @since  : 1.0
 */
public interface ExcelReader {
	
	/**
	 * 遍历工作簿中所有的电子表格
	 *
	 * @throws Exception
	 */
	void read() throws Exception;
	
	/**
	 * 只遍历一个电子表格，其中sheetId为要遍历的sheet索引，从0开始
	 *
	 * @param sheetId
	 * @throws Exception
	 */
	void read(int sheetId) throws Exception;
	
	/**
	 * 按行动态读取信息（该方法执行不止一次，而且执行时，文档还可能没有读取完毕）
	 * 每读取一行触发
	 * 
	 * @param sheetIndex 返回的哪个sheet
	 * @param curRow 返回的哪一行
	 * @param rowList 返回对应行的数据
	 */
	void onRowRead(int sheetIndex, int curRow, List<String> rowList);
	
	/**
	 * 一般方式读取信息(该方法执行时数据已经读取)
	 * 整个文档读取完后触发
	 * 
	 * @param sheetIndex 返回的哪个sheet
	 * @param sheet 当前sheet的所有数据
	 */
	void onAllRead(int sheetIndex, List<List<String>> sheet);
}
