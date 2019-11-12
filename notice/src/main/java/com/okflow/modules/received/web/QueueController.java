package com.okflow.modules.received.web;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.okflow.common.utils.ImportExcelUtils;
import com.okflow.middleware.activitymq.ProducerService;
import com.okflow.middleware.redis.RedisCache;
import com.okflow.modules.received.entity.Tie;
import com.okflow.modules.received.service.TieService;
import com.okflow.modules.received.service.YbUserService;

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
	@Autowired
	private YbUserService ybUserService;
	@Autowired
	private RedisCache redisCache;
	@Autowired
	private TieService tieService;

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
		return "modules/received/nextstep";
	}

	@RequestMapping(value = { "excelTepImport" })
	public String excelTepImport() {
		return "modules/received/import";
	}

	@RequestMapping(value = { "studentImport" })
	public String studentImport(@RequestParam(value = "file", required = true) MultipartFile file,
			HttpServletRequest request) {// 学生数据导入
		System.out.println("文件名为:" + file.getOriginalFilename());
		try {
			String fileName = file.getOriginalFilename();
			InputStream inputStream = file.getInputStream();
			List<Map<String, Object>> sourceList = ImportExcelUtils.readExcel(fileName, inputStream);

			// redisCache.putListCache("stuImport", sourceList);
			ybUserService.impStuData(sourceList);

			System.out.println("sourceList" + sourceList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "modules/received/import";
	}

	@RequestMapping(value = { "tieTree" })
	@ResponseBody
	public String tieTree() {// 系别生成树
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		List<Tie> tList = tieService.findTieList();
		for (Tie tie : tList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", tie.getId());
			map.put("text", tie.getName());
			jsonList.add(map);
		}
		return JSON.toJSONString(jsonList);
	}
}
