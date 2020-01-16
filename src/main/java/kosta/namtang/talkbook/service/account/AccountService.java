package kosta.namtang.talkbook.service.account;

import kosta.namtang.talkbook.model.domain.account.Account;
import kosta.namtang.talkbook.model.domain.account.Users;
import kosta.namtang.talkbook.model.dto.request.UserSetRequest;

public interface AccountService {

    Account createAccount(UserSetRequest request) throws Exception;

    void updateUser(Users user);

    void deleteAccount(Account acc);

    Account login(Account acc) throws Exception;

    Boolean checkId(String id);
}
