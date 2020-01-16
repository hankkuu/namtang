package kosta.namtang.talkbook.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kosta.namtang.talkbook.model.domain.Admin;
import kosta.namtang.talkbook.model.domain.account.Account;
import kosta.namtang.talkbook.repository.AdminRepository;
import kosta.namtang.talkbook.repository.account.AccountRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    @Autowired
    AccountRepository accountRepository;

    public UserDetailsServiceImpl() {
        System.out.println("UserDetailsServiceImpl 생성자............");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account =  accountRepository.findByUserId(username);
        
        return User.builder().username(account.getUserId()).password(account.getUserPassword()).roles(account.getRole().toString()).build();
        //return null;
    }

}
