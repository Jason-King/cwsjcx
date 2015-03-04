package com.cwsjcx.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

public class Util {
	public static void genResponse(HttpServletResponse response, String result) {
		// response.setContentType("text/html;charset=gb2312");
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(result);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	static public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		return ip;
	}

	private static char[] BASE64 = "abcdefghijklmnopqrstuvwxyz_ABCDEFGHIJKLMNOPQRSTUVWXYZ-0123456789"
			.toCharArray();

	public static String generateSUUID() {
		UUID uuid = UUID.randomUUID();
		char[] chs = new char[22];
		long most = uuid.getMostSignificantBits();
		long least = uuid.getLeastSignificantBits();
		int high = (int) ((most >> 13) ^ (least >> 31)) & 0x3c;
		int k = chs.length - 1;
		for (int i = 0; i < 10; i++, least >>>= 6) {
			chs[k--] = BASE64[(int) (least & 0x3f)];
		}
		chs[k--] = BASE64[(int) ((least & 0x3f) | (most & 0x30))];
		most >>>= 2;
		for (int i = 0; i < 10; i++, most >>>= 6) {
			chs[k--] = BASE64[(int) (most & 0x3f)];
		}
		chs[k--] = BASE64[(int) (high | most)];
		return new String(chs);
	}

	// public static String getOutTradeNo(String suuid) {
	// String strNo = suuid;
	// SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
	// Date date = new Date();
	// strNo = strNo + format.format(date);
	//
	// java.util.Random r = new java.util.Random();
	// strNo = strNo + r.nextInt();
	// strNo = strNo.substring(0, 39);
	// return strNo;
	// }

	public static long DateCompare2Now(String earlyDay) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = new Date(System.currentTimeMillis());
		Date d2;
		long iDay = 0;
		try {
			// System.out.println("earlyDay:"+earlyDay+", d1:"+d1.toString()+", "+d1.toGMTString()+","+d1.toLocaleString());
			d2 = sdf.parse(earlyDay);
			iDay = (d1.getTime() - d2.getTime());
			// System.out.println("iDay:"+iDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return iDay;
	}

	public static long DateCompare2Now(Date d2) {
		Date d1 = new Date(System.currentTimeMillis());
		long iDay = 0;
		try {
			// System.out.println("earlyDay:"+earlyDay+", d1:"+d1.toString()+", "+d1.toGMTString()+","+d1.toLocaleString());
			iDay = (d1.getTime() - d2.getTime());
			// System.out.println("iDay:"+iDay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iDay;
	}

	public static void setOutput(HttpServletResponse response, String strResult) {
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(strResult);
			out.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 此方法用于刷新并且向页面传送json字符
	 * 
	 * @param jsonData
	 * @param response
	 */
	public static void sendJsonDataToClient(String jsonData,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		
		try {

			response.getWriter().write(jsonData);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取默认格式JSON字符串
	 * 
	 * @param flag
	 *            操作是否成功
	 * @param msg
	 *            提示信息
	 * @return
	 */
	public static String getDefJsonString(boolean flag, String msg) {
		return "{\"flag\":" + flag + ",\"msg\":\"" + msg + "\"}";
	}

	public static void printOperationLog(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		String isLogin = (String) session.getAttribute("ADMIN_IS_LOGIN");
		String strName = (String) session.getAttribute("ADMIN_NAME");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date(System.currentTimeMillis());
		String strTime = sdf.format(d1);

		String paras = "";
		Enumeration test = request.getParameterNames();
		while (test.hasMoreElements()) {
			String paraName = (String) test.nextElement();
			paras += paraName + "=" + request.getParameter(paraName) + " ";
		}

		System.out.println(strTime + "\t" + strName + "\t" + getIpAddr(request)
				+ "\t" + request.getRequestURI() + "\t" + paras);

	}

	public static String printJccLog(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd HHmmss");
		Date d1 = new Date(System.currentTimeMillis());
		String strTime = sdf.format(d1);

		String paras = "";
		Enumeration test = request.getParameterNames();
		while (test.hasMoreElements()) {
			String paraName = (String) test.nextElement();
			paras += paraName + "=" + request.getParameter(paraName) + ",";
		}

		System.out.println(strTime + "\t" /* + getIpAddr(request) */+ "\t"
				+ request.getRequestURI() + "\t" + paras);
		return paras;

	}

	public static String getJccLog(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd HH:mm:ss");
		Date d1 = new Date(System.currentTimeMillis());
		String strTime = sdf.format(d1);

		String paras = "";
		Enumeration test = request.getParameterNames();
		while (test.hasMoreElements()) {
			String paraName = (String) test.nextElement();
			paras += paraName + "=" + request.getParameter(paraName) + ",";
		}

		return (strTime + "\t" /* + getIpAddr(request) */+ "\t"
				+ request.getRequestURI() + "\t" + paras);

	}

	public static String getCurrentDatetime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd HH:mm:ss");
		Date d1 = new Date(System.currentTimeMillis());
		String strTime = sdf.format(d1);
		return strTime;
	}
	
	public static String getFullCurrentDatetime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = new Date(System.currentTimeMillis());
		String strTime = sdf.format(d1);
		return strTime;
	}
	/**
	 * 获取当前的年月日，格式为yyyyMMdd
	 * */
	public static String getYMD(){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(d);
	}
	public static void d(String info){
		//System.out.println(info);
	}

	public static void p(String info){
		//System.out.println(info);
	}
	/**
	 * double型转百分比，保留2为小数
	 * */
	public static String format(double number){
		NumberFormat nFromat = NumberFormat.getPercentInstance();
		nFromat.setMinimumFractionDigits(2);//设置保留小数位
		nFromat.setRoundingMode(RoundingMode.HALF_UP);
		return nFromat.format(number);
	}
	/**
	 * 校验当前服务是生产服务还是测试服务
	 * */
	public static boolean checkServiceIpAddr(HttpServletRequest request){
		boolean flag = false;
		System.out.println("当前服务器IP："+request.getLocalName());
		if(//"121.41.34.211".equals(request.getLocalName()) || 
			"115.29.176.251".equals(request.getLocalName())||
			"121.41.21.6".equals(request.getLocalName())
			){
			flag = true;
		}
		return flag;
	}
	
	public static JSONObject transferUserHint(JSONObject jsonObject) {
		// 修改错误代码为1108，错误信息为‘您输入的证件号、姓名或手机号有误’的错误提示信息
		if ("1108".equals(jsonObject.optString("ret_code")))
			jsonObject.put("ret_msg", "亲，您输入的信息有误或银行卡未开通电子/无卡支付，请联系聚财猫客服处理");
		else if ("1004".equals(jsonObject.optString("ret_code"))) {
			String oriMsg = jsonObject.optString("ret_msg");
			jsonObject.put("ret_msg", oriMsg + " 请检查您的身份证号和银行卡号");
		} else if ("5001".equals(jsonObject.optString("ret_code"))) {
			jsonObject.put("ret_msg", "银行卡号错误");
		}
		return jsonObject;
	}
	/**
	 * 获取当前的年月日，格式为dd
	 * */
	public static String getDay(){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		return sdf.format(d);
	}
	public static String formatMoney(double money){
		DecimalFormat fmt = new DecimalFormat("##,###,###,###,###");
		return fmt.format(money);
	}
	/**
	 * 判断是否为数字格式的字符串
	 * */
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches(); 
	}

	public static float checkIdCardAge(String idCard) {
		if (null != idCard && (idCard.length() == 15 || idCard.length() == 18)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MMdd");
			Date d1 = new Date(System.currentTimeMillis());
			String strTime1 = sdf.format(d1);

			String strTime2;
			if (idCard.length() == 18) {
				String strTime21 = idCard.substring(6, 10);
				String strTime22 = idCard.substring(10, 14);
				strTime2 = strTime21 + '.' + strTime22;
			} else {
				String strTime21 = idCard.substring(6, 8);
				String strTime22 = idCard.substring(8, 12);
				strTime2 = "19" + strTime21 + '.' + strTime22;
			}

			// System.out.println("strTime1:" + strTime1 + ", strTime2:"
			// + strTime2);
			float t1 = Float.parseFloat(strTime1);
			float t2 = Float.parseFloat(strTime2);
			return (t1 - t2);

		} else {
			return 0;
		}
	}
	
	public static void initCallbackAndTestServer(HttpServletRequest request) {
		if (false == Constant.inited) {
			String serverName = request.getServerName();
			String cbAddress = "http://" + serverName + ":"
					+ request.getServerPort() + request.getContextPath() + "/";
			// System.out.println("initCallbackAndTestServer, set SERVER_ADDRESS to "
			// + cbAddress);
			// Constant.SERVER_ADDRESS = cbAddress;
			if (serverName.equals("test.jucaicat.com")) {
				System.out
						.println("initCallbackAndTestServer, Constant.SERVER_ADDRESS is test server");
				Constant.isTestServer = true;
				Constant.SERVER_ADDRESS = cbAddress;
			}
			Constant.inited = true;

//			System.out.println("1:" + request.getRequestURI() + ", "
//			+ request.getRequestURL() + ", " + request.getServletPath()
//			+ ", " + request.getServerName() + ", "
//			+ request.getServerPort() + ", " + request.getLocalPort()
//			+ "," + request.getContextPath());
//	1:/jcc/ProductHomePage, http://localhost:8080/jcc/ProductHomePage, /ProductHomePage, localhost, 8080, 8080,/jcc
//  1:/jcc/ProductHomePage, http://127.0.0.1:8080/jcc/ProductHomePage, /ProductHomePage, 127.0.0.1, 8080, 8080,/jcc
//  1:/jcc/ProductHomePage, http://192.168.3.120:8080/jcc/ProductHomePage, /ProductHomePage, 192.168.3.120, 8080, 8080,/jcc
		}

	}

}
