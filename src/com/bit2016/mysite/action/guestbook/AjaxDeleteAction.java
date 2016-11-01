package com.bit2016.mysite.action.guestbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.dao.GuestbookDao;
import com.bit2016.web.Action;

import net.sf.json.JSONObject;

public class AjaxDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sNo = request.getParameter("no");
		String pw = request.getParameter("password");
		Long no = Long.parseLong(sNo);
		new GuestbookDao().delete(no, pw);
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("result", "success");
//		map.put("data", vo);
//		
//		response.setContentType("application/json; charset=utf-8");
//		JSONObject jsonObject = JSONObject.fromObject(map);
//		response.getWriter().println(jsonObject.toString());
	}

}
