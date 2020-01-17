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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("")
    public ShopResponse signUp(@RequestBody UserSetRequest user) throws Exception {
        ShopResponse result = null;
        log.debug(user.toString());
        System.out.println("1" + user.getEmail());
        Account account = accountService.createAccount(user);

        if (account != null) {
            // 구매 DB 입력 완료
            result = new ShopResponse(StatusCode.Success, JsonUtil.toJson(account));
        } else {
            throw new Exception();
        }

        return result;
    }

    @GetMapping("/checkId")
    public ShopResponse checkId(@RequestParam String id) {
        log.debug(id);

        Boolean result = accountService.checkId(id);
        if (result == true)
            return new ShopResponse(StatusCode.Fail, "중복 ID 입니다");
        else
            return new ShopResponse(StatusCode.Success, "사용가능합니다 ID 입니다");

    }

    @GetMapping("/updateAccount")
    public ShopResponse updateAccount(HttpServletRequest request) {
        log.debug("updateAccount");
        long userIdx = Long.valueOf(String.valueOf(request.getSession().getAttribute("userIdx")));

        Users user = accountService.updateAccount(userIdx);
        if (user != null)
            return new ShopResponse(StatusCode.Success, JsonUtil.toJson(user));
        else
            return new ShopResponse(StatusCode.Fail, "user 검색 실패");
    }

    @GetMapping("/updateUser")
    public ShopResponse updateUser(@RequestBody UserSetRequest user, HttpServletRequest request) {
        log.debug("updateUser");
        long userIdx = Long.valueOf(String.valueOf(request.getSession().getAttribute("userIdx")));
        user.setUserIdx(userIdx);
        accountService.updateUser(user);
        if (user != null)
            return new ShopResponse(StatusCode.Success, "user 정보 업데이트");
        else
            return new ShopResponse(StatusCode.Fail, "user 검색 실패");

    }

    // Security로 대체
//    @PostMapping("/login")
//    public ShopResponse login(@RequestBody UserSetRequest user) throws Exception {
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
//    }

}
