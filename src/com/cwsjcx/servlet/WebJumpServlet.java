/**   
 * 文件名：WebJumpServlet.java   
 *   
 * 版本信息：   
 * 日期：2015年2月28日   
 * Copyright  Corporation 2015    
 * 版权所有   
 *   
 */

package com.cwsjcx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.cwsjcx.util.DatabaseUtility;

/**
 * 此类描述的是：
 * 
 * @author: HuaWuQue
 * @version: 2015年2月28日 上午11:49:11
 */
@WebServlet(urlPatterns = { "/WebJump" })
public class WebJumpServlet extends HttpServlet {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 * 
	 * @since Ver 1.2
	 */

	private static final long serialVersionUID = -8608229251804310705L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		Calendar calEnviron = Calendar.getInstance();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = new StringBuilder("select id, number, name,money,month from ");
		
		String page = request.getParameter("page");
		if(null!=page && !"".equals(page.trim())){
			page = page.trim();
		}else{
			page = "should_receive ";
		}
		sql.append(page);
		sql.append(" where 1=1 ");
		String companyName = request.getParameter("company_name");
		if(null!=companyName && !"".equals(companyName.trim())){
			companyName = companyName.trim();
			sql.append(" and name like '%"+companyName+"%' ");
			
		}else{
			companyName = "";
		}
		
		/*String month = request.getParameter("month");
		if(null!=month && !"".equals(month.trim())){
			month = month.trim();
			
		}else if(null==companyName || "".equals(companyName.trim())){
			month = calEnviron.get(Calendar.YEAR)+"-";
			month += "0"+calEnviron.get(Calendar.MONTH);
		}
		sql.append(" and month = '"+month+"' ");*/
		System.out.println(sql);
		List list = null;
		try {
			connection= DatabaseUtility.getConnection();
			preparedStatement = connection.prepareStatement(sql.toString());
			resultSet = preparedStatement.executeQuery();
			
			list = new ArrayList();
			while(resultSet.next()){
				Map map = new HashMap();
				map.put("id", resultSet.getString(1));
				map.put("number", resultSet.getString(2));
				map.put("name", resultSet.getString(3));
				map.put("money", resultSet.getString(4));
				map.put("month", resultSet.getString(5));
				list.add(map);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DatabaseUtility.close(connection, preparedStatement, resultSet);
		}
		
		JSONObject resultJSONMap = new JSONObject();//转换成JSON格式输出
        resultJSONMap.put("list", list);
        out.write(resultJSONMap.toString());
	}
}
