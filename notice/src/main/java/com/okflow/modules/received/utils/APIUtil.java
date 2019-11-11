package com.okflow.modules.received.utils;

public class APIUtil {

	/*
	 * 获取当前用户已加入的公共群
	 */
	public static final String PUBLIC_GROUP = "https://openapi.yiban.cn/group/public_group";
	/*
	 * 上传文件至资料库
	 */
	public static final String UPLOAD = "https://openapi.yiban.cn/data/upload";
	/*
	 * 获取当前用户好友列表
	 */
	public static final String ME_LIST = "https://openapi.yiban.cn/friend/me_list";
	/*
	 * 获取易班推荐资讯
	 */
	public static final String YB_PUSH = "https://openapi.yiban.cn/news/yb_push";
	/*
	 * 
	 * 获取当前用户资料库文件下载列表
	 */
	public static final String DOWNLOAD = "https://openapi.yiban.cn/data/download";

	/*
	 * 
	 * 获取所属应用指定access_token的信息，expire_in值为0，则该授权凭证已过期，需要重新授权
	 */
	public static final String TOKEN_INFO = "https://openapi.yiban.cn/oauth/token_info";
	/*
	 * 
	 * 获取已授权用户的access_token
	 */
	public static final String access_token = "https://openapi.yiban.cn/oauth/access_token";
	/*
	 * 获取当前用户基本信息
	 */
	public static final String user_me = "https://openapi.yiban.cn/user/me";
	/*
	 * 获取当前用户实名信息
	 */
	public static final String user_real_me = "https://openapi.yiban.cn/user/real_me";
	/*
	 * 开发者主动取消指定用户的授权
	 */
	public static final String revoke_token = "https://openapi.yiban.cn/oauth/revoke_token";
	/*
	 * 向指定用户发送易班站内信应用提醒
	 */
	public static final String msg_letter = "https://openapi.yiban.cn/msg/letter";

}
