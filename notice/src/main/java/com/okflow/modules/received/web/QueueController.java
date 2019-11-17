package com.okflow.modules.received.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.okflow.common.config.Global;
import com.okflow.common.utils.ImportExcelUtils;
import com.okflow.middleware.activitymq.ProducerService;
import com.okflow.middleware.redis.RedisCache;
import com.okflow.modules.received.entity.Claban;
import com.okflow.modules.received.entity.Imessage;
import com.okflow.modules.received.entity.Tie;
import com.okflow.modules.received.entity.YbUser;
import com.okflow.modules.received.service.ClabanService;
import com.okflow.modules.received.service.ImessageService;
import com.okflow.modules.received.service.TieService;
import com.okflow.modules.received.service.YbUserService;
import com.okflow.modules.received.utils.QueueUtils;

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
	@Autowired
	private ClabanService clabanService;
	@Autowired
	private ImessageService imessageService;

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
	public String release(Model model) {// 通知发布

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
		System.out.println("tList" + tList.size());
		for (Tie tie : tList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", tie.getId());
			map.put("text", tie.getName());
			jsonList.add(map);
		}
		System.out.println("jsonList" + jsonList);
		return JSON.toJSONString(jsonList);
	}

	@RequestMapping(value = { "loadClaban" })
	public String loadClaban(String tieId, Model model) {
		if (StringUtils.isNotBlank(tieId)) {
			Tie tie = tieService.get(tieId);
			if (tie != null) {
				List<Claban> claList = tie.getClaList();
				model.addAttribute("claList", claList);
			}
		}
		return "modules/received/nextstep";
	}

	@RequestMapping(value = { "loadStudent" })
	public String loadStudent(String claId, Model model) {
		if (StringUtils.isNotBlank(claId)) {
			Claban claban = clabanService.get(claId);
			if (claban != null) {
				List<YbUser> userList = claban.getYbList();
				model.addAttribute("userList", userList);
			}
		}
		return "modules/received/stu";
	}

	@RequestMapping(value = { "candidate" })
	public String loginQueue(String[] indexs, Model model) {// 选择人员之后的下一步
		String indexStr = StringUtils.join(indexs, ",");
		model.addAttribute("indexStr", indexStr);
		return "modules/received/page";
	}

	@RequestMapping(value = { "deleteStu" })
	@ResponseBody
	public String deleteStu(String id) {// 删除指定学生
		System.out.println("删除id" + id);
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(id)) {
			int rows = ybUserService.delete(id);
			if (rows > 0) {
				map.put("status", "200");
			} else {
				map.put("status", "100");
			}
		}
		return JSON.toJSONString(map);
	}

	@RequestMapping(value = { "sendMessage" })
	public String sendMessage(Imessage imessage, String indexStr) throws JSONException, IOException {// 发送消息
		Map<String, Object> map = new HashMap<String, Object>();
		String producerKey = "9676068";// 消息生产者;
		String rever = QueueUtils.getReverStr(indexStr);
		map.put("producerKey", producerKey);
		map.put("theme", imessage.getTheme());
		map.put("content", imessage.getContent());
		map.put("rever", rever);
		map.put("consumerKey", indexStr);
		producerService.sendMapMessage(destination, map);
		return "redirect:" + Global.getAdminPath() + "/queue/queue/indexIcon";
	}

	@RequestMapping(value = "/arrComit")
	public String arrComit(String[] indexs, Model model) {
		String indexStr = StringUtils.join(indexs, ",");
		model.addAttribute("indexStr", indexStr);
		return "modules/received/page";
	}

	@RequestMapping(value = { "indexIcon" })
	public String indexIcon(Model model) {
		List<Imessage> messageList = imessageService.getMeNewsList();
		model.addAttribute("messageList", messageList);
		return "modules/received/index-icon";
	}
}
