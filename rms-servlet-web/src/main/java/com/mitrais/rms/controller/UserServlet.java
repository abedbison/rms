package com.mitrais.rms.controller;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;
import com.mitrais.rms.service.impl.UserServiceImpl;
import com.mitrais.rms.util.CommonHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;

@WebServlet("/users/*")
public class UserServlet extends AbstractController {
    
    private static final String WEB_MSG = "wmx";
    private static final String LOGGED = "loggedUser";
    private UserServiceImpl userService = UserServiceImpl.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String pathInfo = req.getPathInfo();
        String path = getTemplatePath(req.getServletPath()+req.getPathInfo());
        HttpSession session = req.getSession();
        synchronized(session) {
            User loggedUser = (User) session.getAttribute(LOGGED);
            if (loggedUser == null) {
                redirectLogin(req, resp, session);
            } else {
                UserDao userDao = UserDaoImpl.getInstance();
                if ("/list".equalsIgnoreCase(pathInfo)){ // GET LIST
//                    UserDao userDao = UserDaoImpl.getInstance();
                    List<User> users = userDao.findAll();
                    req.setAttribute("users", users);
                } else if (pathInfo.startsWith("/form")) { // GET VALUE
                    User user = null;
                    long id = CommonHelper.parseLong(req.getParameter("id"));
                    if (id > 0) {
//                        UserDao userDao = UserDaoImpl.getInstance();
                        Optional<User> oUser = userDao.find(id);
                        user = (oUser.isPresent())?oUser.get():null;
                    }
                    req.setAttribute("user", user);
                    
                    String action = req.getParameter("action");
                    String titleText = "New";
                    String btnText = "Save";
                    if (action.equalsIgnoreCase("edit")) {
                        action = "edit";
                        titleText = "Edit";
                    } else if(action.equalsIgnoreCase("delete")) {
                        action = "delete";
                        titleText = "Delete";
                        btnText = "Delete";
                    } else {
                        action = "new";
                    }
                    req.setAttribute("action", action);
                    req.setAttribute("titleText", titleText);
                    req.setAttribute("btnText", btnText);
                }
                
                String sessionMessage = (String) session.getAttribute(WEB_MSG);
                if (sessionMessage != null) {
                    req.setAttribute("sessionMessage", sessionMessage);
                    session.removeAttribute(WEB_MSG);
                }
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
                requestDispatcher.forward(req, resp);
            } // if (loggedUser == null)
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String pathInfo = req.getPathInfo();
        String action = req.getParameter("action");
        String sessionMessage = null;
        HttpSession session = req.getSession();
        synchronized(session) {
            User loggedUser = (User) session.getAttribute(LOGGED);
            if (loggedUser == null) {
                redirectLogin(req, resp, session);
            } else {
                User user = new User(CommonHelper.parseLong(req.getParameter("id")),
                        req.getParameter("userName"), req.getParameter("password"));
                switch (action) {
                    case "add":
                        sessionMessage = userService.save(user);
                        break;
                    case "edit":
                        sessionMessage = userService.update(user);
                        break;
                    case "delete":
                        sessionMessage = userService.delete(user);
                        break;
                    default:
                        sessionMessage = "no action defined";
                        break;
                }
                session.setAttribute(WEB_MSG, sessionMessage);
                resp.sendRedirect(req.getContextPath() + "/users/list");
            } // if (loggedUser == null)
        }
    }
    
    protected void redirectLogin(HttpServletRequest req, HttpServletResponse resp,
            HttpSession session) throws ServletException, IOException {
        session.setAttribute(WEB_MSG, "Please Login First");
        resp.sendRedirect(req.getContextPath() + "/login");
    }
    
}
