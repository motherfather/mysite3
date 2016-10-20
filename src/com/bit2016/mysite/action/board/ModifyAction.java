package com.bit2016.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.dao.BoardDao;
import com.bit2016.mysite.vo.BoardVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardVo vo = new BoardVo();
		vo.setNo(Long.parseLong(no));
		vo.setTitle(title);
		vo.setContent(content);
		
		new BoardDao().modify(vo);
		
		WebUtil.redirect(request, response, "/mysite3/board?a=view&no="+no);
	}

}
