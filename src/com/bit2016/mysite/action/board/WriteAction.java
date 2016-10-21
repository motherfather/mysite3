package com.bit2016.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2016.mysite.dao.BoardDao;
import com.bit2016.mysite.vo.BoardVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("group_no") == null) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String no = request.getParameter("no");
			
			BoardVo vo = new BoardVo();
			
			vo.setTitle(title);
			vo.setContent(content);
			vo.setNo(Long.parseLong(no));
			
			new BoardDao().write(vo);
		} else if (request.getParameter("group_no") != null) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String users_no = request.getParameter("users_no");
			String group_no = request.getParameter("group_no");
			String order_no = request.getParameter("order_no");
			String depth = request.getParameter("depth");
			BoardVo vo = new BoardVo();
			
			vo.setTitle(title);
			vo.setContent(content);
			vo.setUsers_no(Long.parseLong(users_no));
			vo.setGroup_no(Long.parseLong(group_no));
			vo.setOrder_no(Long.parseLong(order_no));
			vo.setDepth(Long.parseLong(depth));
			
			new BoardDao().write(vo);
		}
		
		
		WebUtil.redirect(request, response, "/mysite3/board");
	}

}
