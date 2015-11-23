package com.android.lovehome.constant;


/**
 * 
 * 类名称:Constant 类描述:常量类 
 * 
 * @version 1.0.0
 * 
 */
public class Constant {
	// 测试
	public final static boolean isTest = false;
	// 承运商 http://10.0.6.204:8080/router/rest   正式http://api.56.zhaogang.com/carrierapi/router/rest
//	public final static String URL = "http://10.0.6.204:8080/router/rest";
	//UAT测试
	public final static String URL = "http://10.0.5.204:8080/carrier/router/rest";

	// 与服务器时间的偏差
	public static long TIME_DISTANCE = 100;;

	// 版本号
	public static String Version = "1.0";

	// GET网络请求
	public final static int GET = 0;
	// POST网络请求
	public final static int POST = 1;
	// PUT网络请求
	public final static int PUT = 2;
	// DELETE网络请求
	public final static int DELETE = 3;
	// BITMAP网络请求
	public final static int BITMAP = 4;

	// 注册 logistics.carrier.register.verify
	public final static String METHOD_APPREGISTER = "logistics.carrier.register.verify";
	// 完成注册接口
	public final static String METHOD_REGISTER = "logistics.carrier.register.insert";

	/*
	 * //登陆new:logisticsv2.entrust.login.get public final static String
	 * METHOD_APPLOGIN="logisticsv2.entrust.login.get";
	 */
	// 获取验证码logistics.carrier.verification.get
	public final static String METHOD_GETCODE = "logistics.carrier.verification.get";
	// 找回密码
	public final static String METHOD_FINDPASSWORD = "logistics.carrier.password.get";
	// 查看我的资料
	public final static String METHOD_MYINFO = "logistics.carrier.info.get";
	// 修改联系人
	public final static String METHOD_UPDATE_CONTACTSUER = "logistics.carrier.contactsuser.update";
	// 修改联系方式
	public final static String METHOD_UPDATE_CONTACTSTEL = "logistics.carrier.contactstel.update";
	// 修改密码
	public final static String METHOD_UPDATE_PASSWORD = "logistics.carrier.password.update";
	// 退出账号
	public final static String METHOD_EXITACCOUT = "logistics.carrier.logout.get";
	/** feng.xiao */
	// 获取消息列表
	public final static String METHOD_MSG_LIST = "logistics.carrier.graborder.message.lists";
	// 获取抢单广告位
	public final static String METHOD_GRAB_BANNER_LIST = "logistics.carrier.graborder.banner.get";
	// 获取抢单列表
	public final static String METHOD_GRAB_ORDER_LIST = "logistics.carrier.graborder.lists";
	// 是否打开抢单
	public final static String METHOD_TOOGLE_GRAB_ORDER = "logistics.carrier.graborder.receipt";
	// 抢单记录列表
	public final static String METHOD_GRAB_ORDER_RECORD = "logistics.carrier.graborder.history.lists";
	// 车辆列表
	public final static String METHOD_CAR_LIST = "logistics.carrier.vehicles.lists";
	// 新增车辆
	public final static String METHOD_CAR_ADD = "logistics.carrier.vehicles.insert";
	// 删除车辆
	public final static String METHOD_CAR_DELETE = "logistics.carrier.vehicles.delete";
	// 新增或修改定制路线
	public final static String METHOD_INSERT_OR_UPDATE_ROUTES = "logistics.carrier.graborder.route.update";
	// 线路列表
	public final static String METHOD_ROUTE_LIST = "logistics.carrier.graborder.route.lists";
	// 抢单报价
	public final static String METHOD_GRAB_OFFER = "logistics.carrier.quote.price.insert";
	// 确认车辆
	public final static String METHOD_CAR_SURE = "logistics.carrier.task.vehicle.insert";

	// 订单跟踪 任务账单列表
	public final static String METHOD_ORDER_LIST = "logistics.carrier.task.going.lists";
	// 获取上传证件信息
	public final static String METHOD_GETIMAGE_INFO = "logistics.carrier.imageinfo.get";
	// 上传证件信息
	public final static String METHOD_UPLOAD_FILE_INFO = "logistics.carrier.imageinfo.insert";
	// 上传图片信息
	public final static String METHOD_UPLOAD_IMAGE = "logistics.carrier.file.upload";
	// 获取协议内容
	public final static String METHOD_GET_AGREEMENT = "logistics.carrier.register.protocol.get";
/*	// 获取协议读取状态(已和版本更新接口合并，这个接口在2.1.1的下个版本中被废弃)
	public final static String METHOD_GET_AGREEMENT_TYPE = "logistics.carrier.register.protocol.status.get";*/
	// 更新协议状态
	public final static String METHOD_UPDATE_AGREEMENT_TYPE = "logistics.carrier.register.protocol.status.update";

	// 账单核对列表
	public final static String METHOD_BILL_LIST = "logistics.carrier.task.billing.lists";
	// 账单核对日期
	public final static String METHOD_BILL_DATE = "logistics.carrier.task.billMonths.get";
	// 订单详情
	public static final String METHOD_ORDER_DETAIL = "logistics.carrier.task.info.get";
	// 抢单详情
	public static final String METHOD_GRAB_DETAIL = "logistics.carrier.graborder.info.get";
	// 更新任务状态
	public static final String METHOD_UPDATE_STATUS = "logistics.carrier.task.status.update";
	// 版本更新
	public static final String METHOD_UPDATE="logistics.carrier.appstatus.get";
	//关于我们
	public static final String METHOD_ABOUTUS="logistics.carrier.aboutus.get";
	//获取帮助中心内容
	public static final String METHOD_HELPCENTER="logistics.carrier.helpcenter.get";
	/**
	 * 动画跳转的type,,右进入
	 */
	public final static int RIGHT_ENTER = 995;
	/**
	 * 动画跳转的type,,右退出
	 */
	public final static int RIGHT_QUITE = 996;
	/**
	 * 动画跳转的type,,下进入
	 */
	public final static int DOWN_ENTER = 997;
	/**
	 * 动画跳转的type,,下退出
	 */
	public final static int DOWN_QUITE = 998;
	/**
	 * 动画跳转的type,,无动画
	 */
	public final static int NO_ANIMATION = 999;

	/**
	 * 屏幕宽度为480
	 */
	public final static int SCREE_WIDTH_480 = 480;
	/**
	 * 屏幕宽度为540
	 */
	public final static int SCREE_WIDTH_540 = 540;
	/**
	 * 屏幕宽度为800
	 */
	public final static int SCREE_WIDTH_800 = 800;
	/**
	 * 屏幕宽度为1080
	 */
	public final static int SCREE_WIDTH_1080 = 1080;
	/**
	 * 验证码时间
	 */
	public final static int CODETIME = 60;
	/**
	 * 修改起点(首页——查参考价)
	 */
	public final static int MODIFY_CHECK_START = 100;
	/**
	 * 修改卸点（首页——查参考价)
	 */
	public final static int MODIFY_CHECK_END = 101;
	/**
	 * 选择货物(首页——查参考价)
	 */
	public final static int CHOICE_CHECK_TYPE = 102;
	/**
	 * 修改起点(首页——帮忙找车)
	 */
	public final static int MODIFY_DIRECT_START = 103;
	/**
	 * 修改卸点（首页——帮忙找车)
	 */
	public final static int MODIFY_DIRECT_END = 104;
	/**
	 * 选择货物(首页——帮忙找车)
	 */
	public final static int CHOICE_DIRECT_TYPE = 105;
	/**
	 * 查参考价(首页——查参考价)
	 */
	public final static int CHECK_QUERY_PRICE = 106;
	/**
	 * 上传资料
	 */
	public final static int UPLOAD_INFO = 107;
	/**
	 * 修改抢单城市
	 */
	public final static int UPDATE_GRAB_CITY = 108;
	/**
	 * 新增抢单线路
	 */
	public final static int ADD_GRAB_LINE = 109;
	/**
	 * 筛选起点
	 */
	public final static int FILTER_START_CITY = 110;
	/**
	 * 查看未读消息
	 */
	public final static int GET_DISREAD_MSG = 111;
	/**
	 * 新增车辆
	 */
	public final static int CAR_ADD = 112;
	/**
	 * 修改抢单线路
	 */
	public final static int UPDATE_GRAB_LINE = 113;

	/**
	 * 注册
	 */
	public final static String REGIST = "regist";
	/**
	 * 找回密码
	 */
	public final static String FINDPASSWORD = "findpassword";
	/**
	 * 修改密码
	 */
	public final static String UPDATE_PASSWORD = "updatepassword";

	/**
	 * 推送消息数目 (先假设有一个数据)
	 */
	public static int PUSH_MSG_NUM = 0;

	/**
	 * 保存状态
	 */
	public final static String TAG_YUYINTYPE = "tag_yuyin";
	/**
	 * 推送action
	 */
	public final static String PUSHACTION = "com.zhaogang.weituowuliu.pushaction";
	/**
	 * 推送type
	 */
	public final static String PUSHTYPE = "com.zhaogang.weituowuliu.pushtype";
	/**
	 * 语音+震动
	 */
	public final static int TAG_YUYIN = 1;
	/**
	 * 提示
	 */
	public final static int TAG_TISHI = 2;
	/**
	 * 震动
	 */
	public final static int TAG_ZHENGDONG = 3;
	// 文件名
	public final static String MEMBERINFO = "memberinfo";
	// 积分商城url
	public final static String POINTSHOPURL = "http://www.mobilevip.zhaogang.com";
	// 您有一条新的抢单 = 0,
	// 抢单成功 = 1,
	// 抢单失败 = 2,
	// 结算成功 = 3,
	// 承运商禁用 = 4,
	// 委托方禁用 = 14,
	// 您有一条新的委托已成立，请确认。 = 11
	// 您有一条订单已送达= 12
	public final static String NEWORDER = "0";
	public final static String ORDERSUCCESS = "1";
	public final static String ORDERFAILD = "2";
	public final static String CLEARINGSUCCESS = "3";
	public final static String CARRIESDISABLED = "4";
	public final static String ENTRUSTDISABLED = "14";
	public final static String NEWENTRUSTSUCCESS = "11";
	public final static String NEWORDERARRIVER = "12";

	// 待确认
	public final static int TYPE_DAIQUEREN = 1;
	// 全部
	public final static int TYPE_TETAL = 2;
	// 数据类型
	public static int TYPE = 1;
	// 待受理
	public final static int TYPE_DAISHOULI = 3;
	// 正待受理
	public final static int TYPE_SHOULIING = 4;
}
