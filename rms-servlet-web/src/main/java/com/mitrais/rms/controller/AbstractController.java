package com.mitrais.rms.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractController extends HttpServlet {

    private static final String VIEW_PREFIX = "/WEB-INF/jsp";
    private static final String VIEW_SUFFIX = ".jsp";
    private static final String WEB_MSG = "wmx";
    static final String LOGGED = "loggedUser";

    String getTemplatePath(String path) {
        if (path.equalsIgnoreCase("/")) {
            return VIEW_PREFIX + path + "index" + VIEW_SUFFIX;
        } else {
            return VIEW_PREFIX + path + VIEW_SUFFIX;
        }
    }

    void setMessage(HttpSession session, String message) {
        session.setAttribute(WEB_MSG, message);
    }

    void appendMessage(HttpServletRequest req, HttpSession session) {
        String sessionMessage = (String) session.getAttribute(WEB_MSG);
        if (sessionMessage != null) {
            req.setAttribute(WEB_MSG, sessionMessage);
            session.removeAttribute(WEB_MSG);
        }
    }
    
}
