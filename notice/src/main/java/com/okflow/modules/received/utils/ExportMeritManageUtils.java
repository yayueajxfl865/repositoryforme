package com.okflow.modules.received.utils;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.okflow.modules.received.entity.Consumer;

public class ExportMeritManageUtils {

	public static HSSFWorkbook exportExcel_sealing(List<Consumer> pList, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] headers = { "发送人", "发送时间", "通知主题", "接收者", "接收状态" };
		// 导出文件名,单位+发放年月+绩效发放台账
		String fileName = QueueUtils.currentTime();
		// 设置请求头
		response.setHeader("Content-disposition",
				"attachment;filename=" + URLEncoder.encode(fileName + ".xls", "UTF-8"));
		response.setContentType("application/msexcel;charset=UTF-8");
		// 创建Excel表。
		HSSFWorkbook workbook = new HSSFWorkbook();
		// TODO 需要将Table_Name修改为当前数据库中你想导出的数据表
		String Table_Name = "sheet1";
		// 在当前Excel创建一个子表
		Sheet sheet = workbook.createSheet(Table_Name);
		// 创建单元格，设置表头样式
		HSSFCellStyle style = workbook.createCellStyle();// 内容格式
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);// 设置字体大小
		style.setFont(font);

		HSSFCellStyle style1 = workbook.createCellStyle();// 标题格式
		HSSFFont font1 = workbook.createFont();
		font1.setFontHeightInPoints((short) 12);// 设置字体大小
		style1.setFont(font1);

		// 设置表头信息（写入Excel左上角是从(0,0)开始的）
		Row row = sheet.createRow(0);
		row.setHeight((short) (25 * 20));
		int colnum = headers.length;
		for (int i = 0; i < colnum; i++) {
			String name = headers[i];
			// 单元格
			Cell cell = row.createCell(i);
			// 写入数据
			cell.setCellValue(name);
			cell.setCellStyle(style1);
		}
		// 遍历集合数据，产生数据行
		Iterator<Consumer> it = pList.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			// 从第1行开始创建
			row = sheet.createRow(index);// 行
			row.setHeight((short) (25 * 15));
			Consumer mm = it.next();
			Cell cell = null;
			for (int i = 0; i < colnum; i++) {// 项Excel写固定行数据
				cell = row.createCell(i);// 列
				cell.setCellStyle(style);
				if (i == 0) {
					cell.setCellValue(mm.getImessage().getYbUser().getYb_realname() != null
							? mm.getImessage().getYbUser().getYb_realname().toString()
							: "");
				} else if (1 == i) {
					cell.setCellValue(mm.getImessage().getCreateDate() == null ? ""
							: mm.getImessage().getCreateDate().toString());
				} else if (2 == i) {
					cell.setCellValue(
							StringUtils.isBlank(mm.getImessage().getTheme()) ? "" : mm.getImessage().getTheme());

				} else if (3 == i) {
					cell.setCellValue(
							StringUtils.isNotBlank(mm.getYbUser().getYb_realname()) ? mm.getYbUser().getYb_realname()
									: "");
				} else if (4 == i) {
					cell.setCellValue(StringUtils.isNotBlank(mm.getStatus()) ? mm.getStatus() : "");
				}
			}
		}
		sheet.setColumnWidth(0, 2500);// 设置列宽（列从0开始）
		sheet.setColumnWidth(1, 6500);
		sheet.setColumnWidth(2, 6700);
		sheet.setColumnWidth(3, 2500);
		sheet.setColumnWidth(4, 2600);
		OutputStream outputStream = response.getOutputStream();// 打开流
		workbook.write(outputStream);// HSSFWorkbook写入流
		workbook.close();// HSSFWorkbook关闭
		outputStream.flush();// 刷新流
		outputStream.close();// 关闭流
		return workbook;
	}
}
