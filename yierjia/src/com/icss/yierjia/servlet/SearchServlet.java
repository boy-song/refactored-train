package com.icss.yierjia.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.yierjia.biz.ItemBiz;
import com.icss.yierjia.biz.ShopBiz;
import com.icss.yierjia.entity.Item;
import com.icss.yierjia.entity.Shop;
import com.icss.yierjia.util.PageResult;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option=request.getParameter("option");
		String keyWords=request.getParameter("keyWords");
		String currentPage=request.getParameter("currentPage");
		
		if (currentPage==null&&!"".equals(currentPage)) {
			currentPage="1";
		}
		try {
			if (option.equals("shop")) {
				PageResult<Shop> pr=new PageResult<Shop>();//������ҳ�������
				pr.setCurrentPage(Integer.parseInt(currentPage));//���ö���ĵ�ǰҳֵ
				ShopBiz sb=new ShopBiz();//����Biz�����
				sb.setPageResult(keyWords, pr);//�������÷�ҳ������Եķ���
				request.setAttribute("keyWords", keyWords);
				request.setAttribute("page", pr);//���ͷ�ҳ�������
				request.getRequestDispatcher("/shops.jsp").forward(request, response);//��ת
			}else if (option.equals("item")) {
				PageResult<Item> pr=new PageResult<Item>();//������ҳ�������
				pr.setCurrentPage(Integer.parseInt(currentPage));//���ö���ĵ�ǰҳֵ
				ItemBiz ib=new ItemBiz();//����Biz�����
				ib.setPageResult(keyWords, pr);//�������÷�ҳ������Եķ���
				request.setAttribute("keyWords", keyWords);
				request.setAttribute("page", pr);//���ͷ�ҳ�������
				request.getRequestDispatcher("/items.jsp").forward(request, response);//��ת
			}
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
