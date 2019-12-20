package com.okflow.modules.received.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.okflow.modules.received.entity.Claban;
import com.okflow.modules.received.entity.Tie;
import com.okflow.modules.received.entity.YbUser;
import com.okflow.modules.received.service.ClabanService;
import com.okflow.modules.received.service.ClubsService;
import com.okflow.modules.received.service.ConsumerService;
import com.okflow.modules.received.service.GradeService;
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
@RequestMapping(value = { "${adminPath}/queue/mobile" })
public class MobileController {

	@Autowired
	private YbUserService ybUserService;
	@Autowired
	private TieService tieService;
	@Autowired
	private ClabanService clabanService;
	@Autowired
	private ImessageService imessageService;
	@Autowired
	private ClubsService clubsService;
	@Autowired
	private ConsumerService consumerService;
	@Autowired
	private GradeService gradeService;
	
	@RequestMapping(value = { "mobileSend" })
	public String mobile(HttpServletRequest request, HttpServletResponse response) {// 班长发送
		String yb_userid = QueueUtils.getyb_userid(request, response);// 当前登录易班Id
		String role = QueueUtils.getRole(yb_userid);
		if ("admin".equals(role)) {// 当前角色是管理员
			return "modules/received/mobile/adminMain";
			
		} else if ("handle".equals(role)) {// 经办

		} else if ("monitor".equals(role)) {// 班长

		} else if ("chairman".equals(role)) {// 会长

		} else if ("teacher".equals(role)) {// 老师

		}
		return null;

	}
	
	@RequestMapping(value= {"tieList"})
	public String tieList(Model model) {//学生管理-所有系别
		List<Tie> list  =  tieService.findTieList();
		model.addAttribute("list", list);
		return "modules/received/mobile/tieMain";
	}
	@RequestMapping(value= {"nextTie"})
	public String nextTie(String Tid,Model model) {//选择系别的下一步
		Tie tie = tieService.get(Tid);
		List<Claban> list = tie.getClaList();
		model.addAttribute("list", list);
		return "modules/received/mobile/clabanMain";
	}
	@RequestMapping(value= {"nextClaban"})
	public String nextClaban(String Tid,Model model) {//选择班级进行下一步
		Claban claban = clabanService.get(Tid);
		List<YbUser> list = claban.getYbList();
		model.addAttribute("list", list);
		return "modules/received/mobile/stuMain";
	}
	@RequestMapping(value= {"sendMessage"})
	public String sendMessage() {//发送消息
		return "modules/received/mobile/sendMessage";
	}
}
