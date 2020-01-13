package kosta.namtang.talkbook.security;

import kosta.namtang.talkbook.common.RoleCode;
import kosta.namtang.talkbook.model.domain.account.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class SimpleSecurityUser extends User {

    private static final String ROLE_PREFIX ="ROLE_";

    private Account account;
    private String accountId;

    public SimpleSecurityUser(Account acc) {
        super(acc.getUserId(), acc.getUserPassword(), makeGrantedAuthority(acc.getRole()));
        this.account = acc;
        this.accountId = acc.getUserId();
    }

    public static List<GrantedAuthority> makeGrantedAuthority(RoleCode role) {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.name()));
        return list;
    }
}
