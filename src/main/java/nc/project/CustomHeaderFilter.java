package nc.project;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CustomHeaderFilter implements Filter {
    @Override
    public void destroy() {
        System.out.println("destroy filter. release our resources here if any");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
            IOException,ServletException {
        HttpServletResponse httpServletResponse=(HttpServletResponse)response;
        httpServletResponse.setHeader("Custom-Filter-Header","*");
        chain.doFilter(request, response);      // continue execution of other filter chain.
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Init filter");
    }
}
