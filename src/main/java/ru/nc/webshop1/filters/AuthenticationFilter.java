package ru.nc.webshop1.filters;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        boolean loggedIn = session != null && session.getAttribute("user") != null;

        if (!loggedIn) {
            RequestDispatcher rd = request.getRequestDispatcher("/login");
            request.setAttribute("message", "<font color=red>Доступ запрещён для неавторизированных пользователей!</font>");
            rd.include(request, response);
        } else {
            boolean isManager = session.getAttribute("role").toString().equals("0");
            if (!isManager) {
                RequestDispatcher rd = request.getRequestDispatcher("/cabinet");
                request.setAttribute("message", "<font color=red>Вам не хватает прав доступа для просмотра этой страницы!</font>");
                rd.include(request, response);
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }

}
