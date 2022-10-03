package app.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AutoFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
//        chain.doFilter(request,response);
        if (request.getMethod().equals("GET")) {
            chain.doFilter(request, response);
            return;
        }
        String secret = getServletContext().getInitParameter("secret");
        String textFromHeader = request.getHeader("Authorization");
        String[] message = textFromHeader.split(" ");
        if (message.length > 1) {
            if (message[1].equals(secret)) {
                chain.doFilter(req, res);
                return;
            }
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
