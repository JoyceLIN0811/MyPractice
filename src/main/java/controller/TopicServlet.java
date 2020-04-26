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

@WebServlet("/_04_topic/topic")
public class TopicServlet extends HttpServlet {
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
		
//		TopicBean tb = null;
//		 session.setAttribute("TopicBean",tb);

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
		req.setAttribute("topic_content", pagecontent);
		
		ReplyDaoImpl_Jdbc tpl2 = new ReplyDaoImpl_Jdbc();
		System.out.println(topicid);
		
		long rnum = tpl2.getRecordCounts(topicid);
		System.out.println(rnum);
		req.setAttribute("ReplyTotalNum", rnum);
		
		List<ReplyBean> rpage = tpl2.getAllreply(topicid);
		System.out.println(rpage);
		req.setAttribute("reply_content", rpage);
		

		RequestDispatcher rd = req.getRequestDispatcher("topic.jsp");
		rd.forward(req, resp);
		return;


	}

}