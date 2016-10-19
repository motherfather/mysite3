package com.bit2016.mysite.action.board;

import com.bit2016.web.Action;
import com.bit2016.web.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if ("writeform".equals(actionName)) {
			action = new WriteFormAction();
		} else if ("viewform".equals(actionName)) {
			action = new ViewAction();
		} else if ("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		} else {
			action = new ListAction();
		}
		return action;
	}

}
