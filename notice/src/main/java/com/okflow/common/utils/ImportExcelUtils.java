package com.okflow.common.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportExcelUtils {
	public static boolean isXls(String fileName) {
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return true;
		} else if ("xlsx".equals(extension)) {
			return false;
		} else {
			throw new RuntimeException("格式不对");
		}
	}

	@SuppressWarnings("deprecation")
	public static List<Map<String, Object>> readExcelByKey(String fileName, InputStream inputStream) throws Exception {

		boolean ret = isXls(fileName);
		Workbook workbook = null;
		// 根据后缀创建不同的对象
		if (ret) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			workbook = new XSSFWorkbook(inputStream);
		}
		Sheet sheet = workbook.getSheetAt(0);
		// 得到标题行
		Row titleRow = sheet.getRow(0);
		int lastRowNum = sheet.getLastRowNum();//获取表格最后一行
		int lastCellNum = titleRow.getLastCellNum();//获取表格最后一列
		List<Map<String, Object>> list = new ArrayList<>();

		for (int i = 1; i <= lastRowNum; i++) {//从表格第一行开始遍历
			Map<String, Object> map = new HashMap<>();
			Row row = sheet.getRow(i);//获取行
			for (int j = 0; j < lastCellNum; j++) {//获取列
				// 得到列名
				String key = titleRow.getCell(j).getStringCellValue();
				Cell cell = row.getCell(j);
				cell.setCellType(CellType.STRING);

				map.put(key, cell.getStringCellValue());
			}
			list.add(map);
		}
		workbook.close();
		return list;
	}
	@SuppressWarnings("deprecation")
	public static List<Map<String, Object>> readExcel(String fileName, InputStream inputStream) throws Exception {

		boolean ret = isXls(fileName);
		Workbook workbook = null;
		// 根据后缀创建不同的对象
		if (ret) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			workbook = new XSSFWorkbook(inputStream);
		}
		Sheet sheet = workbook.getSheetAt(0);
		// 得到标题行
		Row titleRow = sheet.getRow(0);
		int lastRowNum = sheet.getLastRowNum();//获取表格最后一行
		int lastCellNum = titleRow.getLastCellNum();//获取表格最后一列
		List<Map<String, Object>> list = new ArrayList<>();

		for (int i = 1; i <= lastRowNum; i++) {//从表格第一行开始遍历
			Map<String, Object> map = new HashMap<String, Object>();
			Row row = sheet.getRow(i);//获取行
			for (int j = 0; j < lastCellNum; j++) {//获取列
				// 得到列名
				String key = String.valueOf(j);//用表格列数下标作为map的key
				Cell cell = row.getCell(j);
				cell.setCellType(CellType.STRING);
				map.put(key, cell.getStringCellValue());
			}
			list.add(map);
		}
		workbook.close();
		return list;
	}
}