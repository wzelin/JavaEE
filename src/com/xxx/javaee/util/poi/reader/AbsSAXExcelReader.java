package com.xxx.javaee.util.poi.reader;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author :周忠友
 * @date   ：2017年8月23日 上午11:12:12
 * @since  : 1.0
 * 
 * 读取Excel数据，适合大量数据的情况
 */
public abstract class AbsSAXExcelReader extends DefaultHandler implements ExcelReader {

	// 共享字符串表
	private SharedStringsTable sst;

	// 上一次的内容
	private String lastContents;
	private boolean nextIsString;

	private int sheetIndex = -1;
	private List<String> rowList = new ArrayList<String>();

	// 当前行
	private int curRow = 0;
	// 当前列
	private int curCol = 0;
	// 日期标志
	private boolean dateFlag;
	// 数字标志
	private boolean numberFlag;

	private boolean isTElement;

	/**
	 * 输入流
	 */
	private InputStream inputStream;

	/**
	 * 通过文件的路径初始化
	 * @param inPath 文件路径
	 * @throws FileNotFoundException
	 */
	public AbsSAXExcelReader(String inPath) throws FileNotFoundException {
		this.inputStream = new FileInputStream(inPath);
	}
	/**
	 * 通过文件类初始化
	 * @param file 文件
	 * @throws FileNotFoundException
	 */
	public AbsSAXExcelReader(File file) throws FileNotFoundException {
		this.inputStream = new FileInputStream(file);
	}

	/**
	 * 直接通过输入流初始化
	 * @param inputStream 输入流
	 */
	public AbsSAXExcelReader(InputStream inputStream){
		this.inputStream = inputStream;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * 遍历工作簿中所有的电子表格
	 *
	 * @throws Exception
	 */
	public void read() throws Exception {
		OPCPackage pkg = OPCPackage.open(this.inputStream);
		XSSFReader r = new XSSFReader(pkg);
		SharedStringsTable sst = r.getSharedStringsTable();
		XMLReader parser = fetchSheetParser(sst);
		Iterator<InputStream> sheets = r.getSheetsData();
		while (sheets.hasNext()) {
			curRow = 0;
			sheetIndex++;
			InputStream sheet = sheets.next();
			InputSource sheetSource = new InputSource(sheet);
			parser.parse(sheetSource);
			sheet.close();
		}
	}

	/**
	 * 只遍历一个电子表格，其中sheetId为要遍历的sheet索引，从0开始
	 *
	 * @param sheetId
	 * @throws Exception
	 */
	public void read(int sheetId) throws Exception {
		OPCPackage pkg = OPCPackage.open(this.inputStream);
		XSSFReader r = new XSSFReader(pkg);
		SharedStringsTable sst = r.getSharedStringsTable();
		XMLReader parser = fetchSheetParser(sst);
		// 根据 rId# 或 rSheet# 查找sheet
		InputStream sheet2 = r.getSheet("rId" + ++sheetId);
		sheetIndex++;
		InputSource sheetSource = new InputSource(sheet2);
		parser.parse(sheetSource);
		sheet2.close();
	}

	public XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
		XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
		this.sst = sst;
		parser.setContentHandler(this);
		return parser;
	}

	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {

		// System.out.println("startElement: " + localName + ", " + name + ", "
		// + attributes);

		// c => 单元格
		if ("c".equals(name)) {
			// 如果下一个元素是 SST 的索引，则将nextIsString标记为true
			String cellType = attributes.getValue("t");
			if ("s".equals(cellType)) {
				nextIsString = true;
			} else {
				nextIsString = false;
			}
			// 日期格式
			String cellDateType = attributes.getValue("s");
			if ("1".equals(cellDateType)) {
				dateFlag = true;
			} else {
				dateFlag = false;
			}
			String cellNumberType = attributes.getValue("s");
			if ("2".equals(cellNumberType)) {
				numberFlag = true;
			} else {
				numberFlag = false;
			}

		}
		// 当元素为t时
		if ("t".equals(name)) {
			isTElement = true;
		} else {
			isTElement = false;
		}

		// 置空
		lastContents = "";
	}

	public void endElement(String uri, String localName, String name) throws SAXException {

		// System.out.println("endElement: " + localName + ", " + name);

		// 根据SST的索引值的到单元格的真正要存储的字符串
		// 这时characters()方法可能会被调用多次
		if (nextIsString) {
			try {
				int idx = Integer.parseInt(lastContents);
				lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
			} catch (Exception e) {

			}
		}
		// t元素也包含字符串
		if (isTElement) {
			String value = lastContents.trim();
			rowList.add(curCol, value);
			curCol++;
			isTElement = false;
			// v => 单元格的值，如果单元格是字符串则v标签的值为该字符串在SST中的索引
			// 将单元格内容加入rowlist中，在这之前先去掉字符串前后的空白符
		} else if ("v".equals(name)) {
			String value = lastContents.trim();
			value = value.equals("") ? " " : value;
			try {
				// 日期格式处理
				if (dateFlag) {
					Date date = HSSFDateUtil.getJavaDate(Double.valueOf(value));
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					value = dateFormat.format(date);
				}
				// 数字类型处理
				if (numberFlag) {
					BigDecimal bd = new BigDecimal(value);
					value = bd.setScale(3, BigDecimal.ROUND_UP).toString();
				}
			} catch (Exception e) {
				// 转换失败仍用读出来的值
			}
			rowList.add(curCol, value);
			curCol++;
		} else {
			// 如果标签名称为 row ，这说明已到行尾，调用 onRowRead() 方法
			if (name.equals("row")) {
				onRowRead(sheetIndex + 1, curRow, rowList);
				rowList.clear();
				curRow++;
				curCol = 0;
			}
		}

	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		// 得到单元格内容的值
		lastContents += new String(ch, start, length);
	}


	@Override
	public void onAllRead(int sheetIndex, List<List<String>> sheet) {
		throw new RuntimeException("该方法sax方式不实现");
	}

}
