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

import com.google.protobuf.Value;

import dao.impl.TopicDaoImpl_Jdbc;
import model.MemberBean;
import model.TopicBean;
import service.TopicService;
import service.impl.TopicServiceImpl;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/_04_topic/update_delete_topic.do")
public class update_delete_topicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
				
		String username = mb.getUsername();
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String tidstr = req.getParameter("topicid");
		String cidstr = req.getParameter("categoryid");
		String action = req.getParameter("action");
		
		System.out.println(req.getParameter("topicid"));
		System.out.println(req.getParameter("title"));
		System.out.println(req.getParameter("content"));
		System.out.println(req.getParameter("categoryid"));
		System.out.println(req.getParameter("action"));
		System.out.println(mb.getUsername());


		int topicid = 0 ;
		int categoryid = 0 ;

		try{
			// 進行資料型態的轉換
			topicid = Integer.parseInt(tidstr.trim());
			categoryid = Integer.parseInt(cidstr.trim());
		} catch(NumberFormatException e){
			throw new ServletException(e); 
		}
	
		
		TopicDaoImpl_Jdbc tpl = new TopicDaoImpl_Jdbc();
		if(action=="delete") {
		int n = tpl.deleteTopic(topicid);
		if (n == 1) {
			session.setAttribute("BookDeleteMsg", "文章(" + title + ")刪除成功");
		} else {
			session.setAttribute("BookDeleteMsg", "文章(" + title + ")刪除失敗");
		}
		resp.sendRedirect("mytopiclist");
		return;}
		
		TopicBean tbn = new TopicBean();


			tbn.setTopicid(topicid);
			tbn.setCategoryid(categoryid);
			tbn.setTitle(title);
			tbn.setContent(content);
			tpl.updateTopic(tbn); 
	        RequestDispatcher rd = req.getRequestDispatcher("mytopiclist");
		    rd.forward(req, resp);
		    return;
//		}

		
//		tbn.setTopicid(topicid);
//		tbn.setUsername(username);
//		tbn.setCategoryid(categoryid);
//		tbn.setTitle(title);
//		tbn.setContent(content);
//		tpl.saveTopic(tbn);
//		
//		
//		List<TopicBean> page = tpl.getAlltopic();
//		System.out.println( page);
//		req.setAttribute("title_list", page);
//		
//		long num = tpl.getRecordCounts();
//		System.out.println( num);
//		req.setAttribute("TopicsTotalNum", num);
//		req.getRequestDispatcher("/_03_addtopics/update_delete_topic.jsp").forward(req, resp);

		
//		RequestDispatcher rd = req.getRequestDispatcher("/_04_topic/topiclist.jsp");
//		rd.forward(req, resp);
//		req.getRequestDispatcher("/fragment/top.jsp").forward(req, resp);


	}
}