package kosta.namtang.talkbook.security;

import freemarker.template.utility.DateUtil;
import kosta.namtang.talkbook.model.domain.Admin;
import kosta.namtang.talkbook.model.domain.account.Account;
import kosta.namtang.talkbook.repository.AdminRepository;
import kosta.namtang.talkbook.repository.account.AccountRepository;
import kosta.namtang.talkbook.util.DateTimeHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Service
@Slf4j
public class SimpleUserService implements UserDetailsService {

    private static String USER_TYPE = "userType";

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        String userType = (String) RequestContextHolder.getRequestAttributes().getAttribute(USER_TYPE, RequestAttributes.SCOPE_SESSION);
        log.debug("user type :" + userType);

        if ("admin".equals(userType) == true) {
            Admin admin = adminRepository.findByAdminId(id);
            SimpleSecurityUser user = new SimpleSecurityUser(admin);
            //return User.builder().username(admin.getAdminId()).password(admin.getAdminPassword()).roles(admin.getAdminRoleName()).build();
            return user;
        }

        Account acc = accountRepository.findByUserId(id);
        if (acc != null) {
            acc.setLastLoginDate(DateTimeHelper.timeStampNow());
            accountRepository.save(acc);
        }

        SimpleSecurityUser user = new SimpleSecurityUser(acc);
        return user;
    }
}
