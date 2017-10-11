package com.icss.yierjia.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.yierjia.biz.ShopBiz;
import com.icss.yierjia.entity.Shop;
import com.icss.yierjia.util.ExportExcel;

/**
 * Servlet implementation class ExportShopsExcel
 */
public class ExportShopsExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportShopsExcel() {
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
		ShopBiz biz = new ShopBiz();
		try {
			//��ȡ�û��б�
			List<Shop> list = biz.findShopsByState(state);
			String[] headers = {"����id","��������","�û���","����","�ֻ���","����","����","��ַ","״̬","ͼƬ"};
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
