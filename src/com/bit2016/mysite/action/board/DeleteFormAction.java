package com.bit2016.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.dao.BoardDao;
import com.bit2016.mysite.vo.BoardVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class DeleteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String users_no = request.getParameter("no");
		BoardDao dao = new BoardDao();
		Long no = Long.parseLong(users_no);
		BoardVo vo = new BoardVo();
		vo = dao.getView(no);
		
		if (authUser.getName() == vo.getUsers_no()) {
			new BoardDao().delete(no);
		}
		WebUtil.redirect(request, response, "/WEB-INF/views/board");
	}

}
