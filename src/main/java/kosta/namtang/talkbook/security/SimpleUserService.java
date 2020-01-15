package kosta.namtang.talkbook.security;

import freemarker.template.utility.DateUtil;
import kosta.namtang.talkbook.model.domain.account.Account;
import kosta.namtang.talkbook.repository.account.AccountRepository;
import kosta.namtang.talkbook.util.DateTimeHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SimpleUserService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        Account acc = accountRepository.findByUserId(id);
        if(acc != null) {
            acc.setLastLoginDate(DateTimeHelper.timeStampNow());
            accountRepository.save(acc);
        }

        SimpleSecurityUser user = new SimpleSecurityUser(acc);


        return user;
    }
}
