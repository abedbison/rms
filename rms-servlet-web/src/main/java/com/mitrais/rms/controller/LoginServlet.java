package com.mitrais.rms.controller;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends AbstractController {

    private static final String WEB_MSG = "wmx";
    private static final String LOGGED = "loggedUser";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String sLogout = req.getParameter("logout");
        boolean isLogout = (sLogout != null && "X".equals(sLogout));
        
        // no need re-login if already logged
        HttpSession session = req.getSession();
        synchronized(session) {
            User user = (User) session.getAttribute(LOGGED);
            if (user != null && !isLogout) {
                resp.sendRedirect("index.jsp");
            } else {
                if (isLogout) {
                    session.removeAttribute(LOGGED);
                    resp.sendRedirect("index.jsp");
                } else {
                    String sessionMessage = (String) session.getAttribute(WEB_MSG);
                    if (sessionMessage != null) {
                        req.setAttribute(WEB_MSG, sessionMessage);
                        session.removeAttribute(WEB_MSG);
                    }
                    
                    String path = getTemplatePath(req.getServletPath());
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
                    requestDispatcher.forward(req, resp);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        req.setAttribute("userName", userName);
        
        UserDao userDao = UserDaoImpl.getInstance();
        Optional<User> oUser = userDao.findByUserName(userName);
        if (oUser.isPresent() && oUser.get().getPassword().equals(password)) {
            HttpSession session = req.getSession();
            synchronized(session) {
                session.setAttribute(LOGGED, oUser.get());
            }
            resp.sendRedirect("index.jsp");
        } else {
            req.setAttribute(WEB_MSG, "login failed");
            doGet(req, resp);
        }
    }
}
