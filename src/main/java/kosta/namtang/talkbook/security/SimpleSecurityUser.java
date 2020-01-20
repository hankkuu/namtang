package kosta.namtang.talkbook.security;

import kosta.namtang.talkbook.common.RoleCode;
import kosta.namtang.talkbook.model.domain.Admin;
import kosta.namtang.talkbook.model.domain.account.Account;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@EqualsAndHashCode
@Getter
public class SimpleSecurityUser extends User {

    private static final String ROLE_PREFIX = "ROLE_";

    private Account account;
    private String accountId;
    private String text;            // 원하는 값을 넣을 수 있음

//    private Admin admin;
//    private String adminId;

    public SimpleSecurityUser(Account acc) {
        super(String.valueOf(acc.getAccountIdx()), acc.getUserPassword(), makeGrantedAuthority(acc.getRole()));
        this.account = acc;
        this.accountId = String.valueOf(acc.getAccountIdx());
        this.text = acc.getLastLoginDate().toString();
    }

//    public SimpleSecurityUser(Admin admin) {
//        super(admin.getAdminId(), admin.getAdminPassword(), makeGrantedAuthority(RoleCode.Admin));
//        this.admin = admin;
//        this.adminId = String.valueOf(admin.getAdminId());
//    }

    public static List<GrantedAuthority> makeGrantedAuthority(RoleCode role) {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.name()));
        return list;
    }
}
