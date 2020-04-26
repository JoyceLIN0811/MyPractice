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


@WebServlet("/_04_topic/topiclist")
public class TopicListServlet extends HttpServlet {
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
		
	
		String cidstr = req.getParameter("categoryid");		
		System.out.println(req.getParameter("categoryid"));
		
	
		//全部文章列
		if (cidstr == null) {
			
			//文章數量
			long num = tpl.getRecordCounts();
			System.out.println(num);
			req.setAttribute("TopicsTotalNum", num);

			//全部
			List<TopicBean> page = tpl.getAlltopic();
			System.out.println(page);
			req.setAttribute("title_list", page);
			
		}else {
			
			int categoryid = 0 ;
			System.out.println( req.getParameter("categoryid"));
			
			try{
				// 進行資料型態的轉換
				categoryid = Integer.parseInt(cidstr.trim());
				
			} catch(NumberFormatException e){
				throw new ServletException(e); 
			}
			
			//分類
			List<TopicBean> cpage = tpl.getCategorytopic(categoryid);
			System.out.println(cpage);
			req.setAttribute("title_list", cpage);
			
			//文章數量
			long cnum = tpl.getCategoryRecordCounts(categoryid);
			System.out.println(cnum);
			req.setAttribute("TopicsTotalNum", cnum);
			
			
			
			
		}
		
		
		
		


		RequestDispatcher rd = req.getRequestDispatcher("topiclist.jsp");
		rd.forward(req, resp);
		return;


	}

}