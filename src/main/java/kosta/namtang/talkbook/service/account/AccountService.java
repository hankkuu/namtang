package kosta.namtang.talkbook.service.account;

import kosta.namtang.talkbook.model.domain.account.Account;
import kosta.namtang.talkbook.model.domain.account.Users;

public interface AccountService {

    Account createAccount(Account acc, Users user) throws Exception;

    void updateUser(Users user);

    void deleteAccount(Account acc);

    Account login(Account acc);


}
