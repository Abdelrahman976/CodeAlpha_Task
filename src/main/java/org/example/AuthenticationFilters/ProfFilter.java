package org.example.AuthenticationFilters;

import org.example.Model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/prof.jsp", "/ProfDashboard.jsp", "/viewAttendance.jsp", "/editAttendance.jsp"})
public class ProfFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String loginURL = req.getContextPath() + "/login.jsp";
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        User user = loggedIn ? (User) session.getAttribute("user") : null;
        System.out.println("ProfFilter: " + loggedIn + " " + user);
        // Role-based access control
        if (loggedIn && user.getRole().equalsIgnoreCase("professor")) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(loginURL);
        }
    }
    public void destroy() {}
}
