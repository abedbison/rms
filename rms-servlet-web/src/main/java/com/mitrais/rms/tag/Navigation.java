package com.mitrais.rms.tag;

import com.mitrais.rms.model.User;
import com.mitrais.rms.util.CommonHelper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Navigation extends SimpleTagSupport {
    
    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        HttpSession session = request.getSession();
        String relLink = request.getContextPath()+ "/";
        User loggedUser;
        synchronized(session) {
            loggedUser = (User) session.getAttribute("loggedUser");
        }
        
        JspWriter out = getJspContext().getOut();
        if (loggedUser != null) {
            out.print("<a class=\"mdl-navigation__link\" href=\"" +
                    relLink + "users/list\">Users</a>");
            out.print("<a class=\"mdl-navigation__link\" href=\"" + 
                    relLink + "users/form?action=add\">Add User</a>");  
            out.println("<a class=\"mdl-navigation__link\" href=\"" + 
                    relLink + "login?logout=X\">Logout (" + loggedUser.getUserName() + ")</a>");  
        } else {
            out.println("<a class=\"mdl-navigation__link\" href=\"" + 
                    relLink + "login\">Login</a>");   
        }
        
    }
    
}
