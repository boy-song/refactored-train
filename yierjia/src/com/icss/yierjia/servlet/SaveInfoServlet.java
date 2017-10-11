package com.icss.yierjia.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.yierjia.biz.UserBiz;
import com.icss.yierjia.entity.User;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class SavaInfoServlet
 */
public class SaveInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������ʲô·����
		String dir ="E:/eclipse/JavaEE/yierjia/userhead";
		//ÿ���ļ����5m,���1���ļ�
		int maxPostSize =1 * 5 * 1024 * 1024 ;
		//response�ı���Ϊ"utf-8",ͬʱ����ȱʡ���ļ�����ͻ�������,ʵ���ϴ�
		MultipartRequest multi =new MultipartRequest(request, dir, maxPostSize,"utf-8");
		//��ȡ���е���������
		String uid=multi.getParameter("uid");
		String urealname=multi.getParameter("realname");
		String uphone=multi.getParameter("phone");
		String uemail=multi.getParameter("email");
		//���������Ϣ
		Enumeration files = multi.getFileNames();
		String fileName=null;
		UserBiz ub=new UserBiz();
		while (files.hasMoreElements()) {
			String name = (String)files.nextElement();
			File fileUpload = multi.getFile(name);
			if(fileUpload!=null){
				fileName = multi.getFilesystemName(name);
				//ԭ�ļ���
				String imgPath=fileUpload.getName();
				//�и��׺��
				int idx = imgPath.lastIndexOf(".");  
                String extention= imgPath.substring(idx);
                String newImgPath = "head"+uid+extention;
                try {
					ub.saveUserImg(Integer.parseInt(uid), newImgPath);
					File f=new File(dir+"/"+newImgPath);
	                fileUpload.renameTo(f);
				} catch (Exception e) {
					e.printStackTrace();
				}
                
			}
		}
		
		try {
			ub.saveUserInfo(Integer.parseInt(uid), urealname, uphone, uemail);
			//�������ûỰ����
			User user = ub.findByUid(Integer.parseInt(uid));
			request.getSession().setAttribute("user", user);
			//�����
			request.setAttribute("msg", "����ɹ�");
			request.getRequestDispatcher("/person/personal.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
