package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import _00_init.util.GlobalService;
import _00_init.util.SystemUtils2018;
import service.*;
import service.impl.*;
import dao.impl.MemberDaoImpl_Jdbc;
import model.*;
import service.*;
import service.impl.*;


@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/_01_register/register.do")
public class RegisterServletMP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		Map<String, String> errorMsg = new HashMap<String, String>();
		Map<String, String> msgOK = new HashMap<String, String>();

		HttpSession session = req.getSession();
		req.setAttribute("MsgMap", errorMsg); 
		session.setAttribute("MsgOK", msgOK); 
		
		MemberDaoImpl_Jdbc mbpl = new MemberDaoImpl_Jdbc();
		MemberBean mbn = new MemberBean();
		MemberService mbservice = new MemberServiceImpl();

		System.out.println(req.getParameter("username"));
		System.out.println(req.getParameter("password"));
		System.out.println(req.getParameter("email"));
		System.out.println(req.getParameter("age"));
		System.out.println(req.getParameter("gender"));
		
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String agestr = req.getParameter("age");
		int age = 0 ;
		age = Integer.parseInt(agestr.trim());
		
		mbn.setEmail(email);
		mbn.setPassword(password);
		mbn.setUsername(username);
		mbn.setGender(gender);
		mbn.setAge(age);
		
		
		mbpl.saveMember(mbn);
		

		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	
	

}
}