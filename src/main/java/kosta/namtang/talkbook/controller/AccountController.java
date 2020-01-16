package kosta.namtang.talkbook.controller;

import kosta.namtang.talkbook.common.ShopResponse;
import kosta.namtang.talkbook.common.StatusCode;
import kosta.namtang.talkbook.model.domain.account.Account;
import kosta.namtang.talkbook.model.domain.account.Users;
import kosta.namtang.talkbook.model.domain.bill.BillKey;
import kosta.namtang.talkbook.model.dto.request.PurchaseSetRequest;
import kosta.namtang.talkbook.model.dto.request.UserSetRequest;
import kosta.namtang.talkbook.service.account.AccountService;
import kosta.namtang.talkbook.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    //@Autowired
    //AccountService accountService;

    //@Autowired
    //PasswordEncoder pwEncoder;

    @PostMapping("")
    public ShopResponse purchase(@RequestBody UserSetRequest user) throws Exception {
//        ShopResponse result = null;
//        log.debug(user.toString());
//
//        Account account = accountService.createAccount(user);
//
//        if(account != null) {
//            // 구매 DB 입력 완료
//            result = new ShopResponse(StatusCode.Success, JsonUtil.toJson(account));
//        } else {
//            throw new Exception();
//        }
//
//        return result;
        return null;
    }

    @PostMapping("/login")
    public ShopResponse login(@RequestBody UserSetRequest user) throws Exception {
//        ShopResponse result = null;
//        log.debug(user.toString());
//
//        Account acc = new Account();
//        acc.setUserId(user.getEmail());
//        acc.setUserPassword(user.getPassword());
//
//        Account account = accountService.login(acc);
//
//        if(account != null) {
//            // 구매 DB 입력 완료
//            result = new ShopResponse(StatusCode.Success, JsonUtil.toJson(account));
//        } else {
//            throw new Exception();
//        }
//
//        return result;
        return null;
    }

}
