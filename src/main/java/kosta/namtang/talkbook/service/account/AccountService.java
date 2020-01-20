package kosta.namtang.talkbook.service.account;

import kosta.namtang.talkbook.common.ShopResponse;
import kosta.namtang.talkbook.model.domain.account.Account;
import kosta.namtang.talkbook.model.domain.account.UserAddress;
import kosta.namtang.talkbook.model.domain.account.Users;
import kosta.namtang.talkbook.model.dto.request.UserSetRequest;

import java.util.List;

public interface AccountService {

    Account createAccount(UserSetRequest request) throws Exception;

    void updateUser(UserSetRequest user);

    Users updateAccount(long userIdx);

    void deleteAccount(long accountIdx);

    Account login(Account acc) throws Exception;

    ShopResponse checkNewUser(String userId);

    boolean checkPassword(long accountIdx, String password);

    List<UserAddress> selectAddress(long accountIdx);
}
