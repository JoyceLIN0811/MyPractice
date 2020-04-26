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

import dao.impl.TopicDaoImpl_Jdbc;
import model.MemberBean;
import model.TopicBean;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/_03_addtopics/addtopics.do")
public class addtopicsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	public static Long getCurrentTime() {
//		double doubleTime = (Math.floor(System.currentTimeMillis()/60000L));
//		long floorValue = new Double(doubleTime).longValue();
//		return floorValue*60;
//	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		HttpSession session = req.getSession(false);
		
		System.out.println("Hello world !!!!!!!");
		
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
			resp.sendRedirect(getServletContext().getContextPath() + "/index.jsp"  );
			return;
		}
//		MemberBean mb = new MemberBean();
		

		String username = mb.getUsername();
//		String username = req.getParameter("username");
		String idstr = req.getParameter("categoryid");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
//		long posttime = System.currentTimeMillis();
		Timestamp posttime = new java.sql.Timestamp(System.currentTimeMillis());
		String pageNo = req.getParameter("pageNo");
		if (pageNo == null || pageNo.trim().length() == 0){
			pageNo = (String) session.getAttribute("pageNo") ;
			if (pageNo == null){
			   pageNo = "1";
			} 
		} 
		int categoryid = 0 ;

		try{
			// 進行資料型態的轉換
			categoryid = Integer.parseInt(idstr.trim());
		} catch(NumberFormatException e){
			throw new ServletException(e); 
		}
		
		System.out.println(Integer.parseInt(idstr.trim()));
		System.out.println(req.getParameter("title"));
		System.out.println(req.getParameter("content"));
		System.out.println(mb.getUsername());
		System.out.println(posttime);

		
		TopicBean tbn = new TopicBean();
		TopicDaoImpl_Jdbc tpl = new TopicDaoImpl_Jdbc();

		tbn.setUsername(username);
		tbn.setCategoryid(categoryid);
		tbn.setTitle(title);
		tbn.setContent(content);
		tbn.setPosttime(posttime);
		tpl.saveTopic(tbn);
		
		
		List<TopicBean> page = tpl.getAlltopic();
		System.out.println( page);
		req.setAttribute("title_list", page);
		
		long num = tpl.getRecordCounts();
		System.out.println( num);
		req.setAttribute("TopicsTotalNum", num);
		req.getRequestDispatcher("/_04_topic/topiclist").forward(req, resp);

		
//		RequestDispatcher rd = req.getRequestDispatcher("/_04_topic/topiclist.jsp");
//		rd.forward(req, resp);
//		req.getRequestDispatcher("/fragment/top.jsp").forward(req, resp);

	}

}