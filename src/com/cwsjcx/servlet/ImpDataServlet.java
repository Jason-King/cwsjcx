package com.cwsjcx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import com.cwsjcx.util.DatabaseUtility;
import com.cwsjcx.util.ExcelReader;

@WebServlet(urlPatterns = { "/ImpData" })
public class ImpDataServlet extends HttpServlet {
	private static final long serialVersionUID = -8608229251804310705L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		// 上传数据
		Connection connection = null;
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;

		// 这里对request进行封装，RequestContext提供了对request多个访问方法
		RequestContext requestContext = new ServletRequestContext(request);
		String month = request.getParameter("month");
		String type = request.getParameter("type");
		// 判断表单是否是Multipart类型的。这里可以直接对request进行判断，不过已经以前的用法了
		if (FileUpload.isMultipartContent(requestContext)) {

			int maxPostSize = 1000 * 1024 * 1024;
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
			servletFileUpload.setSizeMax(maxPostSize);

			try {
				List fileItems = servletFileUpload.parseRequest(request);
				Iterator iter = fileItems.iterator();

				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (!item.isFormField()) {// 是文件

						// 获取文件名称, 以判断后缀是 .xls 还是 .xlsx
						String strName = item.getName();
						String subName = strName.substring(strName
								.lastIndexOf(".") + 1);

						// 通过ExcelReader将表格中的数据转换成Map
						ExcelReader er = new ExcelReader();
						Map<Integer, String> map = er.readExcelContent(
								item.getInputStream(), subName);

						// 将数据插入到表中
						connection = DatabaseUtility.getConnection();
						connection.setAutoCommit(false); // 相当于start transaction
						String delSql = "delete from " + type
								+ " ;";
						pst2 = connection.prepareStatement(delSql);
						pst2.setString(1, month);
						pst2.execute();

						String insSql = "insert into " + type + "(" + "number,"
								+ "name," + "money," + "month," + "status"
								+ " )  " + " values " + " (?,?,?,?,?) ";
						pst = connection.prepareStatement(insSql);

						for (int i = 1; i <= map.size(); i++) {
							// 获取Map的Value
							String data = map.get(i);
							// 转成字符串数组
							String[] dataStr = data.split("    ");

							pst.setString(1, dataStr[0]);
							pst.setString(2, dataStr[1]);
							if (dataStr.length < 3) {
								pst.setString(3, "0");
							} else {
								pst.setString(3, dataStr[2]);
							}
							pst.setString(4, month);
							pst.setInt(5, 1);

							pst.addBatch();
						}

						// 执行批量更新
						pst.executeBatch();

						// 上传成功
						PrintWriter out = response.getWriter();
						out.write("文件上传成功");
						out.flush();
						connection.commit();
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} catch (NamingException e) {
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} finally {
				DatabaseUtility.close(connection, pst, null);
				DatabaseUtility.close(null, pst2, null);
			}

		} else {
			PrintWriter out = response.getWriter();
			out.write("操作失败，上传文件类型非法");
			out.flush();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
