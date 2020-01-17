package kosta.namtang.talkbook.model.domain.account;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Users {

    @Id
    private long accountIdx;
    @Column(name = "USER_NAME", nullable = false)
    private String userName;
    @Column(name = "USER_SEX", nullable = false)
    private String userSex;
    @Column(name = "USER_AGE", nullable = true)
    private String userAge;
    @Column(name = "USER_PHONE", nullable = false)
    private String userPhone;
    @Column(name = "USER_ADDRESS", nullable = false)
    private String userAddress;
    @Column(name = "CREATE_DATE", nullable = false)
    private Timestamp createDate;
    @Column(name = "UPDATE_DATE", nullable = false)
    private Timestamp updateDate;
}
