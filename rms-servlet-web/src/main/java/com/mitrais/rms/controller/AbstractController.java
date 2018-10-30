package com.mitrais.rms.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractController extends HttpServlet {

    public static final String VIEW_PREFIX = "/WEB-INF/jsp";
    public static final String VIEW_SUFFIX = ".jsp";
    protected static final String WEB_MSG = "wmx";
    protected static final String LOGGED = "loggedUser";

    protected String getTemplatePath(String path) {
        if (path.equalsIgnoreCase("/")) {
            return VIEW_PREFIX + path + "index" + VIEW_SUFFIX;
        } else {
            return VIEW_PREFIX + path + VIEW_SUFFIX;
        }
    }

    protected void setMessage(HttpSession session, String message) {
        session.setAttribute(WEB_MSG, message);
    }

    protected void appendMessage(HttpServletRequest req, HttpSession session) {
        String sessionMessage = (String) session.getAttribute(WEB_MSG);
        if (sessionMessage != null) {
            req.setAttribute(WEB_MSG, sessionMessage);
            session.removeAttribute(WEB_MSG);
        }
    }
    
}
