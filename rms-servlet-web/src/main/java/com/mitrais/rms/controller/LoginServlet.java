package com.mitrais.rms.controller;

import com.mitrais.rms.model.User;
import com.mitrais.rms.service.UserService;
import com.mitrais.rms.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String sLogout = req.getParameter("logout");
        boolean isLogout = ("X".equals(sLogout));

        HttpSession session = req.getSession();
        synchronized(session) {
            User user = (User) session.getAttribute(LOGGED);
            if (isLogout) {
                session.removeAttribute(LOGGED);
            }
            if (isLogout || user != null) {
                resp.sendRedirect("index.jsp");
            } else {
                this.appendMessage(req, session);
                String path = getTemplatePath(req.getServletPath());
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
                requestDispatcher.forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        req.setAttribute("userName", userName);

        UserService userService = UserServiceImpl.getInstance();
        Optional<User> oUser = userService.findByUserName(userName);
        HttpSession session = req.getSession();
        oUser.ifPresent(user -> {
            if (user.getPassword().equals(password)) {
                synchronized(session) {
                    session.setAttribute(LOGGED, oUser.get());
                }
            }
        });
        if (!oUser.isPresent()) {
            synchronized(session) {
                this.setMessage(session, "login failed");
            }
        }
        resp.sendRedirect("login");
    }
    
}
