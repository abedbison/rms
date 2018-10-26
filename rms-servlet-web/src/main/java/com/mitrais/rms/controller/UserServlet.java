package com.mitrais.rms.controller;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;
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
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String pathInfo = req.getPathInfo();
        String path = getTemplatePath(req.getServletPath()+req.getPathInfo());
        HttpSession session = req.getSession();
        synchronized(session) {
            User loggedUser = (User) session.getAttribute(LOGGED);
            if (loggedUser != null) {
                if ("/list".equalsIgnoreCase(pathInfo)){ // GET LIST
                    UserDao userDao = UserDaoImpl.getInstance();
                    List<User> users = userDao.findAll();
                    req.setAttribute("users", users);
                } else if (pathInfo.startsWith("/form")) { // GET VALUE
                    User user = null;
                    long id = CommonHelper.parseLong(req.getParameter("id"));
                    if (id > 0) {
                        UserDao userDao = UserDaoImpl.getInstance();
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
            } else {
                redirectLogin(req, resp, session);
            } // if (loggedUser != null)
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String pathInfo = req.getPathInfo();
        long id = CommonHelper.parseLong(req.getParameter("id"));
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String action = req.getParameter("action");
        String sessionMessage = null;
        HttpSession session = req.getSession();
        synchronized(session) {
            User loggedUser = (User) session.getAttribute(LOGGED);
            if (loggedUser != null) {
                if (pathInfo.equalsIgnoreCase("/form")) {
                    User user = null;
                    UserDao userDao = UserDaoImpl.getInstance();
                    user = new User(id, userName, password);
                    if ("add".equalsIgnoreCase(action)) { // INSERT
                        if (userDao.findByUserName(userName).isPresent()) {
                            sessionMessage = userName + " already exist";
                        } else {
                            sessionMessage = (userDao.save(user))? 
                                    "user name <b>" +  userName + "</b> created": 
                                    "failed to create <b>" +  userName + "</b>";
                        }
                    } else if ("edit".equalsIgnoreCase(action)) { // UPDATE
                        sessionMessage = (userDao.update(user))? 
                                "user name <b>" +  userName + "</b> changed": 
                                "failed to change <b>" +  userName + "</b> data";
                    } else if ("delete".equalsIgnoreCase(action)) { // DELETE
                        sessionMessage = (userDao.delete(user))? 
                                "user name <b>" +  userName + "</b> deleted": 
                                "failed to delete <b>" +  userName + "</b>";
                    } else {
                        sessionMessage = "no action defined";
                    }
                }
                session.setAttribute(WEB_MSG, sessionMessage);
                resp.sendRedirect(req.getContextPath() + "/users/list");
            } else {
                redirectLogin(req, resp, session);
            }
        }
    }
    
    protected void redirectLogin(HttpServletRequest req, HttpServletResponse resp,
            HttpSession session) throws ServletException, IOException {
        session.setAttribute(WEB_MSG, "Please Login First");
        resp.sendRedirect(req.getContextPath() + "/login");
    }
    
}
