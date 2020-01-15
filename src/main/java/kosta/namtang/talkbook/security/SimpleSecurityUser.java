package kosta.namtang.talkbook.security;

import kosta.namtang.talkbook.common.RoleCode;
import kosta.namtang.talkbook.model.domain.account.Account;
import lombok.Getter;
import lombok.Setter;
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
    private String text;

    public SimpleSecurityUser(Account acc) {
        super(String.valueOf(acc.getAccountIdx()), acc.getUserPassword(), makeGrantedAuthority(acc.getRole()));
        this.account = acc;
        this.accountId = String.valueOf(acc.getAccountIdx());
        this.text = acc.getLastLoginDate().toString();
    }

    public static List<GrantedAuthority> makeGrantedAuthority(RoleCode role) {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.name()));
        return list;
    }
}
