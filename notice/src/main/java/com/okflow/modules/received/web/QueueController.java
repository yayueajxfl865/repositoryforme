package com.okflow.modules.received.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.okflow.middleware.redis.RedisCache;
import com.okflow.modules.received.entity.Claban;
import com.okflow.modules.received.entity.Clubs;
import com.okflow.modules.received.entity.Consumer;
import com.okflow.modules.received.entity.Imessage;
import com.okflow.modules.received.entity.Tie;
import com.okflow.modules.received.entity.YbUser;
import com.okflow.modules.received.service.ClabanService;
import com.okflow.modules.received.service.ClubsService;
import com.okflow.modules.received.service.ImessageService;
import com.okflow.modules.received.service.TieService;
import com.okflow.modules.received.service.YbUserService;
import com.okflow.modules.received.utils.QueueUtils;

import cn.yiban.open.common.User;
import net.sf.json.JSONObject;

/**
 * 消息队列Controller
 * 
 * @author xiaofanglin
 * @version 2019-10-19
 */
@Controller
@RequestMapping(value = { "${adminPath}/queue/queue" })
public class QueueController {

	/*
	 * @Autowired private ProducerService producerService;
	 * 
	 * @Autowired
	 * 
	 * @Qualifier("informQueueDestination") private Destination destination;
	 */
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
	@Autowired
	private ClubsService clubsService;

	@RequestMapping(value = { "tokenUrl" })
	public String tokenUrl(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String uri = request.getRequestURI();
		System.out.println("url" + url);
		System.out.println("uri" + uri);

		System.out.println("回调地址");
		return "modules/received/index";
	}

	@RequestMapping(value = { "messagetext" })
	public String messagetext(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		System.out.println("userName" + userName);
		// producerService.sendTextMessage(destination, userName);
		return "layouts/login";
	}

	@RequestMapping(value = { "pass" })
	public String pass() {
		return "modules/received/pass";
	}

	@RequestMapping(value = { "release" })
	public String release(Model model) {// 通知发布

		return "modules/received/pageList";
	}

	@RequestMapping(value = { "stuManage" })
	public String stuManage() {
		return "modules/received/nextstep";
	}

	@RequestMapping(value = { "clubsManage" })
	public String clubsManage() {
		return "modules/received/clubsNextstep";
	}

	@RequestMapping(value = { "excelTepImport" })
	public String excelTepImport() {
		return "modules/received/import";
	}

	@RequestMapping(value = { "studentImport" })
	@ResponseBody
	public String studentImport(@RequestParam(value = "file", required = true) MultipartFile file,
			HttpServletRequest request) {// 学生数据导入
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String fileName = file.getOriginalFilename();
			InputStream inputStream = file.getInputStream();
			List<Map<String, Object>> sourceList = ImportExcelUtils.readExcel(fileName, inputStream);
			// redisCache.putListCache("stuImport", sourceList);
			ybUserService.impStuData(sourceList);
			map.put("status", "200");
			map.put("message", sourceList.size());
		} catch (Exception e) {
			map.put("status", "100");
			e.printStackTrace();
		}
		return JSON.toJSONString(map);
	}

	@RequestMapping(value = { "teacherImport" })
	@ResponseBody
	public String teacherImport(@RequestParam(value = "file", required = true) MultipartFile file,
			HttpServletRequest request) {// 教师数据导入
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String fileName = file.getOriginalFilename();
			InputStream inputStream = file.getInputStream();
			List<Map<String, Object>> sourceList = ImportExcelUtils.readExcel(fileName, inputStream);
			ybUserService.impTeaData(sourceList);
			map.put("status", "200");
			map.put("message", sourceList.size());
		} catch (Exception e) {
			map.put("status", "100");
			e.printStackTrace();
		}
		return JSON.toJSONString(map);
	}

	@RequestMapping(value = { "clubsImport" })
	@ResponseBody
	public String clubsImport(@RequestParam(value = "file", required = true) MultipartFile file,
			HttpServletRequest request) {// 社团数据导入
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String fileName = file.getOriginalFilename();
			InputStream inputStream = file.getInputStream();
			List<Map<String, Object>> sourceList = ImportExcelUtils.readExcel(fileName, inputStream);
			ybUserService.impClubData(sourceList);
			map.put("status", "200");
			map.put("message", sourceList.size());

		} catch (Exception e) {
			map.put("status", "100");
			e.printStackTrace();
		}
		return JSON.toJSONString(map);
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
		System.out.println("indexs" + indexs);
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
	public String sendMessage(Imessage imessage, String indexStr, HttpServletRequest request,
			HttpServletResponse response) throws IOException {// 发送消息
		/**
		 * 暂不存消息队列 map.put("producerKey", producerKey); map.put("theme",
		 * imessage.getTheme()); map.put("content", imessage.getContent());
		 * map.put("rever", rever); map.put("consumerKey", indexStr);
		 * producerService.sendMapMessage(destination, map);
		 */
		String id="29905380";
		String rever = QueueUtils.getReverStr(indexStr);
		//String yb_userid = QueueUtils.getyb_userid(request, response);
		imessageService.saveImessage(id, imessage.getTheme(), imessage.getContent(), rever, indexStr);
		return "redirect:" + Global.getAdminPath() + "/queue/queue/indexIcon";
	}

	@RequestMapping(value = "/arrComit")
	public String arrComit(String[] indexs, Model model) {
		String indexStr = StringUtils.join(indexs, ",");
		System.out.println("indexStr" + indexStr);
		model.addAttribute("indexStr", indexStr);
		return "modules/received/page";
	}

	@RequestMapping(value = { "indexIcon" })
	public String indexIcon(Model model) {
		List<Imessage> messageList = imessageService.getMeNewsList();
		model.addAttribute("messageList", messageList);
		return "modules/received/index-icon";
	}

	@RequestMapping(value = { "lookMessage" })
	public String lookMessage(String id, Model model) {
		if (StringUtils.isNotBlank(id)) {
			Imessage imessage = imessageService.get(id);
			model.addAttribute("imessage", imessage);
		}
		return "modules/received/lookMessage";
	}

	@RequestMapping(value = { "userMessage" })
	public String userMessage() {// 用户消息列表
		return "modules/received/userMessage";
	}

	@RequestMapping(value = { "historyMessage" })
	public String historyMessage(HttpServletRequest request,
			@RequestParam(value = "pageNo", required = false) Integer pageNo, Model model) {// 根据当前登录角色获取历史消息
		request.getSession().getAttribute("");// 当前登录用户
		String role = "admin";// 暂时初始化为管理员，可管理所有消息
		if ("".equals(pageNo) || pageNo == null) {
			pageNo = 1;
		}
		List<Imessage> messageList = imessageService.getMePageList(pageNo);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("messageList", messageList);
		return "modules/received/historyMessage";
	}

	@RequestMapping(value = { "csmDetails" })
	public String csmDetails(String id, Model model) {
		if (StringUtils.isNotBlank(id)) {
			Imessage imessage = imessageService.get(id);
			List<Consumer> mList = imessage.getConList();
			model.addAttribute("mList", mList);
		}
		return "modules/received/consumerDetails";

	}

	@RequestMapping(value = { "searchYbUser" })
	public String searchYbUser(String yb_userid, String yb_realname, Model model) {// 搜索指定人员
		List<YbUser> list = null;
		if (StringUtils.isNotBlank(yb_userid) && StringUtils.isBlank(yb_realname)) {
			list = ybUserService.findStuList(yb_userid);
		} else if (StringUtils.isBlank(yb_userid) && StringUtils.isNotBlank(yb_realname)) {
			list = ybUserService.searchpByName(yb_realname);
		} else if (StringUtils.isNotBlank(yb_userid) && StringUtils.isNotBlank(yb_realname)) {
			list = ybUserService.searchpByIdAndName(yb_userid, yb_realname);
		}
		model.addAttribute("userList", list);
		return null;
	}

	@RequestMapping(value = { "deleteImessage" })
	@ResponseBody
	public String deleteImessage(String id) {// 删除指定消息
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(id)) {
			try {
				imessageService.deleteImessage(id);
			} catch (Exception e) {
				map.put("status", "100");
			}
			map.put("status", "200");
		}
		return JSON.toJSONString(map);

	}

	@RequestMapping(value = { "recallImessage" })
	public String recallImessage(String id) {// 撤回操作
		if (StringUtils.isNotBlank(id)) {
			try {
				imessageService.recallImessage(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@RequestMapping(value = { "authority" })
	public String authority() {// 权限分配
		return "modules/received/authorityPageList";
	}

	@RequestMapping(value = { "authorityNext" })
	public String authorityNext() {
		return "modules/received/authorityNext";
	}

	@RequestMapping(value = { "authorityLoadClaban" })
	public String authorityLoadClaban(String tieId, Model model) {
		if (StringUtils.isNotBlank(tieId)) {
			Tie tie = tieService.get(tieId);
			if (tie != null) {
				List<Claban> claList = tie.getClaList();
				model.addAttribute("claList", claList);
			}
		}
		return "modules/received/authorityNext";
	}

	@RequestMapping(value = { "authorityLoadStudent" })
	public String authorityLoadStudent(String claId, Model model) {// 分配角色，点击班别查询对应的学生
		if (StringUtils.isNotBlank(claId)) {
			Claban claban = clabanService.get(claId);
			if (claban != null) {
				List<YbUser> userList = claban.getYbList();
				model.addAttribute("userList", userList);
			}
		}
		return "modules/received/authorityStu";
	}

	@RequestMapping(value = { "handleForm" })
	public String handleForm() {// 经办管理
		return "modules/received/handleForm";
	}

	@RequestMapping(value = { "addHandle" })
	public String addHandle() {// 添加角色
		return "modules/received/addhandle";
	}

	@RequestMapping(value = { "loadClubs" })
	public String loadClubs(@RequestParam(value = "clubsId", required = true) String clubsId, Model model) {
		Clubs clubs = clubsService.get(clubsId);
		List<YbUser> userList = clubs.getYbUserList();
		model.addAttribute("userList", userList);
		return "modules/received/clubsNextstep";
	}

	@RequestMapping(value = { "teacherManage" })
	public String teacherManage(Model modle) {
		String role = "teacher";
		List<YbUser> userList = ybUserService.findByRole(role);
		modle.addAttribute("userList", userList);
		return "modules/received/teacherNextstep";
	}

	@RequestMapping(value = { "myMessagePage" })
	public String myMessagePage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pageNo, Model model) {// 我发布的
		if ("".equals(pageNo) || pageNo == null) {
			pageNo = 1;
		}
		String yb_userid = QueueUtils.getyb_userid(request, response);
		List<Imessage> messageList = imessageService.getmyMePageList(yb_userid, pageNo);
		model.addAttribute("messageList", messageList);
		return "modules/received/myMessagePage";
	}

	@RequestMapping(value = { "deleteMessage" })
	@ResponseBody
	public String deleteMessage(@RequestParam(value = "id", required = true) String id) {// 删除通知
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			imessageService.deleteImessage(id);
			map.put("status", "200");
		} catch (Exception e) {
			map.put("status", "100");
			e.printStackTrace();
		}
		return JSON.toJSONString(map);
	}

	@RequestMapping(value = { "withdraw" })
	public String withdraw(@RequestParam(value = "id", required = true) String id) {// 撤回通知
		imessageService.recallImessage(id);
		return "redirect:" + Global.getAdminPath() + "/queue/queue/indexIcon";
	}
}
