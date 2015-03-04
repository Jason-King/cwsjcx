package com.cwsjcx.util;

/**
 * Created by HUANG on 14-7-4.
 * 常用的一些变量
 */
public class Constant {
    /**
     * ************************************************************************
     */
	public static boolean isTestServer = false;
	public static String SERVER_ADDRESS = "http://localhost:8080/jcc/";
	public static boolean inited = false;
	public static final String PRE_PURCHASE_RESULT_PROCESSING = "{\"dt_order\":\"20141201074856\",\"money_order\":\"1000\",\"no_order\":\"127210\",\"oid_partner\":\"201408061000001533\",\"oid_paybill\":\"2014120195780744\",\"result_pay\":\"PROCESSING\",\"ret_code\":\"2004\",\"ret_msg\":\"签约处理中\",\"sign\":\"0e878d7dce8aea7c205c1c08e277a252\",\"sign_type\":\"MD5\"}";
	public static final String PRE_PURCHASE_RESULT_OK = "{\"dt_order\":\"20141201074910\",\"money_order\":\"1000\",\"no_order\":\"127211\",\"oid_partner\":\"201408061000001533\",\"oid_paybill\":\"2014120195780787\",\"ret_code\":\"0000\",\"ret_msg\":\"交易成功\",\"sign\":\"df3bb6e421c69e02d2bd26b1901b863b\",\"sign_type\":\"MD5\",\"sms_flag\":\"1\",\"token\":\"4EB6BF3249ECC71DA5ABE2349B5C1C37\"}";
	public static final String PURCHASE_RESULT_OK = "{\"dt_order\":\"20141201074910\",\"money_order\":\"1000\",\"no_agree\":\"2014120122803255\",\"no_order\":\"127211\",\"oid_partner\":\"201408061000001533\",\"oid_paybill\":\"2014120195780787\",\"result_pay\":\"SUCCESS\",\"ret_code\":\"0000\",\"ret_msg\":\"交易成功\",\"settle_date\":\"20141201\",\"sign\":\"c4ccc1f2ff78f274af74141edd2a1681\",\"sign_type\":\"MD5\"}";
	public static final String PURCHASE_RESULT_PROCESSING = "{\"dt_order\":\"20141201074910\",\"money_order\":\"1000\",\"no_agree\":\"2014120122803255\",\"no_order\":\"127211\",\"oid_partner\":\"201408061000001533\",\"oid_paybill\":\"2014120195780787\",\"result_pay\":\"PROCESSING\",\"ret_code\":\"2008\",\"ret_msg\":\"PROESS\",\"settle_date\":\"20141201\",\"sign\":\"c4ccc1f2ff78f274af74141edd2a1681\",\"sign_type\":\"MD5\"}";
	public static final String PRE_BIND_OK = "{\"oid_partner\":\"201408061000001533\",\"ret_code\":\"0000\",\"ret_msg\":\"交易成功\",\"sign\":\"5f8320de7910d43532a82f56fec5bdc8\",\"sign_type\":\"MD5\",\"token\":\"674BF27DED88D3C1EA757B6537A14ED6\",\"user_id\":\"168771\"}";
	public static final String BIND_OK = "{\"card_no\":\"6212261608001833523\",\"no_agree\":\"2014120623375729\",\"oid_partner\":\"201408061000001533\",\"ret_code\":\"0000\",\"ret_msg\":\"交易成功\",\"sign\":\"47ddbc8eb1fa4ce9ebc7402988c14223\",\"sign_type\":\"MD5\",\"user_id\":\"169004\"}";
	public static final String UNBIND_OK = "{\"ret_msg\":\"交易成功\",\"sign\":\"e5ee85a902c77b049c7112fdaf57e76e\",\"ret_code\":\"0000\",\"sign_type\":\"MD5\"}";
    private static final String SMS_BASE_URL = "http://sdk2.entinfo.cn:8061/mdsmssend.ashx";
    private static final String SMS_QUERY_BASIC_URL = SMS_BASE_URL + "?sn=SDK-WSS-010-06746&pwd=1D79C7157ACE76E39754C1BC6C3E4D4E&";
    private static final String SMS_REGISTER_CONTENT_TEXT = "欢迎注册聚财猫，您本次的注册验证码为：%s。该验证码30分钟内有效，60秒后可重发。【聚财猫】";
    private static final String SMS_RESET_PWD_CONTENT_TEXT = "您本次的重置验证码为：%s。该验证码30分钟内有效，60秒后可重发。【聚财猫】";
    private static final String SMS_LOTTERY_CONTENT_TEXT = "欢迎注册聚财猫。您本次的抽奖码为：%s。抽奖活动详情请关注微信订阅号：jucaicat。【聚财猫】";
    private static final int PAGE_SIZE = 20;
    private static final int PANEL_LIST_SIZE = 5;
    private static final String SMS_SELLOUT_CONTENT_TEXT = "已售罄，请及时补充！【聚财猫】";
    private static final String PRODUCT_DETAIL_IMAGE_BASE_URL = "";
    private static final String[] SELLOUT_SEND_NUMBER = new String[]{"13738089662", "17721016658","13291898855","18001950989"};
    private static final String[] SERVICE_MONITOR_SEND_NUMBER = new String[]{"13738089662", "17721016658","18017769234","13611629512","13291898855"};
    private static final String[] SERVICE_51_MONITOR_NUMBER = new String[]{"15190662262","13611629512"};
    public static final String LOTTERY_BEGIN_TIME = "20141110000000";
    public static final String LOTTERY_END_TIME = "20141114000000";
    public static final String UMENG_PUSH_URL = "http://msg.umeng.com/api/send";
    public static final String ANDROID_APPKEY = "53e8a372fd98c5156002c830";
    public static final String ANDROID_APP_MASTER_SECRET ="gey14tkdcopomrvm5ndpjkjusfqtiivl";
    public static final String IOS_APPKEY = "53e8a372fd98c5156002c830";
    public static final String IOS_APP_MASTER_SECRET ="gey14tkdcopomrvm5ndpjkjusfqtiivl";
    public static final String ACTIVITY_URL = "http://www.jucaicat.com/jcc_act/lottery/activity-turntable2/01activity-records.jsp?tag=need_login";;
    private static final int WEB_PAGE_SIZE = 15;
    public static final String PRE_PURCHASE_JSON = "PRE_PURCHASE_JSON";
    public static final String PRE_BIND_CARD_JSON = "PRE_BIND_CARD_JSON";
    public static final String DUO_MENG_URL = "http://e.domob.cn/track/ow/api/postback";
    public static final String IOS_APP_ID = "916475073";
    public static final String DUO_MENG_KEY = "5c6942596c0140a208b9a8e711ee9ad2";
    public static int getWebPageSize() {
		return WEB_PAGE_SIZE;
	}
    private static final String[] BANKS = new String[]{
            "农业银行",
            "工商银行",
            "招商银行",
            "中国银行",
            "光大银行",
            "华夏银行",
            "平安银行",
            "建设银行",
            "浦发银行", //8
            "未知银行", //unknown
            "农业银行", //same as 0
            "兴业银行", //11
            "中信银行" //12
    };


    public static String getSmsRegisterContentText() {
        return SMS_REGISTER_CONTENT_TEXT;
    }

    public static String getSmsQueryBasicUrl() {
        return SMS_QUERY_BASIC_URL;
    }

    public static int getPageSize() {
        return PAGE_SIZE;
    }

    public static int getPanelListSize() {
        return PANEL_LIST_SIZE;
    }

    public static String getSmsResetPwdContentText() {
        return SMS_RESET_PWD_CONTENT_TEXT;
    }

    public static String getProductDetailImageBaseUrl() {
        return PRODUCT_DETAIL_IMAGE_BASE_URL;
    }

    public static String getSmsLotteryContentText() {
        return SMS_LOTTERY_CONTENT_TEXT;
    }

    public static String getSmsSelloutContentText() {
        return SMS_SELLOUT_CONTENT_TEXT;
    }

    public static String[] getSelloutSendNumber() {
        return SELLOUT_SEND_NUMBER;
    }

    public static String[] getBanks() {
        return BANKS;
    }

	public static String[] getServiceMonitorSendNumber() {
		return SERVICE_MONITOR_SEND_NUMBER;
	}

	public static String[] getService51MonitorNumber() {
		return SERVICE_51_MONITOR_NUMBER;
	}
	
}
