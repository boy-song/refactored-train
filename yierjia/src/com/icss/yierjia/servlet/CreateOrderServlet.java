package com.icss.yierjia.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.yierjia.biz.OrderBiz;
import com.icss.yierjia.entity.User;

/**
 * Servlet implementation class CreateOrderServlet
 */
public class CreateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡҳ�����
		String[] idArr=request.getParameterValues("idArr");
		String[] countArr=request.getParameterValues("countArr");
		String address=request.getParameter("address");
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		//��ȡ�û�id
		User user=(User)request.getSession().getAttribute("user");
		int uid=user.getUid();
		//��ȡ����ʱ�䲢���и�ʽת��
		Date odate=new Date();
		Timestamp tt=new Timestamp(odate.getTime());
		//��������
		OrderBiz ob=new OrderBiz();
		try {
			ob.createOrder(idArr, countArr, uid, tt, address, name, phone);
			request.getRequestDispatcher("/person/order_list.jsp").forward(request, response);
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
