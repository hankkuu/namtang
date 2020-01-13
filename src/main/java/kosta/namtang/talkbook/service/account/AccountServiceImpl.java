package kosta.namtang.talkbook.service.account;

import kosta.namtang.talkbook.common.RoleCode;
import kosta.namtang.talkbook.model.domain.account.Account;
import kosta.namtang.talkbook.model.domain.account.Users;
import kosta.namtang.talkbook.repository.account.AccountRepository;
import kosta.namtang.talkbook.repository.account.UserRepository;
import kosta.namtang.talkbook.util.DateTimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public Account createAccount(Account acc, Users user) throws Exception {
        Timestamp createDate = DateTimeHelper.timeStampNow();
        acc.setCreateDate(createDate);
        acc.setDeleteDate(DateTimeHelper.timeStamp("1900-01-01 00:00:00"));
        acc.setLastLoginDate(DateTimeHelper.timeStamp("1900-01-01 00:00:00"));
        acc.setTermsAgreeDate(createDate);
        acc.setRole(RoleCode.Member);
        Account member = accountRepository.save(acc);
        if(member == null) {
            throw new Exception();
        }

        user.setAccountIdx(member.getAccountIdx());
        user.setCreateDate(createDate);
        user.setUpdateDate(createDate);
        Users u = userRepository.save(user);
        if(u == null) {
            throw new Exception();
        }

        return member;
    }

    @Override
    public void updateUser(Users user) {
        Optional<Users> u = userRepository.findById(user.getAccountIdx());
        u.ifPresent( (update) -> {
            update.setUpdateDate(DateTimeHelper.timeStampNow());
            update.setUserAddress(user.getUserAddress());
            update.setUserAge(user.getUserAge());
            update.setUserName(user.getUserName());
            update.setUserPhone(user.getUserPhone());

            userRepository.save(update);
        });
    }

    @Override
    public void deleteAccount(Account acc) {
        return;
    }

    @Override
    public Account login(Account acc) {
        return null;
    }
}
