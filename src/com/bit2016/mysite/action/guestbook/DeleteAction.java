package com.bit2016.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.dao.GuestbookDao;
import com.bit2016.mysite.vo.GuestbookVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String no = request.getParameter("no");
		
		GuestbookVo vo = new GuestbookVo();
		long no1 = Long.parseLong(no);
		new GuestbookDao().delete(no1, password);
		
		WebUtil.redirect(request, response, "/mysite3/guestbook");
	}

}
