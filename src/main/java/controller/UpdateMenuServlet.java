//package controller;
//
//import java.io.IOException;
//import java.sql.Timestamp;
//import java.util.Collection;
//import java.util.List;
//
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//
//import model.*;
//import service.*;
//import service.impl.*;
//import dao.impl.*;
//
//@WebServlet("/fragment/top.jsp")
//public class UpdateMenuServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	int pageNo = 1;
//
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		doPost(req, resp);
//	}
//
//	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
//		HttpSession session = req.getSession(false);
//		
//		if(session == null) {
////			resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() +"/_02_login/login.jsp"));
//			req.getRequestDispatcher("/_02_login/login.jsp").forward(req, resp);
//			return;
//		}
//		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
//		if (mb == null) {
//			resp.sendRedirect(getServletContext().getContextPath() + "/index.jsp"  );
//			return;
//		}
//				
//		TopicBean tbn = new TopicBean();
//		TopicDaoImpl_Jdbc tpl = new TopicDaoImpl_Jdbc();
//
//
//		List<TopicBean> page = tpl.getAlltopic();
//		System.out.println( page);
//		req.setAttribute("title_list", page);
//		
//		RequestDispatcher rd = req.getRequestDispatcher("topiclist.jsp");
//		rd.forward(req, resp);
//
//		
//
//	}
//	
//}