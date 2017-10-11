package com.icss.yierjia.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.yierjia.biz.OrderBiz;
import com.icss.yierjia.biz.UserBiz;
import com.icss.yierjia.dao.OrderDao;
import com.icss.yierjia.entity.Order;
import com.icss.yierjia.util.ExportExcel;


/**
 * Servlet implementation class ExcelServlet
 */
public class ExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String docsPath = request.getSession().getServletContext().getRealPath("docs");
		ExportExcel ex = new ExportExcel();
		OrderDao dao = new OrderDao();
		try {
			List<Order> list = dao.selectAllOrders(0,-1);
			String[] headers = {"������","�̼�id","�û�id","�µ�ʱ��","�˷�","�����۸�","�ռ���","�ֻ���","�ռ���ַ","����״̬","ȡ��ʱ��","ȡ��ԭ��"};
			ex.export(docsPath, headers, list,"������");
			String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
			String fileName = "ExportData.xls";
			String filePath = docsPath + FILE_SEPARATOR + fileName;
			download(filePath, response);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	private void download(String path, HttpServletResponse response) {
		try {
			// path��ָ�����ص��ļ���·����
			File file = new File(path);
			// ȡ���ļ�����
			String filename = file.getName();
			// ��������ʽ�����ļ���
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// ���response
			response.reset();
			// ����response��Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes(),"ISO-8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=gb2312");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
