package com.icss.yierjia.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.yierjia.biz.UserBiz;
import com.icss.yierjia.dao.OrderDao;
import com.icss.yierjia.entity.Order;
import com.icss.yierjia.entity.User;
import com.icss.yierjia.util.ExportExcel;

/**
 * Servlet implementation class ExportUsersExcel
 */
public class ExportUsersExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportUsersExcel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�ĵ�·��
		String docsPath = request.getSession().getServletContext().getRealPath("docs");
		//��ȡ����
		String state = request.getParameter("state");
		//������������
		ExportExcel ex = new ExportExcel();
		UserBiz biz = new UserBiz();
		try {
			//��ȡ�û��б�
			List<User> list = biz.findUsersByState(state);
			String[] headers = {"�û�id","�û���","ͷ��","��ʵ����","����","�ֻ���","����","��ַ","֧������","���","�û�״̬","�û���ɫ","��֤��"};
			ex.export(docsPath, headers, list,"�û���");
			String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
			String fileName = "ExportData.xls";
			String filePath = docsPath + FILE_SEPARATOR + fileName;
			//����
			ex.download(filePath, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
