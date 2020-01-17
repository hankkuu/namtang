package kosta.namtang.talkbook.service.account;

import kosta.namtang.talkbook.common.RoleCode;
import kosta.namtang.talkbook.model.domain.account.Account;
import kosta.namtang.talkbook.model.domain.account.Users;
import kosta.namtang.talkbook.model.dto.request.UserSetRequest;
import kosta.namtang.talkbook.repository.account.AccountRepository;
import kosta.namtang.talkbook.repository.account.UserRepository;
import kosta.namtang.talkbook.util.DateTimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    PasswordEncoder pwEncoder;

    @Transactional
    @Override
    public Account createAccount(UserSetRequest request) throws Exception {

        String encryptPw = pwEncoder.encode(request.getPassword());
        Timestamp createDate = DateTimeHelper.timeStampNow();

        Account acc = new Account();
        acc.setUserId(request.getUserId());
        acc.setUserPassword(encryptPw);
        acc.setCreateDate(createDate);
        acc.setDeleteDate(DateTimeHelper.timeStamp("1900-01-01 00:00:00"));
        acc.setLastLoginDate(DateTimeHelper.timeStamp("1900-01-01 00:00:00"));
        acc.setTermsAgreeDate(createDate);
        acc.setRole(RoleCode.Member);
        Account member = accountRepository.save(acc);
        if (member == null) {
            throw new Exception();
        }
        Users user = new Users();
        user.setUserEmail(request.getEmail());
        System.out.println(request.getEmail());
        user.setUserPost(request.getUserPost());
        user.setUserAddress(request.getUserAddress());
        user.setUserAddressDetail(request.getUserAddressDetail());
        user.setUserName(request.getUserName());
        user.setUserPhone(request.getPhone());
        user.setUserSex(request.getSex());
        user.setAccountIdx(member.getAccountIdx());
        user.setCreateDate(createDate);
        user.setUpdateDate(createDate);
        Users u = userRepository.save(user);
        if (u == null) {
            throw new Exception();
        }

        return member;
    }

    @Override
    public Users updateAccount(long userIdx) {
        Users user = userRepository.findById(userIdx).orElse(null);
        return user;
    }

    @Override
    public void updateUser(UserSetRequest user) {
        Optional<Users> u = userRepository.findById(user.getUserIdx());
        u.ifPresent((update) -> {
            update.setUpdateDate(DateTimeHelper.timeStampNow());
            update.setUserName(user.getUserName());
            update.setUserPhone(user.getPhone());
            update.setUserEmail(user.getEmail());
            update.setUserPost(user.getUserPost());
            update.setUserAddress(user.getUserAddress());
            update.setUserAddressDetail(user.getUserAddressDetail());

            userRepository.save(update);
        });
    }

    @Override
    public void deleteAccount(Account acc) {
        Optional<Account> account = accountRepository.findById(acc.getAccountIdx());
        account.ifPresent((update) -> {
            update.setDeleteDate(DateTimeHelper.timeStampNow());
            accountRepository.save(update);
        });
        return;
    }

    @Override
    public Account login(Account account) throws Exception {

        // security로 대체된 기능
        Account a = accountRepository.findByUserId(account.getUserId());
        if (a != null) {
            Boolean matched = pwEncoder.matches(account.getUserPassword(), a.getUserPassword());
            if (matched == true) {
                return a;
            } else {
                throw new Exception();
            }
        }
        return a;
    }

    @Override
    public Boolean checkId(String id) {
        Account acc = accountRepository.findByUserId(id);
        if (acc != null) {
            return true;
        }
        return false;
    }
}
