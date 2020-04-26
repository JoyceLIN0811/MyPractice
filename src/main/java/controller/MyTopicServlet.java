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

@WebServlet("/_04_topic/mytopic")
public class MyTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageNo = 1;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession(false);
		
		if (session == null) {
			req.getRequestDispatcher("/_02_login/login.jsp").forward(req, resp);
			return;
		}
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
			resp.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
		
		String idstr = req.getParameter("topicid");
		System.out.println(req.getParameter("topicid"));

		int topicid = 0 ;

		try{
			// 進行資料型態的轉換
			topicid = Integer.parseInt(idstr.trim());
		} catch(NumberFormatException e){
			throw new ServletException(e); 
		}

		TopicDaoImpl_Jdbc tpl = new TopicDaoImpl_Jdbc();

		TopicBean pagecontent = tpl.getTopic(topicid);
		System.out.println(pagecontent);
		req.setAttribute("mytopic_content", pagecontent);
		

		RequestDispatcher rd = req.getRequestDispatcher("/_03_addtopics/update_delete_topic.jsp");
		rd.forward(req, resp);
		return;


	}

}