package kosta.namtang.talkbook.security.intercepter;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Slf4j
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        log.info("preHandle.................................");

        Enumeration<String> en = request.getSession().getAttributeNames();

        while (en.hasMoreElements()) {
            String name = en.nextElement();
            log.info("NAME: " + name);
            log.info("" + request.getSession().getAttribute(name));
        }


        String dest = request.getParameter("dest");

        if (dest != null) {
            request.getSession().setAttribute("dest", dest);
        }

        return super.preHandle(request, response, handler);
    }


}