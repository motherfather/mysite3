package com.bit2016.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.dao.BoardDao;
import com.bit2016.mysite.vo.BoardVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
//		BoardDao dao = new BoardDao();
//		Long no1 = Long.parseLong(no);
//		BoardVo vo = dao.getView(no1);
		
		request.setAttribute("data", new BoardDao().getView(Long.parseLong(no)));
		
		WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");
	}

}
