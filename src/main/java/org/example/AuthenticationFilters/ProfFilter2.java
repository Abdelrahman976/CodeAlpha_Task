package org.example.AuthenticationFilters;

import org.example.Model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/ProfDashboard.jsp")
public class ProfFilter2 implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String loginURL = req.getContextPath() + "/login.jsp";
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        User user = loggedIn ? (User) session.getAttribute("user") : null;

        // Role-based access control
        if (loggedIn && user.getRole().equals("professor")) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(loginURL);
        }
    }
}
