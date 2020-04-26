package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.*;
import service.*;
import service.impl.*;
import dao.impl.*;
import dao.*;


@WebServlet("/_04_topic/mytopiclist")
public class MyTopicListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageNo = 1;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession(false);

		if (session == null) {
//			resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() +"/_02_login/login.jsp"));
			req.getRequestDispatcher("/_02_login/login.jsp").forward(req, resp);
			return;
		}
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
			resp.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
	
		TopicDaoImpl_Jdbc tpl = new TopicDaoImpl_Jdbc();
		
	
		Integer memberid = mb.getMemberid(); 
		
		System.out.println(req.getParameter("memberid"));
		

		
		//個人文章列
		
		//文章數量
		long mynum = tpl.getMyRecordCounts(memberid);
		System.out.println(mynum);
		req.setAttribute("MyTopicsTotalNum", mynum);

		//全部
		List<TopicBean> mpage = tpl.getMytopic(memberid);
		System.out.println(mpage);
		req.setAttribute("mytitle_list", mpage);
		
		


		RequestDispatcher rd = req.getRequestDispatcher("mytopiclist.jsp");
		rd.forward(req, resp);
		return;


	}

}