package kosta.namtang.talkbook.core.log;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {
        final String url = ((HttpServletRequest) req).getRequestURI();

        if(url.matches("/(health|.+\\.(ico|js|css|jpg|png|woff|woff2))")) {
            req.setAttribute("ignoreLogging", true);
        }

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}
