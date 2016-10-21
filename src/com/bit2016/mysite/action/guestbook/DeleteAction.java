package com.bit2016.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.dao.GuestBookDao;
import com.bit2016.mysite.vo.GuestBookVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String no = request.getParameter("no");
		
		GuestBookVo vo = new GuestBookVo();
		long no1 = Long.parseLong(no);
		new GuestBookDao().delete(no1, password);
		
		WebUtil.redirect(request, response, "/mysite3/guestbook");
	}

}
