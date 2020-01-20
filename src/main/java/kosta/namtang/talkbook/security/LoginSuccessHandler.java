package kosta.namtang.talkbook.security;

import kosta.namtang.talkbook.model.domain.account.Account;
import kosta.namtang.talkbook.repository.account.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

@Slf4j
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private HttpSession session; //autowiring session

    @Autowired
    private AccountRepository repository; //autowire the user repo

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        super.onAuthenticationSuccess(request, response, authentication);

        String userId = "";
        String loginId = "";
        if (authentication.getPrincipal() instanceof Principal) {
            userId = ((Principal) authentication.getPrincipal()).getName();

        } else {
            if (authentication.getPrincipal() instanceof SimpleSecurityUser) {
                loginId = ((SimpleSecurityUser) authentication.getPrincipal()).getUserId();
            }
            userId = ((User) authentication.getPrincipal()).getUsername();
        }
        logger.info("userName: " + userId);
        logger.debug("test message: " + loginId);

        //Account account = repository.findByUserId(userId);

        HttpSession session = request.getSession();
        session.setAttribute("userIdx", userId);
        session.setAttribute("loginId", loginId);
    }

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {

        log.info("--------------determineTargetUrl------------------------");

        Object dest = request.getSession().getAttribute("destination");

        String nextURL = null;

        if (dest != null) {

            request.getSession().removeAttribute("destination");

            nextURL = (String) dest;

        } else {

            nextURL = super.determineTargetUrl(request, response);
        }

        log.info("-------------------" + nextURL + "========================");
        return nextURL;
    }
}
