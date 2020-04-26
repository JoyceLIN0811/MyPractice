package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _00_init.util.GlobalService;
import model.*;
import service.*;
import service.impl.*;

@WebServlet("/_02_login/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		request.setAttribute("ErrorMsgKey", errorMsgMap);
	
		//  讀取使用者輸入資料
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String requestURI = (String) session.getAttribute("requestURI");

		// 檢查使用者輸入資料

		MemberService memberService = new MemberServiceImpl();
		MemberBean mb = null;
		try {
			mb = memberService.checkIDPassword(email, password);
			if (mb != null) {
				session.setAttribute("LoginOK", mb);
			} else {
				errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
			}
		} catch (RuntimeException ex) {
			errorMsgMap.put("LoginError", ex.getMessage());
		}
		if (errorMsgMap.isEmpty()) {
			if (requestURI != null) {
				requestURI = (requestURI.length() == 0 ? request.getContextPath() : requestURI);
				response.sendRedirect(response.encodeRedirectURL(requestURI));
				return;
			} else {
				request.getRequestDispatcher("/fragment/top.jsp").forward(request, response);

				return;
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/_02_login/login.jsp");
			rd.forward(request, response);
			return;
		}
	}
}
