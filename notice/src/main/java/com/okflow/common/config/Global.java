package com.okflow.common.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

import com.google.common.collect.Maps;
import com.okflow.common.utils.PropertiesLoader;

/**
 * 全局配置类
 * 
 * @author xiaofanglin
 * @version 
 */
public class Global {
	/** 保存全局属性值 */
	private static Map<String, String> map = Maps.newHashMap();
	/** 属性文件加载对象 */
	private static PropertiesLoader propertiesLoader = new PropertiesLoader("config.properties");
	/** 是否Development Mode */
	private static boolean isDevelopmentMode = false;

	public static boolean isDevelopmentMode() {
		return isDevelopmentMode;
	}

	public static void setDevelopmentMode(boolean isDevelopmentMode) {
		Global.isDevelopmentMode = isDevelopmentMode;
	}

	/** 业务中经办人复核人取值的模式 true 新取法，false 旧取法 */
	public static boolean dealOperatorMode = true;

	/** 获取配置 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null) {
			value = propertiesLoader.getProperty(key);
			map.put(key, value);
		}
		return value;
	}

	/** 获取管理端根路径 */
	public static String getAdminPath() {
		return getConfig("adminPath");
	}

	/** 获取前端根路径 */
	public static String getFrontPath() {
		return getConfig("frontPath");
	}

	/** 获取URL后缀 */
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}

	/** 是否是演示模式，演示模式下不能修改用户、角色、密码、菜单、授权 */
	public static Boolean isDemoMode() {
		String dm = getConfig("demoMode");
		return "true".equals(dm) || "TRUE".equals(dm) || "1".equals(dm);
	}

	/** 在修改系统用户和角色时是否同步到Activiti */
	public static Boolean isSynActivitiIndetity() {
		String dm = getConfig("activiti.isSynActivitiIndetity");
		return "true".equals(dm) || "TRUE".equals(dm) || "1".equals(dm);
	}

	/** 是否启用CA登录，正式环境中应设置为true */
	public static Boolean enableCaLogin() {
		String dm = getConfig("enablecalogin");
		return "true".equals(dm) || "TRUE".equals(dm) || "1".equals(dm);
	}

	/**
	 * 获取CKFinder上传文件的根目录
	 * 
	 * @return
	 */
	public static String getCkBaseDir() {
		String dir = getConfig("userfiles.basedir");
		Assert.hasText(dir, "配置文件里没有配置userfiles.basedir属性");
		if (!dir.endsWith("/")) {
			dir += "/";
		}
		return dir;
	}

	/** 保存人员当前信息 */
	public static Integer OPERATOR_SAVE_CURRENT = 0;
	/** 计算最新工资 */
	public static Integer OPERATOR_CALCULATE_NEWEST_SALARY = 1;
	/** 计算历史工资 */
	public static Integer OPERATOR_CALCULATE_HISTORICAL_SALARY = 2;
	/** 人员解锁 */
	public static Integer OPERATOR_UNLOCK = 3;
	/** 无 */
	public static String NULL = "00";
	/** 流程salary_hrss */
	public static String PROCESSKEY_SALARY_HRSS = "salary_hrss";
	/** 流程salary_admin1 一级主管单位 */
	public static String PROCESSKEY_SALARY_ADMIN1 = "salary_admin1";
	/** 流程salary_admin2 二级主管单位 */
	public static String PROCESSKEY_SALARY_ADMIN2 = "salary_admin2";
	/** 流程salary_admin3 三级主管单位 */
	public static String PROCESSKEY_SALARY_ADMIN3 = "salary_admin3";
	/** 流程salary_admin4 四级主管单位 */
	public static String PROCESSKEY_SALARY_ADMIN4 = "salary_admin4";
	/** 流程salary_admin5 五级主管单位 */
	public static String PROCESSKEY_SALARY_ADMIN5 = "salary_admin5";
	/** 市县流程salary_hrss_local */
	public static String PROCESSKEY_SALARY_HRSS_LOCAL = "salary_hrss_local";
	/** 市县流程salary_admin_local1 一级主管单位 */
	public static String PROCESSKEY_SALARY_ADMIN_LOCAL1 = "salary_admin_local1";
	/** 市县流程salary_admin_local2 二级主管单位 */
	public static String PROCESSKEY_SALARY_ADMIN_LOCAL2 = "salary_admin_local2";
	/** 市县流程salary_admin3_local3 三级主管单位 */
	public static String PROCESSKEY_SALARY_ADMIN_LOCAL3 = "salary_admin_local3";
	/** 北海流程salary_hrss_leader_local */
	public static String PROCESSKEY_SALARY_HRSS_LD = "salary_hrss_ld";
	/** 北海流程salary_admin_leader_local1 一级主管单位 */
	public static String PROCESSKEY_SALARY_ADMIN_LD1 = "salary_admin_ld1";
	/** 北海流程salary_admin_leader_local2 二级主管单位 */
	public static String PROCESSKEY_SALARY_ADMIN_LD2 = "salary_admin_ld2";
	/** 北海流程处级salary_hrss_ld_od */
	public static String PROCESSKEY_SALARY_HRSS_LD_OD = "salary_hrss_ld_od";
	/** 北海流程处级salary_admin_ld_od1 */
	public static String PROCESSKEY_SALARY_ADMIN_LD_OD1 = "salary_admin_ld_od1";
	/** 独生子女流程 */
	public static String PROCESSKEY_RETIRE_ADMIN1 = "retire_admin1";
	/** 独生子女流程 */
	public static String PROCESSKEY_RETIRE_ADMIN2 = "retire_admin2";
	/** 独生子女流程 */
	public static String PROCESSKEY_RETIRE_ADMIN3 = "retire_admin3";
	/** 市县通用业务流程-无主管单位 */
	public static String PROCESSKEY_SALARY_HRSS_BH = "salary_hrss_bh";
	/** 市县通用业务流程-一级主管单位 */
	public static String PROCESSKEY_SALARY_ADMIN_BH1 = "salary_admin_bh1";
	/** 市县通用业务流程-二级主管单位 */
	public static String PROCESSKEY_SALARY_ADMIN_BH2 = "salary_admin_bh2";
	/** 基层单位业务办理状态-填写依据 */
	public static String DEAL_TRANSACT_GIST = "0";
	/** 基层单位业务办理状态-呈报 */
	public static String DEAL_TRANSACT_REPORT = "1";
	/** 基层单位业务办理状态-提交审批(完成) */
	public static String DEAL_TRANSACT_APPROVE = "2";
	/** 业务性质 - 机关在职 01 */
	public static String BUSINESS_PROPERTY_JGZZ = "01";
	/** 角色 - 经办 */
	public static String ROLE_JB = "经办";
	/** 角色 - 单位U盾 */
	public static String ROLE_DWUD = "单位U盾";
	/** 角色 - 工资福利处 */
	public static String ROLE_GXHRSS = "工资福利处";
	/** 角色 - 工资福利科 */
	public static String ROLE_CITYHRSS = "工资福利科";
	/** 角色 - 工资福利股 */
	public static String ROLE_COUNTYHRSS = "工资福利股";
	/** 角色 - 局领导 */
	public static String ROLE_THIRDHRSS = "局领导";
	/** 角色 - 组织部 */
	public static String ROLE_FOURHRSS = "组织部";
	/** 业务性质 - 机关离退休 0103 */
	public static String BUSINESS_PROPERTY_JGLTX = "0103";
	/** 业务性质 - 事业在职 02 */
	public static String BUSINESS_PROPERTY_SYZZ = "02";
	/** 业务性质 - 事业运动员0201 */
	public static String BUSINESS_PROPERTY_SYYDY = "0201";
	/** 业务性质 - 事业离退休 0203 */
	public static String BUSINESS_PROPERTY_SYLTX = "0203";
	/** 性别 - 男 = 1 */
	public static final String SEX_MAN = "1";
	/** 性别 - 女 = 2 */
	public static final String SEX_WOMAN = "2";
	/** 退休类型 - 离休 */
	public static final String RETIRED_LX = "01";
	/** 退休类型 - 退休 */
	public static final String RETIRED_TX = "02";
	/** 退休类别 - 享受原工资100%退休费的退休老专家、起义人员、老工人 */
	public static final String RETIRED_EXPERT = "03";
	/** 退休类型 - 退职 */
	public static final String RETIRED_TZ = "04";
	/** 退休类型 - 镇南关 */
	public static final String RETIRED_ZNG = "05";
	/** 进入单位方式 - 考试录用01 */
	public static final String JRDWFS_KSLY = "01";
	/** 进入单位方式 - 调入02 */
	public static final String JRDWFS_DR = "02";
	/** 进入单位方式 - 军转干安置03 */
	public static final String JRDWFS_JZGAZ = "03";
	/** 进入单位方式 - 选调04 */
	public static final String JRDWFS_XD = "04";
	/** 进入单位方式 - 公开招聘05 */
	public static final String JRDWFS_GKZP = "05";
	/** 进入单位方式 - 其他06 */
	public static final String JRDWFS_QT = "06";
	/** 进入单位方式 - 转隶调入09 */
	public static final String JRDWFS_ZL = "09";
	/** 人员新增机关需要材料列表 */
	public static final String NEWEMPACCY_JG = "{obj:all|type1:4,5,59" // 组织部直接管理的领导干部
			+ "|type2:1,2,4,5,59" // 人员调动主管部门:自治区公务员局主管;新增方式:转任、调任
			+ "|type3:1,7,59" // 人员调动主管部门:自治区公务员局主管;新增方式:从应届毕业生中录用
			+ "|type4:1,7,60,59" // 人员调动主管部门:自治区公务员局主管;新增方式:录用有工作经验的(9:劳动合同+10:人事代理)
			+ "|type5:1,2,5,7,11,12,59" // 人员调动主管部门:自治区公务员局主管;新增方式:接收军转干部
			+ "|type6_1:2,4,5,59" // 人员调动主管部门:自治区党委组织部;新增方式:转任 (如果是区直机关人员之间转任)
			+ "|type6_2:1,2,4,5,59" // 人员调动主管部门:自治区党委组织部;新增方式:转任 (如果是从市县和外省转任)
			+ "|type7:2,4,5,6,59" // 人员调动主管部门:自治区党委组织部;新增方式:调任
			+ "|type8:7,8,59" // 人员调动主管部门:自治区党委组织部;新增方式:从应届毕业生中录用
			+ "|type9:7,8,60,59" // 人员调动主管部门:自治区党委组织部;新增方式:录用有工作经验的
			+ "|type10:2,5,7,12,59}"; // 人员调动主管部门:自治区党委组织部;新增方式:接收军转干部
	/** 人员新增事业需要材料列表 */
	public static final String NEWEMPACCY_SY = "{obj:all|type11:32,34,35,59" // 组织部直接管理的领导干部
			+ "|type12:31,32,34,35,59" // 新增方式：调入
			+ "|type13:31,36,59" // 新增方式：从应届毕业生中录用
			+ "|type14:31,32,36,60,59" // 新增方式：录用有工作经验的
			+ "|type15:31,32,35,36,39,40,59}"; // 新增方式：接收军转干部
	public static Map<String, String> aloneBusinessName = new HashMap<String, String>();
	static {
		aloneBusinessName.put("1", "11550");
		aloneBusinessName.put("2", "11700");
		aloneBusinessName.put("3", "");
		aloneBusinessName.put("4", "11900");
		aloneBusinessName.put("5", "12000");
		aloneBusinessName.put("6", "32200");
		aloneBusinessName.put("7", "12300");
		aloneBusinessName.put("8", "12400");
		aloneBusinessName.put("9", "12500");
		aloneBusinessName.put("10", "");
		aloneBusinessName.put("11", "12600");
		aloneBusinessName.put("12", "11001");
		aloneBusinessName.put("13", "31003");
		aloneBusinessName.put("14", "11004");
		aloneBusinessName.put("15", "11005");
		aloneBusinessName.put("16", "11101");
		aloneBusinessName.put("17", "11102");
		aloneBusinessName.put("18", "11201");
		aloneBusinessName.put("19", "11202");
		aloneBusinessName.put("20", "11401");
		aloneBusinessName.put("21", "11403");
		aloneBusinessName.put("22", "11404");
		aloneBusinessName.put("20140301", "11501");
		aloneBusinessName.put("20140302", "11502");
		aloneBusinessName.put("20140303", "11503");
		aloneBusinessName.put("20140304", "11504");
		aloneBusinessName.put("90", "");
		aloneBusinessName.put("91", "");
		aloneBusinessName.put("20140305", "12801");
		aloneBusinessName.put("20140306", "12802");
		aloneBusinessName.put("20130307", "12806");
		aloneBusinessName.put("20140327", "13100");
		aloneBusinessName.put("23", "21550");
		aloneBusinessName.put("24", "21800");
		aloneBusinessName.put("25", "22100");
		aloneBusinessName.put("26", "");
		aloneBusinessName.put("27", "22100");
		aloneBusinessName.put("28", "22400");
		aloneBusinessName.put("29", "22500");
		aloneBusinessName.put("30", "22600");
		aloneBusinessName.put("31", "22700");
		aloneBusinessName.put("32", "22800");
		aloneBusinessName.put("33", "");
		aloneBusinessName.put("34", "23000");
		aloneBusinessName.put("35", "21001");
		aloneBusinessName.put("36", "21002");
		aloneBusinessName.put("37", "21003");
		aloneBusinessName.put("38", "21101");
		aloneBusinessName.put("39", "21102");
		aloneBusinessName.put("40", "21103");
		aloneBusinessName.put("41", "21104");
		aloneBusinessName.put("42", "21108");
		aloneBusinessName.put("43", "21109");
		aloneBusinessName.put("2015012301", "21107");
		aloneBusinessName.put("2015012302", "21105");
		aloneBusinessName.put("2015012303", "21106");
		aloneBusinessName.put("44", "21110");
		aloneBusinessName.put("45", "21111");
		aloneBusinessName.put("46", "21112");
		aloneBusinessName.put("47", "21201");
		aloneBusinessName.put("48", "21202");
		aloneBusinessName.put("49", "21203");
		aloneBusinessName.put("50", "21204");
		aloneBusinessName.put("51", "12801");
		aloneBusinessName.put("52", "12804");
		aloneBusinessName.put("53", "12804");
		aloneBusinessName.put("54", "12805");
		aloneBusinessName.put("55", "12807");
		aloneBusinessName.put("20140311", "23205");
		aloneBusinessName.put("20140312", "23202");
		aloneBusinessName.put("20140313", "23201");
		aloneBusinessName.put("20140314", "21401");
		aloneBusinessName.put("20140315", "21402");
		aloneBusinessName.put("20140316", "21403");
		aloneBusinessName.put("20140317", "21404");
		aloneBusinessName.put("511", "");
		aloneBusinessName.put("521", "");
		aloneBusinessName.put("531", "23203");
		aloneBusinessName.put("541", "23204");
		aloneBusinessName.put("551", "23206");
		aloneBusinessName.put("56", "21181");
		aloneBusinessName.put("57", "21181");
		aloneBusinessName.put("58", "21181");
		aloneBusinessName.put("60", "13206");
		aloneBusinessName.put("61", "13207");
		aloneBusinessName.put("62", "13208");
		aloneBusinessName.put("63", "13209");
		aloneBusinessName.put("1001", "");
		aloneBusinessName.put("4000", "");
		aloneBusinessName.put("4001", "");
		aloneBusinessName.put("4002", "");
		aloneBusinessName.put("4003", "");
		aloneBusinessName.put("5001", "");
		aloneBusinessName.put("5002", "");
		aloneBusinessName.put("5003", "");
		aloneBusinessName.put("5004", "11102");
		aloneBusinessName.put("6001", "");
		aloneBusinessName.put("9001", "11705");
		aloneBusinessName.put("9002", "11550");
		aloneBusinessName.put("9004", "21805");
		aloneBusinessName.put("9005", "21700");
		aloneBusinessName.put("9006", "");
		aloneBusinessName.put("9007", "21501");
		aloneBusinessName.put("9008", "");
		aloneBusinessName.put("9009", "22900");
		aloneBusinessName.put("9100", "23301");
		aloneBusinessName.put("9101", "23302");
		aloneBusinessName.put("9102", "23303");
		aloneBusinessName.put("9103", "23301");
		aloneBusinessName.put("9104", "23305");
		aloneBusinessName.put("9105", "23308");
		aloneBusinessName.put("9150", "23306");
		aloneBusinessName.put("9106", "11401");
		aloneBusinessName.put("9107", "11402");
		aloneBusinessName.put("9109", "21502");
		aloneBusinessName.put("9110", "12100");
		aloneBusinessName.put("9111", "11305");
		aloneBusinessName.put("9112", "11302");
		aloneBusinessName.put("9113", "11303");
		aloneBusinessName.put("9114", "11304");
		aloneBusinessName.put("2015030901", "11307");
		aloneBusinessName.put("9115", "12808");
		aloneBusinessName.put("9120", "12809");
		aloneBusinessName.put("9122", "12810");
		aloneBusinessName.put("2015030904", "12818");
		aloneBusinessName.put("9116", "21301");
		aloneBusinessName.put("9117", "21302");
		aloneBusinessName.put("9118", "21303");
		aloneBusinessName.put("2015030902", "21305");
		aloneBusinessName.put("9119", "23207");
		aloneBusinessName.put("9121", "23208");
		aloneBusinessName.put("2015030903", "23217");
		aloneBusinessName.put("9130", "22200");
		aloneBusinessName.put("100000", "");
		aloneBusinessName.put("8888", "21181");
		aloneBusinessName.put("2015020101", "11002");
		aloneBusinessName.put("2015033101", "12700");
		aloneBusinessName.put("2015040101", "23005");
		aloneBusinessName.put("2015051701", "32000");
		aloneBusinessName.put("2015053001", "11306");
		aloneBusinessName.put("2015053002", "12803");
		aloneBusinessName.put("2015053003", "21304");
		aloneBusinessName.put("2015053004", "23215");
		aloneBusinessName.put("2015071401", "");
		aloneBusinessName.put("2016090901", "11307");
		aloneBusinessName.put("2016090902", "12818");
		aloneBusinessName.put("2016090903", "21305");
		aloneBusinessName.put("2016090904", "23217");
		aloneBusinessName.put("2016090905", "");
		aloneBusinessName.put("2015080801", "23100");
		aloneBusinessName.put("2015080802", "13200");
		aloneBusinessName.put("2015090801", "23307");
		aloneBusinessName.put("2015091001", "23213");
		aloneBusinessName.put("2015091002", "12815");
		aloneBusinessName.put("2015091201", "11405");
		aloneBusinessName.put("2015091202", "11406");
		aloneBusinessName.put("2015091203", "12811");
		aloneBusinessName.put("2015091204", "12812");
		aloneBusinessName.put("2015091205", "21503");
		aloneBusinessName.put("2015091206", "21504");
		aloneBusinessName.put("2015091207", "23209");
		aloneBusinessName.put("2015091208", "23210");
		aloneBusinessName.put("2016020301", "23211");
		aloneBusinessName.put("2016020302", "12813");
		aloneBusinessName.put("2016020303", "23212");
		aloneBusinessName.put("2016020304", "12814");
		aloneBusinessName.put("2016020305", "23212");
		aloneBusinessName.put("2016020306", "12814");
		aloneBusinessName.put("2016021901", "23214");
		aloneBusinessName.put("2016021902", "12816");
		aloneBusinessName.put("2016031001", "");
		aloneBusinessName.put("2016031002", "");
		aloneBusinessName.put("2016060501", "");
		aloneBusinessName.put("2016061301", "22200");
		aloneBusinessName.put("2016101901", "12002");
		aloneBusinessName.put("2016112101", "");
		aloneBusinessName.put("2016112102", "");
		aloneBusinessName.put("2017020801", "13301");
		aloneBusinessName.put("2017021802", "13302");
		aloneBusinessName.put("2017020802", "13303");
		aloneBusinessName.put("2017021801", "13305");
		aloneBusinessName.put("2017120601", "");
		aloneBusinessName.put("2017021803", "13304");
		aloneBusinessName.put("2017050201", "14001");
		aloneBusinessName.put("2017050202", "14003");
		aloneBusinessName.put("2017050203", "14004");
		aloneBusinessName.put("2017071901", "");
		aloneBusinessName.put("2018071810", "11408"); // 厅管二级局或直属副厅机关高定 2018-07-20
		aloneBusinessName.put("2017121101", "15001"); // 机关纠错
		aloneBusinessName.put("2017122701", "15001"); // 机关纠错
		aloneBusinessName.put("2017122702", "15001"); // 机关纠错
		aloneBusinessName.put("2017122703", "15001"); // 机关纠错
		aloneBusinessName.put("2017121102", "24001"); // 事业纠错
		aloneBusinessName.put("2017122704", "24001"); // 事业纠错
		aloneBusinessName.put("2017122705", "24001"); // 事业纠错
	}
	/** 采用双人审批模式的主管单位 */
	public static Map<String, String> twoCheckCompanyNames = new HashMap<String, String>();
	static {
		twoCheckCompanyNames.put("10938", "广西壮族自治区农业厅");
	}
	public static Map<String, Integer> codeItem99999 = new HashMap<String, Integer>();
	static {
		codeItem99999.put("02", 8814);
		codeItem99999.put("03", 8815);
		codeItem99999.put("04", 8816);
		codeItem99999.put("05", 8817);
		codeItem99999.put("06", 8818);
		codeItem99999.put("07", 8819);
		codeItem99999.put("08", 8820);
		codeItem99999.put("09", 8821);
		codeItem99999.put("10", 8822);
		codeItem99999.put("11", 8823);
		codeItem99999.put("12", 8824);
		codeItem99999.put("4108", 2016122308);
		codeItem99999.put("4109", 2016122309);
		codeItem99999.put("4110", 2016122310);
		codeItem99999.put("4111", 2016122311);
		codeItem99999.put("4112", 2016122312);
		codeItem99999.put("4201", 2016122313);
		codeItem99999.put("4202", 2016122314);
		codeItem99999.put("4203", 2016122315);
		codeItem99999.put("4204", 2016122316);
		codeItem99999.put("4205", 2016122317);
		codeItem99999.put("4206", 2016122318);
		codeItem99999.put("4207", 2016122319);
		codeItem99999.put("4208", 2016122320);
		codeItem99999.put("4209", 2016122321);
		codeItem99999.put("4210", 2016122322);
		codeItem99999.put("4211", 2016122323);
		codeItem99999.put("4212", 2016122324);
		codeItem99999.put("4301", 2017021801);
		codeItem99999.put("4302", 2017021802);
		codeItem99999.put("4303", 2017021803);
		codeItem99999.put("4304", 2017021804);
		codeItem99999.put("4305", 2017021805);
		codeItem99999.put("4306", 2017021806);
		codeItem99999.put("4307", 2017021807);
		codeItem99999.put("4308", 2017021808);
		codeItem99999.put("4309", 2017021809);
		codeItem99999.put("4310", 2017021810);
		codeItem99999.put("4311", 2017021811);
		codeItem99999.put("4312", 2017021812);
		codeItem99999.put("4313", 2017021813);
		codeItem99999.put("4314", 2017021814);
		codeItem99999.put("4315", 2017021815);
		codeItem99999.put("4316", 2017021816);
		codeItem99999.put("4317", 2017021817);
		codeItem99999.put("4318", 2017021818);
		codeItem99999.put("31", 2017042801);
		codeItem99999.put("32", 2017042802);
		codeItem99999.put("33", 2017042803);
		codeItem99999.put("34", 2017042804);
		codeItem99999.put("35", 2017042805);
		codeItem99999.put("36", 2017042806);
		codeItem99999.put("37", 2017042807);
		codeItem99999.put("38", 2017042808);
		codeItem99999.put("39", 2017042809);
		codeItem99999.put("41", 2017042810);
		codeItem99999.put("42", 2017042811);
		codeItem99999.put("43", 2017042812);
		codeItem99999.put("51", 2017042813);
		codeItem99999.put("52", 2017042814);
		codeItem99999.put("53", 2017042815);
		codeItem99999.put("54", 2017042816);
		codeItem99999.put("55", 2017042817);
		codeItem99999.put("56", 2017042818);
		codeItem99999.put("57", 2017042819);
		codeItem99999.put("58", 2017042820);
		codeItem99999.put("59", 2017042821);
		codeItem99999.put("61", 2017042822);
		codeItem99999.put("62", 2017042823);
		codeItem99999.put("4101", 2016122301);
		codeItem99999.put("4102", 2016122302);
		codeItem99999.put("4103", 2016122303);
		codeItem99999.put("4104", 2016122304);
		codeItem99999.put("4105", 2016122305);
		codeItem99999.put("4106", 2016122306);
		codeItem99999.put("4107", 2016122307);
		codeItem99999.put("14", 9030);
		codeItem99999.put("15", 9031);
		codeItem99999.put("16", 9032);
		codeItem99999.put("17", 9033);
		codeItem99999.put("18", 9034);
		codeItem99999.put("19", 9035);
		codeItem99999.put("20", 9036);
		codeItem99999.put("1", 8679);
		codeItem99999.put("2", 8680);
		codeItem99999.put("3", 8681);
		codeItem99999.put("4", 8682);
		codeItem99999.put("5", 8683);
	}
	public static Map<String, Integer> codeItem1 = new HashMap<String, Integer>();
	static {
		codeItem1.put("0101", 1); // 公务员
		codeItem1.put("0102", 2); // 机关技术工人
		codeItem1.put("0103", 3); // 机关普通工人
		codeItem1.put("0104", 4); // 试用期公务员
		codeItem1.put("0105", 5); // 机关学徒期技术工人
		codeItem1.put("0106", 6); // 机关熟练期普通工人
		codeItem1.put("0201", 7); // 管理人员
		codeItem1.put("0202", 8); // 专业技术
		codeItem1.put("0203", 9); // 事业技术工人
		codeItem1.put("0204", 10); // 事业普通工人
		codeItem1.put("0205", 11); // 见习期管理人员
		codeItem1.put("0206", 12); // 见习期专业技术人员
		codeItem1.put("0207", 13); // 事业学徒期技术工人
		codeItem1.put("0208", 14); // 事业熟练期普通工人
		codeItem1.put("0209", 15); // 初期
		codeItem1.put("0301", 16); // 试训运动员
		codeItem1.put("0302", 17); // 运动员
		codeItem1.put("0303", 18); // 登山运动员
	}
	public static Map<String, Integer> codeItem99999_ = new HashMap<String, Integer>();
	static {
		codeItem99999_.put("01", 8833); // 正高一级
		codeItem99999_.put("02", 8834); // 正高二级
		codeItem99999_.put("03", 8835); // 正高三级
		codeItem99999_.put("04", 8836); // 正高四级
		codeItem99999_.put("05", 8837); // 副高五级
		codeItem99999_.put("06", 8838); // 副高六级
		codeItem99999_.put("07", 8839); // 副高七级
		codeItem99999_.put("08", 8840); // 中级八级
		codeItem99999_.put("09", 8841); // 中级九级
		codeItem99999_.put("10", 8842); // 中级十级
		codeItem99999_.put("11", 8843); // 助理十一级
		codeItem99999_.put("12", 8844); // 助理十二级
		codeItem99999_.put("13", 8845); // 员级十三级
		codeItem99999_.put("1", 8679); // 高级技师
		codeItem99999_.put("2", 8680); // 技师
		codeItem99999_.put("3", 8681); // 高级工
		codeItem99999_.put("4", 8682); // 中级工
		codeItem99999_.put("5", 8683); // 初级工
	}

	/***
	 * 以下机关单位其津补贴是可以参照南宁市的津补贴,其他的区直驻邕机关只能参照驻邕津补贴标准
	 */
	public static Map<String, String> company_nnbz = new HashMap<String, String>();
	static {
		company_nnbz.put("10371", "广西区监狱管理局南宁转送站");
		company_nnbz.put("10219", "广西壮族自治区女子监狱");
		company_nnbz.put("12613", "广西壮族自治区森林公安局高峰派出所");
		company_nnbz.put("10450", "黎塘监狱");
		company_nnbz.put("10318", "南宁市地方税务局");
		company_nnbz.put("10464", "广西壮族自治区新康监狱");
		company_nnbz.put("10506", "上林县地方税务局");
		company_nnbz.put("10540", "隆安县地方税务局");
		company_nnbz.put("11224", "崇左市无线电管理处");
		company_nnbz.put("10321", "横县地方税务局");
		company_nnbz.put("10218", "广西壮族自治区南宁监狱");
		company_nnbz.put("12737", "广西壮族自治区警官学校");
		company_nnbz.put("10704", "武鸣县地方税务局");
		company_nnbz.put("10708", "南宁市无线电管理处");
		company_nnbz.put("10726", "宾阳县地方税务局");
		company_nnbz.put("10782", "南宁市水文水资源局");
		company_nnbz.put("11386", "广西女子强制隔离戒毒所");
		company_nnbz.put("10958", "广西壮族自治区未成年犯管教所");
		company_nnbz.put("10960", "马山县地方税务局");
		company_nnbz.put("12609", "广西壮族自治区森林公安局良凤江派出所");
		company_nnbz.put("12614", "广西壮族自治区森林公安局七坡派出所");
		company_nnbz.put("10724", "南宁铁路运输法院");
		company_nnbz.put("11027", "南宁铁路运输中级法院");
		company_nnbz.put("10422", "广西壮族自治区人民检察院南宁铁路运输分院");
		company_nnbz.put("11037", "南宁铁路运输检察院");
		company_nnbz.put("10415", "广西第一强制隔离戒毒所");
		company_nnbz.put("12515", "广西第三强制隔离戒毒所");
		company_nnbz.put("11224", "广西区工信委崇左市无线电管理处");
		company_nnbz.put("10903", "广西壮族自治区信息安全评测中心");
		company_nnbz.put("12585", "广西壮族自治区地方税务局南宁稽查局");
	}

	/***
	 * 以下考核地方允许修改考核子集
	 */
	public static Map<String, String> standard_assessment = new HashMap<String, String>();
	static {
		standard_assessment.put("02yjq", "百色市-右江区");
		standard_assessment.put("102qzbj", "调查队-市本级调查队");
		standard_assessment.put("103qzwz", "调查队-梧州调查队");
		standard_assessment.put("104qzlz", "调查队-柳州调查队");
		standard_assessment.put("105qzgl", "调查队-桂林调查队");
		standard_assessment.put("106qzbh", "调查队-北海调查队");
		standard_assessment.put("107qzfcg", "调查队-防城港调查队");
		standard_assessment.put("108qzqz", "调查队-钦州调查队");
		standard_assessment.put("109qzgg", "调查队-贵港调查队");
		standard_assessment.put("110qzyl", "调查队-玉林调查队");
		standard_assessment.put("111qzbs", "调查队-百色调查队");
		standard_assessment.put("112qzhz", "调查队-贺州调查队");
		standard_assessment.put("113qzhc", "调查队-河池调查队");
		standard_assessment.put("114qzlb", "调查队-来宾调查队");
		standard_assessment.put("115qzcz", "调查队-崇左调查队");
		standard_assessment.put("116qzyn", "调查队-邕宁调查队");
		standard_assessment.put("117qzsl", "调查队-上林调查队");
		standard_assessment.put("118qzms", "调查队-马山调查队");
		standard_assessment.put("119qzfs", "调查队-扶绥调查队");
		standard_assessment.put("120qzdx", "调查队-大新调查队");
		standard_assessment.put("121qzxz", "调查队-象州调查队");
		standard_assessment.put("122qzxc", "调查队-忻城调查队");
		standard_assessment.put("123qzys", "调查队-阳朔调查队");
		standard_assessment.put("124qzxa", "调查队-兴安调查队");
		standard_assessment.put("125qzqz", "调查队-全州调查队");
		standard_assessment.put("126qzpl", "调查队-平乐调查队");
		standard_assessment.put("127qzlz", "调查队-鹿寨调查队");
		standard_assessment.put("128qzcx", "调查队-岑溪调查队");
		standard_assessment.put("129qztx", "调查队-藤县调查队");
		standard_assessment.put("130qzgp", "调查队-桂平调查队");
		standard_assessment.put("131qzpn", "调查队-平南调查队");
		standard_assessment.put("132qztl", "调查队-田林调查队");
		standard_assessment.put("133qzbb", "调查队-博白调查队");
		standard_assessment.put("134qzty", "调查队-田阳调查队");
		standard_assessment.put("135qztd", "调查队-田东调查队");
		standard_assessment.put("136qzjx", "调查队-靖西调查队");
		standard_assessment.put("137qzhj", "调查队-环江调查队");
		standard_assessment.put("138qznd", "调查队-南丹调查队");
		standard_assessment.put("139qzyz", "调查队-宜州调查队");
		standard_assessment.put("140qzda", "调查队-都安调查队");
		standard_assessment.put("141qzhp", "调查队-合浦调查队");
		standard_assessment.put("142qzls", "调查队-灵山调查队");
		standard_assessment.put("143qzpb", "调查队-浦北调查队");
		standard_assessment.put("144qznn", "调查队-南宁调查队");
		standard_assessment.put("147qzbl", "调查队-北流调查队");
		standard_assessment.put("146qzhx", "调查队-横县调查队");
		standard_assessment.put("145qzfc", "调查队-富川调查队");
	}
}
