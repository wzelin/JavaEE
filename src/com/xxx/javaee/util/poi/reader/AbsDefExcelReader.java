package com.xxx.javaee.util.poi.reader;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :周忠友
 * @date   ：2017年8月23日 下午1:43:56
 * @since  : 1.0
 * 
 * 默认Excel读取方式，不适用大量数据的情况
 */
public abstract class AbsDefExcelReader implements ExcelReader{

	/**
	 * 输入流
	 */
	private InputStream inputStream;

	/**
	 * 通过文件的路径初始化
	 * @param inPath 文件路径
	 * @throws FileNotFoundException
	 */
	public AbsDefExcelReader(String inPath) throws FileNotFoundException {
		this.inputStream = new FileInputStream(inPath);
	}
	/**
	 * 通过文件类初始化
	 * @param file 文件
	 * @throws FileNotFoundException
	 */
	public AbsDefExcelReader(File file) throws FileNotFoundException {
		this.inputStream = new FileInputStream(file);
	}

	/**
	 * 直接通过输入流初始化
	 * @param inputStream 输入流
	 */
	public AbsDefExcelReader(InputStream inputStream){
		this.inputStream = inputStream;
	}

	@Override
	public void read() throws Exception {
		XSSFWorkbook wb = null;
		try{
			wb = new XSSFWorkbook(this.inputStream);
			int sheetsNum = wb.getNumberOfSheets();
			for(int i = 0; i <= sheetsNum; i++){
				//-- 读取
				XSSFSheet st = wb.getSheetAt(i);
				this.readExcel(st,i);
			}
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			wb.close();
		}
		
	}

	@Override
	public void read(int sheetId) throws Exception {
		XSSFWorkbook wb = null;
		try{
			wb = new XSSFWorkbook(this.inputStream);
			//-- 读取
			XSSFSheet st = wb.getSheetAt(sheetId);
			this.readExcel(st,sheetId);
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			wb.close();
		}
	}

	@SuppressWarnings("deprecation")
	private void readExcel(XSSFSheet st, int sheetId){
		List<List<String>> allList = new ArrayList<List<String>>();
		int len = st.getLastRowNum();
		for(int i = 0;i <= len;i++){
			XSSFRow row = st.getRow(i);
			int colNum = row.getLastCellNum();
			
			List<String> rowList = new ArrayList<String>();
			
			for(int j = 0; j < colNum;j++){
				XSSFCell cell  = row.getCell(j);
				if(cell.getCellTypeEnum() == CellType.NUMERIC){
					double num = cell.getNumericCellValue();					
					BigDecimal kk = new BigDecimal(num);					
					String str = kk.toString();
					rowList.add(str);
				}else{
					rowList.add(cell.getStringCellValue());
				}
			}
			this.onRowRead(sheetId, i, rowList);
			allList.add(rowList);
		}
		this.onAllRead(sheetId, allList);
	}
	
	@Override
	public void onRowRead(int sheetIndex, int curRow, List<String> rowList) {
		
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
