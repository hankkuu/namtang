package kosta.namtang.talkbook.model.domain.account;

import kosta.namtang.talkbook.common.RoleCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ACCOUNT_SEQ_NO")
    @SequenceGenerator(sequenceName = "ACCOUNT_SEQ_NO", name = "ACCOUNT_SEQ_NO", allocationSize = 1)
    private long accountIdx;
    @Column(name = "USER_ID", nullable = false)
    private String userId;
    @Column(name = "USER_PASSWORD", nullable = false)
    private String userPassword;
    @Column(name = "ROLE", nullable = false)
    private RoleCode role;

    @Column(name = "CREATE_DATE", nullable = false)
    private Timestamp createDate;
    @Column(name = "DELETE_DATE", nullable = false)
    private Timestamp deleteDate;
    @Column(name = "LAST_LOGIN_DATE", nullable = false)
    private Timestamp lastLoginDate;
    @Column(name = "TERMS_AGREE_DATE", nullable = false)
    private Timestamp termsAgreeDate;
}


