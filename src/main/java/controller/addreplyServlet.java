package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.impl.*;
import model.*;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/_04_topic/addreply.do")
public class addreplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		HttpSession session = req.getSession(false);
		
		System.out.println("Hello world !!!!!!!");
		
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
//		if (mb == null) {
//			resp.sendRedirect(getServletContext().getContextPath() + "/index.jsp"  );
//			return;
//		}
		
		 TopicBean tb = (TopicBean)session.getAttribute("TopicBean");
			// 如果找不到t物件
			if (tb == null) {
				// 就新建物件
				tb = new  TopicBean();
				// 並將此新建的物件放到session物件內，成為它的屬性物件
				session.setAttribute("TopicBean", tb);   
			}
		
//		TopicBean tb = (TopicBean)session.getAttribute("TopicBean");

		String username = mb.getUsername();
		System.out.println(username);

		String replycontent = req.getParameter("replycontent");
		System.out.println(replycontent);

		Timestamp replytime = new java.sql.Timestamp(System.currentTimeMillis());
		System.out.println(replytime);
		String idstr = req.getParameter("topicid");
//		Integer topicid = tb.getTopicid();
				int topicid = 0 ;

		try{
			// 進行資料型態的轉換
			topicid = Integer.parseInt(idstr.trim());
		} catch(NumberFormatException e){
			e.printStackTrace();
			throw new ServletException(e); 
		}
		System.out.println(topicid);
		System.out.println(username);
		System.out.println(topicid);
		System.out.println(replycontent);
		System.out.println(replytime);

		
		ReplyBean rbn = new ReplyBean();
		ReplyDaoImpl_Jdbc tpl = new ReplyDaoImpl_Jdbc();

		rbn.setUsername(username);
		rbn.setTopicid(topicid);
		rbn.setReplycontent(replycontent);
		rbn.setReplytime(replytime);
		tpl.saveReply(rbn);
		
		
		long rnum = tpl.getRecordCounts(topicid);
		System.out.println(rnum);
		req.setAttribute("ReplyTotalNum", rnum);
		
		req.getRequestDispatcher("/_04_topic/topic").forward(req, resp);

	}

}