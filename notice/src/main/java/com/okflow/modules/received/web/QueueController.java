package com.okflow.modules.received.web;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.okflow.common.utils.ImportExcelUtils;
import com.okflow.middleware.activitymq.ProducerService;

/**
 * 消息队列Controller
 * 
 * @author xiaofanglin
 * @version 2019-10-19
 */
@Controller
@RequestMapping(value = { "${adminPath}/queue/queue" })
public class QueueController {

	@Autowired
	private ProducerService producerService;
	@Autowired
	@Qualifier("informQueueDestination")
	private Destination destination;

	@RequestMapping(value = { "tokenUrl" })
	public String tokenUrl() {
		return "layouts/login";
	}

	@RequestMapping(value = { "messagetext" })
	public String messagetext(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		System.out.println("userName" + userName);
		producerService.sendTextMessage(destination, userName);
		return "layouts/login";
	}

	@RequestMapping(value = { "pass" })
	public String pass() {
		return "modules/received/pass";
	}

	@RequestMapping(value = { "release" })
	public String release() {// 通知发布
		return "modules/received/page";
	}

	@RequestMapping(value = { "excelTepImport" })
	public String excelTepImport() {
		return "modules/received/import";
	}

	@RequestMapping(value = { "studentImport" })
	public String studentImport(@RequestParam(value = "file", required = true) MultipartFile file,
			HttpServletRequest request) {
		System.out.println("文件名为:" + file.getOriginalFilename());
		try {
			String fileName = file.getOriginalFilename();
			InputStream inputStream = file.getInputStream();
			List<Map<String, Object>> sourceList = ImportExcelUtils.readExcel(fileName, inputStream);

			System.out.println("sourceList" + sourceList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "modules/received/import";
	}
}
