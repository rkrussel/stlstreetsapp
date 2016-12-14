package com.stlstreetapp.controllers;

import javax.servlet.http.HttpSession;

import com.stlstreetapp.models.User;
import com.stlstreetapp.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import com.stlstreetapp.models.dao.ZoneDao;

public class AbstractController {
	@Autowired
    protected UserDao userDao;
	
	@Autowired
	protected ZoneDao zoneDao;

    public static final String userSessionKey = "user_id";

    protected User getUserFromSession(HttpSession session) {
    	
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        return userId == null ? null : userDao.findByUid(userId);
    }
    
    protected void setUserInSession(HttpSession session, User user) {
    	session.setAttribute(userSessionKey, user.getUid());
    }
    
	
}
