package com.icss.yierjia.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.icss.yierjia.biz.ItemBiz;
import com.icss.yierjia.entity.Item;
import com.icss.yierjia.util.PageResult;

/**
 * Servlet implementation class AjaxPageItemsBySid
 */
public class AjaxPageItemsBySid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxPageItemsBySid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ղ������̼�id����ǰҳ�롣
		String sid = request.getParameter("sid");
		String currentPage = request.getParameter("currentPage");
		if (sid != null && currentPage != null && !sid.equals("") && !currentPage.equals("")) {
			PageResult<Item> page = new PageResult<Item>();
			//���õ�ǰҳ��
			page.setCurrentPage(Integer.parseInt(currentPage));
			ItemBiz biz = new ItemBiz();
			try {
				//����page����
				biz.setPageResultBySid(Integer.parseInt(sid), page);
				//����json����
				JSONObject json = new JSONObject();
				json = JSONObject.fromObject(page);
				//System.out.println(json.toString());
				PrintWriter out = response.getWriter();
				out.print(json.toString());
			} catch (Exception e) {
				request.setAttribute("msg", e.getMessage());
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
